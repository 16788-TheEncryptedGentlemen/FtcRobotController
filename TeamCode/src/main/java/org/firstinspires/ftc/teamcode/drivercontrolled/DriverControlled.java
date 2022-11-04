package org.firstinspires.ftc.teamcode.drivercontrolled;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;

@TeleOp
public class DriverControlled extends OpMode {

    //--------------------------------------------------------------------
    //Used variables:
    // * Robot: The robot.
    // * LeftJoyX: The float value of the left joystick of player 1.
    // * LeftJoyY: The float value of the right joystick of player 1.
    // * StrafeSpeed: The speed of strafing (between 0 and 1).
    // * StrafeAngle: The angle at which the robot needs to strafe between -180 and 180 degrees.
    // * RobotAngle: The angle of the robot in the absolute orientation field.
    // * TurnSpeed: The speed of turning between -1 (left) and 1 (right).
    // * GyroCorrectionAngle: The angle that the robot needs to correct to whilst strafing with gyro correction.
    // * DeviationAngle: The deviation of the robot whilst strafing with gyro correction.
    // * CorrectionFactor: The correction factor for the motors whilst strafing with gyro correction.
    // * AntiJerkTimer: The timer used to counter any jerk that may be caused by the gyro correction.
    //--------------------------------------------------------------------
    CompetitionRobot Robot;

    //--------------------------------------------------------------------
    //Used variables
    //--------------------------------------------------------------------


    //--------------------------------------------------------------------
    //Initialisation
    //--------------------------------------------------------------------
    @Override
    public void init() {
        Robot = new CompetitionRobot(this);
    }
    //--------------------------------------------------------------------
    //Initialisation
    //--------------------------------------------------------------------


    //--------------------------------------------------------------------
    //Loop
    //--------------------------------------------------------------------
    @Override
    public void loop() {
        //--------------------------------------------------------------------
        //Getting inputs and calculating values for the drive system
        // * Getting inputs from controller, imu and calculating variables:
        // * LeftJoyY, LeftJoyX, RobotAngle, StrafeSpeed, StrafeAngle, TurnSpeed
        //--------------------------------------------------------------------

        if (gamepad2.a) {
            telemetry.addLine("Grab");
            Robot.grabber.grab();
        } else if (gamepad2.y) {
            telemetry.addLine("Drop");
            Robot.grabber.drop();
        } else {
            telemetry.addLine("StopGrabber");
            Robot.grabber.stop();
        }
    }
}


//--------------------------------------------------------------------
//Other controller input
//--------------------------------------------------------------------


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
