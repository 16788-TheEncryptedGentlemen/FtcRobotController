package org.firstinspires.ftc.teamcode.drivercontrolled;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;

@TeleOp
public class DriverControlled extends OpMode {
    /** The robot */
    CompetitionRobot robot;

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
        // ---------------------------------------------------------------------
        // Getting inputs and calculating values for the drive system
        // * Getting inputs from controller, imu and calculating variables:
        // * LeftJoyY, LeftJoyX, RobotAngle, StrafeSpeed, StrafeAngle, TurnSpeed
        // --------------------------------------------------------------------
        /** Left joystick up and down on the gamepad */
        double LeftJoyY = -gamepad1.left_stick_y;
        /** Left joystick left and right on the gamepad */
        double LeftJoyX = gamepad1.left_stick_x;

        /** The angle the robot is in with help of the IMU. */
        double RobotAngle = robot.imu.getAngle();

        /** The speed of strafing. */
        double StrafeSpeed = Math.sqrt(Math.pow(LeftJoyX, 2) + Math.pow(LeftJoyY, 2));

        double StrafeAngle = 0;
        if (StrafeSpeed != 0 && LeftJoyX != 0)
            StrafeAngle = Math.signum(LeftJoyX) * Math.toDegrees(Math.acos(LeftJoyY / StrafeSpeed));
        else if (LeftJoyY < 0)
            StrafeAngle = 180;
        else
            StrafeAngle = 0;

        // The control stick is not perfect and it can have a radius bigger than 1. We
        // fix that here.
        if (StrafeSpeed > 1)
            StrafeSpeed = 1;

        // The speed of turning is controlled by moving the right joystick horizontally.
        double TurnSpeed = gamepad1.right_stick_x;

        robot.drivetrain.setStrafeValues(StrafeAngle, StrafeSpeed);
        robot.drivetrain.addSpeed(-TurnSpeed, -TurnSpeed, TurnSpeed, TurnSpeed);

        // Place AntiJerkTimer and GyroCorrection here, if there is an error.
        double GyroCorrectionAngle = 0;
        
        double DeviationAngle = RobotAngle - GyroCorrectionAngle;
        // Place DeviationAngle code here, if there is an error.

        double CorrectionFactor = 0;
        if (DeviationAngle > -30 && DeviationAngle < 30) {
            CorrectionFactor = DeviationAngle / 30;
        } else {
            CorrectionFactor = Math.signum(DeviationAngle);
        }
        // TODO: deze moet eigenlijk wel iets doen
        // Robot.drivetrain.addSpeed(CorrectionFactor,CorrectionFactor,-CorrectionFactor,-CorrectionFactor);

        robot.drivetrain.fixMotorSpeedOverflow();

        if (gamepad1.right_bumper)
            robot.drivetrain.multiplySpeed(0.7);
        else if (gamepad1.left_bumper)
            robot.drivetrain.multiplySpeed(0.2);
        else
            robot.drivetrain.multiplySpeed(0.5);

        robot.drivetrain.setPower();

        telemetry.addData("DeviationAngle", DeviationAngle);
        telemetry.addData("controllery", LeftJoyY);
        telemetry.addData("GyroCorrectionFactor", CorrectionFactor);
        telemetry.addData("Heading", robot.drivetrain.imu.getAngle());
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
