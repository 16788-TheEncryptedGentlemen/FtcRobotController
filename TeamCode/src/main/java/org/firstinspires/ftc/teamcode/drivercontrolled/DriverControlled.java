package org.firstinspires.ftc.teamcode.drivercontrolled;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Timer;
import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;

@TeleOp
public class DriverControlled extends OpMode {
    /** The robot */
    CompetitionRobot robot;

    private double gyroCorrectionAngle = 0;
    private Timer antiJerkTimer;

    @Override
    /** Initialisation */
    public void init() {
        robot = new CompetitionRobot(this);
    }

    @Override
    /** Repeats program until program is stopped */
    public void loop() {
        // Reset the IMU if start is pressed.
        if (gamepad1.start) {
            robot.imu.reset();
        }

        // Show current position of the robot.
        telemetry.addData("X", robot.odometry.getX());
        telemetry.addData("Y", robot.odometry.getY());
        telemetry.addData("IMU", robot.imu.getAngle());

        // Control different robot parts.
        controlDrivetrain();
        controlLift();
        controlGrabber();
    }

    /** Set the drivetrain using the controller inputs. */
    private void controlDrivetrain() {
        double strafeSpeed = setStrafeValues();
        double turnSpeed = setTurnValues();

        // Left joystick up and down on the gamepad
        double LeftJoyY = -gamepad1.left_stick_y;

        setGyroCorrection(strafeSpeed, turnSpeed);

        robot.drivetrain.fixMotorSpeedOverflow();

        if (gamepad1.right_bumper)
            robot.drivetrain.multiplySpeed(0.7);
        else if (gamepad1.left_bumper)
            robot.drivetrain.multiplySpeed(0.2);
        else
            robot.drivetrain.multiplySpeed(0.5);

        robot.drivetrain.setPower();

        telemetry.addData("controllery", LeftJoyY);
        telemetry.addData("Heading", robot.drivetrain.imu.getAngle());
    }

    /**
     * Set the turning values in the drivetrain, based on the controller inputs.
     * 
     * @return The turning speed.
     */
    private double setTurnValues() {
        // The desired turning speed, between -1 (left) and 1 (right).
        double turnSpeed = gamepad1.right_stick_x;
        robot.drivetrain.addSpeed(-turnSpeed, -turnSpeed, turnSpeed, turnSpeed);
        return turnSpeed;
    }

    /**
     * Set the strafe values in the drivetrain, based on the controller inputs.
     * 
     * @return The strafe speed.
     */
    private double setStrafeValues() {
        // Left to right motion, between -1 (left) and 1 (right).
        double leftRightMotion = gamepad1.left_stick_x;
        // Up and down motion, between -1 (down) and 1 (up). negated so up is positive.
        double upDownMotion = -gamepad1.left_stick_y;

        // How far the left joystick is pushed from the center determines the speed. It
        // can have a radius bigger than 1, so we cap it at 1.
        double strafeSpeed = Math.hypot(leftRightMotion, upDownMotion);
        if (strafeSpeed > 1) {
            strafeSpeed = 1;
        }

        // The angle of the joystick determines the angle (in degrees).
        double strafeAngle = 0;
        if (leftRightMotion > 0) {
            // Strafing right-ish.
            strafeAngle = Math.toDegrees(Math.acos(upDownMotion / strafeSpeed));
        } else if (leftRightMotion < 0) {
            // Strafing left-ish.
            strafeAngle = -Math.toDegrees(Math.acos(upDownMotion / strafeSpeed));
        } else if (upDownMotion < 0) {
            // Strafing perfectly in reverse.
            strafeAngle = 180;
        }

        // Set values in the drivetrain.
        robot.drivetrain.setStrafeValues(strafeAngle, strafeSpeed);
        return strafeSpeed;
    }

    /** Compensate unwanted rotations during strafing. */
    private void setGyroCorrection(double strafeSpeed, double turnSpeed) {
        // The measured robot angle, from the IMU.
        double robotAngle = robot.imu.getAngle();

        if (turnSpeed != 0)
            antiJerkTimer.Reset();

        // Only update the correction angle during the first 0.5s of strafing.
        if (antiJerkTimer.getTime() < 0.5) {
            gyroCorrectionAngle = robotAngle;
            // This also means deviationAngle will be 0, so we can skip the rest.
            return;
        }

        // Compute the deviation from the desired angle.
        double deviationAngle = robotAngle - gyroCorrectionAngle;
        telemetry.addData("DeviationAngle", deviationAngle);

        // If there are no big jumps in angle, we are not standing still, not turning
        // and 250 seconds has elapsed with no turning:
        double correctionFactor = 0;
        if (Math.abs(deviationAngle) < 90 && strafeSpeed != 0 && turnSpeed == 0 && antiJerkTimer.getTime() > 0.25) {
            if (deviationAngle > -30 && deviationAngle < 30) {
                correctionFactor = deviationAngle / 30;
            } else {
                correctionFactor = Math.signum(deviationAngle);
            }
        }

        robot.drivetrain.addSpeed(correctionFactor, correctionFactor, -correctionFactor, -correctionFactor);
        telemetry.addData("GyroCorrectionFactor", correctionFactor);
    }

    /** Controls of the lift on the robot. */
    private void controlLift() {
        telemetry.addData("Left stick y:", gamepad2.left_stick_y);
        if (gamepad2.left_stick_y > 0.1) {
            telemetry.addLine("Up");
            robot.lift.up();
        } else if (gamepad2.left_stick_y < -0.1) {
            telemetry.addLine("Down");
            robot.lift.down();
        } else {
            telemetry.addLine("StopLift");
            robot.lift.stop();
        }
    }

    /** Controls of the grabber on the robot for the beacon. */
    private void controlGrabber() {
        if (gamepad2.x) {
            telemetry.addLine("Grab");
            robot.grabber.grab();
        } else if (gamepad2.b) {
            telemetry.addLine("Drop");
            robot.grabber.drop();
        }
    }
}
