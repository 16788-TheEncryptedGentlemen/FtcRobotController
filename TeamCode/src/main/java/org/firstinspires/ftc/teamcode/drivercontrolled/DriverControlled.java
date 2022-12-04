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
    CompetitionRobot Robot;

    /** The float value of the left joystick of player 1. */
    private double LeftJoyX = 0;
    /** The float value of the right joystick of player 1. */
    private double LeftJoyY = 0;

    /** The speed of strafing (between 0 en 1) */
    private double StrafeSpeed = 0;
    /** The angle at which the robot needs to strafe between -180 and 180 degrees. */
    private double StrafeAngle = 0;

    /** The angle of the robot in the absolute orientation field. */
    private double RobotAngle = 0;

    /** The speed of turning between -1 (left) and 1 (right). */
    private double TurnSpeed = 0;

    /** The angle that the robot needs to correct to whilst strafing with gyro correction. */
    private double GyroCorrectionAngle = 0;
    /** The deviation of the robot whilst strafing with gyro correction. */
    private double DeviationAngle = 0;
    /** The correction factor for the motors whilst strafing with gyro correction. */
    private double CorrectionFactor = 0;
    // Place AntiJerkTimer object here, if there is an error.


    //--------------------------------------------------------------------
    @Override
    /** Initialisation */
    public void init()
    {
        Robot = new CompetitionRobot(this);
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
       /** Left joystick up and down on the gamepad */
        LeftJoyY = -gamepad1.left_stick_y;
        /** Left joystick left and right on the gamepad */
        LeftJoyX = gamepad1.left_stick_x;

        /** The angle the robot is in with help of the IMU.*/
        RobotAngle = Robot.imu.getAngle();

        /** The speed of strafing.*/
        StrafeSpeed = Math.sqrt(Math.pow(LeftJoyX,2) + Math.pow(LeftJoyY,2));

        if(StrafeSpeed != 0 && LeftJoyX != 0)
            StrafeAngle = Math.signum(LeftJoyX)*Math.toDegrees(Math.acos(LeftJoyY/StrafeSpeed));
        else if(LeftJoyY < 0)
            StrafeAngle = 180;
        else
            StrafeAngle = 0;

        //The control stick is not perfect and it can have a radius bigger than 1. We fix that here.
        if(StrafeSpeed > 1)
            StrafeSpeed = 1;

        /** The speed of turning is controlled by moving the right joystick horizontally. */
        TurnSpeed = gamepad1.right_stick_x;

        Robot.drivetrain.setStrafeValues(StrafeAngle, StrafeSpeed);
        Robot.drivetrain.addSpeed(-TurnSpeed, -TurnSpeed, TurnSpeed, TurnSpeed);
        //--------------------------------------------------------------------
        if(gamepad1.start)
            Robot.imu.reset();


        telemetry.addData("X", Robot.odometry.getX());
        telemetry.addData("Y", Robot.odometry.getY());
        telemetry.addData("IMU", Robot.imu.getAngle());

        //--------------------------------------------------------------------
        /** Controls of the grabber on the robot for the beacon. */
        if (gamepad2.x) {
            telemetry.addLine("Grab");
            Robot.grabber.grab();
        } else if (gamepad2.b) {
            telemetry.addLine("Drop");
            Robot.grabber.drop();
        }

        //--------------------------------------------------------------------
        /** Controls of the lift on the robot. */
        if (gamepad2.left_stick_y > 0.1){
            telemetry.addLine("Up");
            Robot.lift.up();
        } else if (gamepad2.left_stick_y < -0.1) {
            telemetry.addLine("Down");
            Robot.lift.down();
        } else {
            telemetry.addLine("StopLift");
            Robot.lift.stop();
        }
        //--------------------------------------------------------------------
        if (gamepad2.dpad_up) {
            telemetry.addLine("LiftLow");
            Robot.lift.liftLow();
        } else  {
            telemetry.addLine("StopLift");
            Robot.lift.stop();
        }
        if (gamepad2.dpad_right) {
            telemetry.addLine ("LiftLowPole");
            Robot.lift.liftLowPole();
        } else  {
            telemetry.addLine("StopLift");
            Robot.lift.stop();
        }
        if (gamepad2.dpad_down) {
            telemetry.addLine ("LiftMidPole");
            Robot.lift.liftMidPole();
        } else  {
            telemetry.addLine("StopLift");
            Robot.lift.stop();
        }
        if (gamepad1.dpad_right) {
                telemetry.addLine ("LiftHighPole");
                Robot.lift.liftHighPole();
        } else  {
            telemetry.addLine("StopLift");
            Robot.lift.stop();
        }

        //--------------------------------------------------------------------
        //Final calculations for the Drivetrain:
        // * GyroCorrection when strafing without turning.
        // * Fixing any overflow in the MotorSpeed array of the Drivetrain class.
        // * Sets new speed values if we auto align to shooting line.
        // * Slow mode.
        // * Sets power to the motors.
        //--------------------------------------------------------------------


        // Place AntiJerkTimer and GyroCorrection here, if there is an error.

        DeviationAngle = RobotAngle - GyroCorrectionAngle;
        // Place DeviationAngle code here, if there is an error.

            if(DeviationAngle > -30 && DeviationAngle < 30){
                CorrectionFactor = DeviationAngle/30;
            }
            else{
                CorrectionFactor = Math.signum(DeviationAngle);
            }
            //TODO: deze moet eigenlijk wel iets doen
           // Robot.drivetrain.addSpeed(CorrectionFactor,CorrectionFactor,-CorrectionFactor,-CorrectionFactor);

        //--------------------------------------------------------------------
        //Gyro correction
        //--------------------------------------------------------------------

        Robot.drivetrain.fixMotorSpeedOverflow();



        if(gamepad1.right_bumper)
            Robot.drivetrain.multiplySpeed(1.0);
        else if(gamepad1.left_bumper)
            Robot.drivetrain.multiplySpeed(0.2);
        else
            Robot.drivetrain.multiplySpeed(0.4);

        Robot.drivetrain.setPower();

        telemetry.addData("DeviationAngle", DeviationAngle);
        telemetry.addData("controllery", LeftJoyY);
        telemetry.addData("GyroCorrectionFactor",CorrectionFactor);
        telemetry.addData("Heading",Robot.drivetrain.imu.getAngle());

    }

    //--------------------------------------------------------------------
    //Final calculations for the Drivetrain
    //--------------------------------------------------------------------
}

//--------------------------------------------------------------------
//Loop
//--------------------------------------------------------------------

