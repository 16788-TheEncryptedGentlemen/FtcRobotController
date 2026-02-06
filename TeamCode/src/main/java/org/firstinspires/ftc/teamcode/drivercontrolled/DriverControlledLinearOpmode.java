package org.firstinspires.ftc.teamcode.drivercontrolled;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robotparts.Timer;
import org.firstinspires.ftc.teamcode.robots.DrivetrainTest;


@TeleOp
public class DriverControlledLinearOpmode extends LinearOpMode {

    /**
     * The robot
     */
    DrivetrainTest robot;

    /**
     * The desired heading when strafing.
     */
    private double desiredHeading = 0;
    private Timer antiJerkTimer;
    private boolean Shoot = false;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialisation
        robot = new DrivetrainTest(this);
        antiJerkTimer = new Timer();
        waitForStart();

        // DO NOT EDIT THIS CODE! MAKE CHANGES IN METHOD controlLoop!
        while (!isStopRequested()) controlLoop();
    }

    /**
     * Repeats program until program is stopped
     */
    public void controlLoop() {
        controlDrivetrain();
        telemetry.addData("X", robot.odometry.getX());
        telemetry.addData("Y", robot.odometry.getY());
        controlShooter();
        BallDelivery();
        Intake();
        SpeedChange();
    }

    private void controlDrivetrain() {
        // If start is pressed then the current heading of the imu is set to 0.
        if (gamepad1.start) {
            robot.imu.reset();
        }


        // The up and down movements of the left joystick on the gamepad of player 1.
        // There is a minus because up is negative and down is positive on the controller.
        double leftJoyY = -gamepad1.left_stick_y;
        // The left and right movements of the left joystick on the gamepad of player 1.
        double leftJoyX = gamepad1.left_stick_x;

        // Show distance (x,y) of the robot on the driver hub for debugging.
        telemetry.addData("X", robot.odometry.getX());
        telemetry.addData("Y", robot.odometry.getY());
        telemetry.addData("IMU", robot.imu.getAngle());

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
        telemetry.addData("robot angle", robotAngle);
        telemetry.addData("desired heading", desiredHeading);

        // Only update the correction angle during the first 0.5s of strafing.
        if (antiJerkTimer.getTime() < 0.1) {
            desiredHeading = robotAngle;
            // This also means deviationAngle will be 0, so we can skip the rest.
            return;
        }

        // Compute the deviation from the desired angle.
        double deviationAngle = robotAngle - desiredHeading;
        telemetry.addData("DeviationAngle", deviationAngle);

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
        telemetry.addData("GyroCorrectionFactor", correctionFactor);
    }

    //controls shooter
    private void BallDelivery() {
        if (gamepad2.left_stick_y > 0.5) {
            telemetry.addLine("Give Ball");
            robot.shooter.DeliverBall();
        } else if (gamepad2.left_stick_y < -0.5) {
            telemetry.addLine("Reverse Ball");
            robot.shooter.ReverseBall();
        } else {
            telemetry.addLine("New Ball");
            robot.shooter.NewBall();
        }
    }

    private void controlShooter() {

        if (gamepad2.rightBumperWasPressed()) {
            Shoot = !Shoot; //On / Off toggle
            telemetry.addData("Shoot", Shoot);
        }

        if (Shoot) {
            telemetry.addLine("Shooting");
            robot.shooterV.shootV(0);
        } else {
            telemetry.addLine("Don't shoot");
            robot.shooter.stopMotor();
        }
    }

    private void SpeedChange() {

        telemetry.addData("Shoot speed: ", robot.shooterV.RPM);
        if (gamepad2.dpadUpWasPressed()) {
            robot.shooterV.RPM = robot.shooterV.RPM + 5;

        } else if (gamepad2.dpadDownWasPressed()) {
            robot.shooterV.RPM = robot.shooterV.RPM - 5;
        } else if (gamepad2.dpadRightWasPressed()) {
            robot.shooterV.RPM = 1050;
        } else if (gamepad2.dpadLeftWasPressed()) {
            robot.shooterV.RPM = 1500;

        }
    }

    private void Intake() {
        if (gamepad2.left_bumper) {
            telemetry.addLine("Taking ball in");
            robot.intake.IntakeStart(0.0);
        } else {
            telemetry.addLine("No taking in");
            robot.intake.IntakeStop();
        }
    }

}
