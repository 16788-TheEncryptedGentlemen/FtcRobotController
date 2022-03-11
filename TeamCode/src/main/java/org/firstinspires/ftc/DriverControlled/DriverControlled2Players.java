package org.firstinspires.ftc.DriverControlled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.Robots.WedstrijdRobot;
import org.firstinspires.ftc.OtherObjects.Timer;
import org.firstinspires.ftc.RobotParts.Spinner;
import org.firstinspires.ftc.RobotParts.Grabbers.Grabber;
import org.firstinspires.ftc.RobotParts.Other.Lift;
import org.firstinspires.ftc.RobotParts.Other.Arm;
import org.firstinspires.ftc.OtherObjects.Targets.TARGET_ENUM_CLASS.TARGET;

@TeleOp
public class DriverControlled2Players extends OpMode
{

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
        WedstrijdRobot Robot;

        private double LeftJoyX = 0;
        private double LeftJoyY = 0;

        private double StrafeSpeed = 0;
        private double StrafeAngle = 0;

        private double RobotAngle = 0;

        private double TurnSpeed = 0;

        private double GyroCorrectionAngle = 0;
        private double DeviationAngle = 0;
        private double CorrectionFactor = 0;

        private Timer AntiJerkTimer;
    //--------------------------------------------------------------------
    //Used variables
    //--------------------------------------------------------------------





    //--------------------------------------------------------------------
    //Initialisation
    //--------------------------------------------------------------------
        @Override
        public void init()
        {
            Robot = new WedstrijdRobot(this);
            AntiJerkTimer = new Timer();
        }
    //--------------------------------------------------------------------
    //Initialisation
    //--------------------------------------------------------------------    





    //--------------------------------------------------------------------
    //Loop
    //--------------------------------------------------------------------  
        @Override
        public void loop()
        {
            //--------------------------------------------------------------------
            //Getting inputs and calculating values for the drive system
            // * Getting inputs from controller, imu and calculating variables:
            // * LeftJoyY, LeftJoyX, RobotAngle, StrafeSpeed, StrafeAngle, TurnSpeed
            //--------------------------------------------------------------------
                LeftJoyY = -gamepad1.left_stick_y;
                LeftJoyX = gamepad1.left_stick_x;

                RobotAngle = Robot.imu.getAngle();

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

                TurnSpeed = gamepad1.right_stick_x;

                Robot.Drivetrain.setStrafeValues(StrafeAngle, StrafeSpeed);
                Robot.Drivetrain.addSpeed(-TurnSpeed, -TurnSpeed, TurnSpeed, TurnSpeed);


            //--------------------------------------------------------------------
            //Getting inputs and calculating values for the drive system
            //--------------------------------------------------------------------





            //--------------------------------------------------------------------
            //Other controller input:
            // * Player 1:
            // * - start: Will reset the angular orientation of the imu.
            // * Player 2:
            // * to be added
            //--------------------------------------------------------------------

                if(gamepad1.start)
                    Robot.imu.Reset();    //was: ResetAngularOrientation()



                telemetry.addData("X", Robot.Odometry.getX());
                telemetry.addData("Y", Robot.Odometry.getY());
                telemetry.addData("IMU", Robot.imu.getAngle());

                //Robot.spinner.Turn(-gamepad2.left_stick_y);

                if(gamepad2.right_trigger > 0)
                    Robot.spinner.DuckWheelMotor.setPower(-gamepad2.right_trigger);
                else if (gamepad2.left_trigger > 0)
                    Robot.spinner.DuckWheelMotor.setPower(gamepad2.left_trigger);
                else
                    Robot.spinner.DuckWheelMotor.setPower(0.0);


               if(gamepad2.dpad_up)
                   Robot.lift.SlideUp();
               else if(gamepad2.dpad_down)
                   Robot.lift.SlideDown();
               else
                   Robot.lift.StopLift();


          //     if(gamepad2.right_stick_y > 0)
               //    Robot.grabber.moveArmToFront();
             //   else if(gamepad2.right_stick_y < 0)
                  //  Robot.grabber.moveArmToBack();
                //else
            //    Robot.grabber.stopArm();

            if (gamepad2.a) {
                telemetry.addLine("BeweegVoor");
                Robot.grabber.Grab();
            } else if (gamepad2.y) {
                telemetry.addLine("BeweegAchter");
                Robot.grabber.Drop();
            } else {
                telemetry.addLine("Niks");
                Robot.grabber.StopGrabber();
            }

            if (gamepad2.right_stick_y > 0)
                    Robot.arm.MoveArmFront();
            else if (gamepad2.right_stick_y < 0)
                    Robot.arm.MoveArmBack();
            else
                    Robot.arm.StopArm();


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
                        if(gamepad1.right_stick_x != 0)
                            AntiJerkTimer.Reset();

                        if(AntiJerkTimer.getTime() < 0.5)
                            GyroCorrectionAngle = Robot.imu.getAngle();

                        DeviationAngle = RobotAngle - GyroCorrectionAngle;

                        //If there are no big jumps in angle, we are not standing still, not turning and 250 seconds has elapsed with no turning:
                        if(Math.abs(DeviationAngle) < 90 && StrafeSpeed != 0 && gamepad1.right_stick_x == 0 && AntiJerkTimer.getTime() > 0.25)
                        {
                            if(DeviationAngle > -30 && DeviationAngle < 30){
                                CorrectionFactor = DeviationAngle/30;
                            }
                            else{
                                CorrectionFactor = Math.signum(DeviationAngle);
                            }

                            Robot.Drivetrain.addSpeed(CorrectionFactor,CorrectionFactor,-CorrectionFactor,-CorrectionFactor);
                        }
                    //--------------------------------------------------------------------
                    //Gyro correction
                    //--------------------------------------------------------------------

                    Robot.Drivetrain.FixMotorSpeedOverflow();
                    
                    /*
                    if(gamepad1.left_bumper)
                      //  Robot.Drivetrain.setSpeed(Robot.ColorSensors.getSpeedValues());
                    */

                    if(gamepad1.right_bumper)
                        Robot.Drivetrain.MultiplySpeed(1.0);
                   else if(gamepad1.left_bumper)
                        Robot.Drivetrain.MultiplySpeed(0.2);
                   else
                       Robot.Drivetrain.MultiplySpeed(0.7);

                    Robot.Drivetrain.setPower();

                    telemetry.addData("DeviationAngle", DeviationAngle);
                    telemetry.addData("controllery", LeftJoyY);
                    telemetry.addData("GyroCorrectionFactor",CorrectionFactor);

                }

            //--------------------------------------------------------------------
            //Final calculations for the Drivetrain
            //--------------------------------------------------------------------
        }

    //--------------------------------------------------------------------
    //Loop
    //--------------------------------------------------------------------



