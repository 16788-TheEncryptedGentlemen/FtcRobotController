package org.firstinspires.ftc.teamcode.drivercontrolled;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robotparts.Timer;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

@TeleOp
public class DriverControlled extends TeleOpMode<CompetitionRobot> {
    /**
     * The desired heading when strafing.
     */
    private double desiredHeading = 0;
    private Timer antiJerkTimer;

    @Override
    public void initCode() {
        robot = new CompetitionRobot(this);
        antiJerkTimer = new Timer();
    }

    /**
     * Repeats program until program is stopped
     */
    public void controlLoop() {
        controlDrivetrain();
        controlShooter();
        controlIntake();
    }

    private void controlDrivetrain() {
        // The up and down movements of the left joystick on the gamepad of player 1.
        // There is a minus because up is negative and down is positive on the controller.
        double leftJoyY = -gamepad1.left_stick_y;
        // The left and right movements of the left joystick on the gamepad of player 1.
        double leftJoyX = gamepad1.left_stick_x;

        // The speed of strafing (between 0 and 1).
        double strafeSpeed = Math.hypot(leftJoyX, leftJoyY);

        // The control stick is not perfect and it can have a radius bigger than 1.
        if (strafeSpeed > 1) {
            strafeSpeed = 1;
        }

        // The direction in which the robot should strafe in <-180, 180].
        double strafeAngle = 0;
        if (strafeSpeed != 0 && leftJoyX != 0) {
            // If the robot is moving towards the side, then calculate the direction.
            strafeAngle = Math.signum(leftJoyX) * Math.toDegrees(Math.acos(leftJoyY / strafeSpeed));
        } else if (leftJoyY < 0) {
            // If the robot is moving backwards, then the direction is 180 degrees.
            strafeAngle = 180;
        } else {
            // If the robot is moving forward or not strafing then the direction is 0 degrees.
            strafeAngle = 0;
        }
        // Set strafe values of the drivetrain.
        robot.drivetrain.setStrafeValues(strafeAngle, strafeSpeed);

        // The speed of turning between -1 (left) and 1 (right).
        double turnSpeed = gamepad1.right_stick_x;
        // Two of the speeds are reversed because they
        robot.drivetrain.addSpeed(-turnSpeed, -turnSpeed, turnSpeed, turnSpeed);

        // Correct the strafing angle when strafing and not rotating.
        if (strafeSpeed > 0 && turnSpeed == 0) {
            correctHeading();
        } else {
            // Otherwise, keep the timer at 0.
            antiJerkTimer.Reset();
        }

        robot.drivetrain.fixMotorSpeedOverflow();

        if (gamepad1.right_bumper) robot.drivetrain.multiplySpeed(0.7);
        else if (gamepad1.left_bumper) robot.drivetrain.multiplySpeed(0.2);
        else robot.drivetrain.multiplySpeed(0.5);

        robot.drivetrain.setPower();
    }

    private void correctHeading() {
        // The measured robot angle, from the IMU.
        double robotAngle = robot.imu.getAngle();

        // Only update the correction angle during the first 0.5s of strafing.
        if (antiJerkTimer.getTime() < 0.1) {
            desiredHeading = robotAngle;
            // This also means deviationAngle will be 0, so we can skip the rest.
            return;
        }

        // Compute the deviation from the desired angle.
        double deviationAngle = robotAngle - desiredHeading;

        // Do not correct the angle if there is a big jump in angle.
        if (Math.abs(deviationAngle) >= 90) {
            return;
        }

        // Correct small angles proportional to the angle, capped at +/-1.
        double correctionFactor = 0;
        if (deviationAngle > -30 && deviationAngle < 30) {
            correctionFactor = deviationAngle / 30;
        } else {
            correctionFactor = Math.signum(deviationAngle);
        }

        robot.drivetrain.addSpeed(-correctionFactor, -correctionFactor, correctionFactor, correctionFactor);
    }

    private void controlIntake() {
        if (gamepad2.left_bumper) {
            robot.intake.start();
        } else {
            robot.intake.stop();
        }

        if (gamepad2.left_stick_y > 0.5) {
            robot.transport.deliverBall();
        } else if (gamepad2.left_stick_y < -0.5) {
            robot.transport.reverseBall();
        } else {
            robot.transport.stop();
        }
    }

    private void controlShooter() {
        if (gamepad2.rightBumperWasPressed()) {
            robot.shooter.toggle();
        }

        // update speed
        if (gamepad2.dpadUpWasPressed()) {
            robot.shooter.faster();
        } else if (gamepad2.dpadDownWasPressed()) {
            robot.shooter.slower();
        } else if (gamepad2.dpadRightWasPressed()) {
            robot.shooter.changeRPM(1050);
        } else if (gamepad2.dpadLeftWasPressed()) {
            robot.shooter.changeRPM(1500);
        }
    }
}
