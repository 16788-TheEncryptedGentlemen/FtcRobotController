package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;
import org.firstinspires.ftc.teamcode.robotparts.Timer;

@TeleOp
public class DriverControlled extends OpMode {
    // TODO: Still need to test it!

    /**
     * The robot
     */
    CompetitionRobot robot;

    /**
     * The desired heading when strafing.
     */
    private double desiredHeading = 0;
    private Timer antiJerkTimer;

    @Override
    /** Initialisation */
    public void init() {
        robot = new CompetitionRobot(this);
        antiJerkTimer = new Timer();
    }

    @Override
    /** Repeats program until program is stopped */
    public void loop() {
        controlGrabber();
        controlArm();
        controlDrivetrain();
        controlTiltMechanism();
        controlDroneLauncher();
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
        //  telemetry.addData("X", robot.odometry.getX());
        //   telemetry.addData("Y", robot.odometry.getY());
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
        // TODO: figure out what's going out here.
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

        robot.drivetrain.addSpeed(correctionFactor, correctionFactor, -correctionFactor, -correctionFactor);
        telemetry.addData("GyroCorrectionFactor", correctionFactor);
    }

    /**
     * Controls of the arm that is attached to the grabber on the robot.
     */
    private void controlArm() {
        // There is a minus because up is negative and down is positive on the controller.
        double direction = -gamepad2.right_stick_y;
        if (direction > 0.1) {
            telemetry.addLine("Arm Up");
            robot.arm.MoveArmUp();
        } else if (direction < -0.1) {
            telemetry.addLine("Arm Down");
            robot.arm.MoveArmDown();
        } else {
            telemetry.addLine("Arm Stop");
            robot.arm.StopArm();
        }
        telemetry.addData("Angle Arm", robot.arm.motor.getCurrentPosition());
        telemetry.addData("Target Arm", robot.arm.position);

    }


    // Controls of the left grabber on the robot for the pixel.
    private void controlGrabber() {
        if (gamepad2.x) {
            telemetry.addLine("Grab");
            robot.grabber.Grab();
        } else if (gamepad2.y) {
            telemetry.addLine("Drop");
            robot.grabber.Drop();
        }
    }

    /*     // Controls of the right grabber on the robot for the pixel.
        if (gamepad2.a) {
            telemetry.addLine("GrabRight");
            robot.grabber.Grab();
        } else if (gamepad2.b) {
            telemetry.addLine("DropRight");
            robot.grabber.Drop();
        }

       // Controls of the left grabber on the robot for the pixel.
        if (gamepad2.right_bumper) {
            telemetry.addLine("GrabLeft");
            robot.grabberLeft.Grab();
            robot.grabberRight.Grab();
        } else if (gamepad2.left_bumper) {
            telemetry.addLine("DropLeft");
            robot.grabberLeft.Drop();
            robot.grabberRight.Drop();
        }
    } */

    private void controlDroneLauncher (){
          if (gamepad2.a){
              robot.droneLauncher.launch();
              telemetry.addLine("DroneLauncherLaunches");
          }
        /*  else{
              robot.droneLauncher.stop();
              telemetry.addLine("DroneLauncherStop");
          }*/
      }
    private void controlTiltMechanism() {
        if (gamepad2.dpad_up) {
            robot.tiltMechanism.TiltMechanismUp();
            telemetry.addLine("TiltMechanismUp");
        } else if (gamepad2.dpad_down) {
            robot.tiltMechanism.TiltMechanismDown();
            telemetry.addLine("TiltMechanismDown");
//        } else {
//            robot.tiltMechanism.StopTiltMechanism();
//            telemetry.addLine("StopTiltMechanism");
//        }
        }
    }
}


