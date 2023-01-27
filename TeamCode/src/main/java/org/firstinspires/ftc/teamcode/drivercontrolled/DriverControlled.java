package org.firstinspires.ftc.teamcode.drivercontrolled;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;


@TeleOp
public class DriverControlled extends OpMode {

    // TODO: Aditi: Add fullstops to documentation and make sure spaces are correct!!!
    // TODO: Aditi : Make sure errors are fixed and make sure you write "!Does not work!" on top of your commits.
    // TODO: Still need to test it!

    //--------------------------------------------------------------------
    /** The robot */
    CompetitionRobot robot;

    // Place AntiJerkTimer object here, if there is an error.


    //--------------------------------------------------------------------
    @Override
    /** Initialisation */
    public void init() {
        robot = new CompetitionRobot(this);
    }
    //--------------------------------------------------------------------

    @Override
    /** Repeats program until program is stopped */
    public void loop() {
        //---------------------------------------------------------------------
        //Getting inputs and calculating values for the drive system
        // * Getting inputs from controller, imu and calculating variables:
        // * LeftJoyY, LeftJoyX, RobotAngle, StrafeSpeed, StrafeAngle, TurnSpeed
        //--------------------------------------------------------------------


        telemetry.addData("encoder", robot.lift.left.getCurrentPosition());

        //--------------------------------------------------------------------
        controlGrabber();

        //--------------------------------------------------------------------
        controlLift();


        //--------------------------------------------------------------------
        //Final calculations for the Drivetrain:
        // * GyroCorrection when strafing without turning.
        // * Fixing any overflow in the MotorSpeed array of the Drivetrain class.
        // * Sets new speed values if we auto align to shooting line.
        // * Slow mode.
        // * Sets power to the motors.
        //--------------------------------------------------------------------


        //--------------------------------------------------------------------
        //Gyro correction
        //--------------------------------------------------------------------

        controlDrivetrain();

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
        // TODO: figure out what's going out here.
        robot.drivetrain.addSpeed(-turnSpeed, -turnSpeed, turnSpeed, turnSpeed);

        correctHeading();

        robot.drivetrain.fixMotorSpeedOverflow();

        if (gamepad1.right_bumper) robot.drivetrain.multiplySpeed(0.7);
        else if (gamepad1.left_bumper) robot.drivetrain.multiplySpeed(0.2);
        else robot.drivetrain.multiplySpeed(0.5);

        robot.drivetrain.setPower();
    }

    private void correctHeading() {
        //``````````````````````````````````````````````````````````````````````````````````````````````````````````    11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````      1   1 The heading of the robot in the absolute orientation field.
        double robotAngle = robot.imu.getAngle();

        // Place AntiJerkTimer and GyroCorrection here, if there is an error.

        /** The angle that the robot needs to correct to whilst strafing with gyro correction. */
        double gyroCorrectionAngle = 0;
        /** The deviation of the robot whilst strafing with gyro correction. */
        double deviationAngle = robotAngle - gyroCorrectionAngle;
        // Place DeviationAngle code here, if there is an error.

        /** The correction factor for the motors whilst strafing with gyro correction. */
        double correctionFactor = 0;
        if (deviationAngle > -30 && deviationAngle < 30) {
            correctionFactor = deviationAngle / 30;
        } else {
            correctionFactor = Math.signum(deviationAngle);
        }
        //TODO: deze moet eigenlijk wel iets doen
        // Robot.drivetrain.addSpeed(CorrectionFactor,CorrectionFactor,-CorrectionFactor,-CorrectionFactor);
    }

    private void controlGrabber() {
        /** Controls of the grabber on the robot for the beacon. */
        if (gamepad2.x) {
            telemetry.addLine("Grab");
            robot.grabber.grab();
        } else if (gamepad2.b) {
            telemetry.addLine("Drop");
            robot.grabber.drop();
        }
    }

    private void controlLift() {
        /** Controls of the lift on the robot. */
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

    //--------------------------------------------------------------------
    //Final calculations for the Drivetrain
    //--------------------------------------------------------------------
}

//--------------------------------------------------------------------
//Loop
//--------------------------------------------------------------------

