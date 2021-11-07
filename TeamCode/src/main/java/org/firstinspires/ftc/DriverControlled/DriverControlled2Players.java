package org.firstinspires.ftc.DriverControlled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.Robots.WedstrijdRobot;
import org.firstinspires.ftc.OtherObjects.Timer;
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
            Robot.Shooter.target.set(TARGET.HIGH_GOAL);     
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
            //--------------------------------------------------------------------
            //Getting inputs and calculating values for the drive system
            //--------------------------------------------------------------------
            
            
            
            
            
            //--------------------------------------------------------------------
            //Other controller input:
            // * Player 1:
            // * - start: Will reset the angular orientation of the imu. 
            // * - b: Will stop the shooter from shooting if the robot is currently shooting. 
            // * Player 2:
            // * - dpad_up: Moves the lift up.
            // * - dpad_down: Moves the lift down.
            // * - left_stick_y: Controls the intake.
            // * - right_stick_y: Controls the WobbleGoalgrabber.
            // * - left_bumper: Grabs the wobble goal with the WobbleGoalgrabber.
            // * - right_bumper: Lets go of the wobble goal.
            // * - a: The robot will prepare for shooting: The lift will move up and the shooter will start (Do Note player 1 still has control over the robot)
            // * - y: Shoots 3 rings in the high goal.
            // * - x: Shoots the 3 power shots autonomously (With a timer-based system so no crashy)
            // * - b: Will stop the shooter from shooting if the robot is currently shooting. 
            //--------------------------------------------------------------------
    
                if(gamepad1.start) 
                    Robot.imu.ResetAngularOrientation();

                Robot.Shooter.Ringprocessor.updateColorSens();
                telemetry.addData("HSV value", Robot.Shooter.Ringprocessor.TophsvValues[0]);
            
                if(gamepad2.dpad_up)
                  Robot.Shooter.Ringprocessor.MoveLiftUp();
                else if (gamepad2.dpad_down)
                {
                  Robot.Shooter.Ringprocessor.MoveLiftDown();
                  //Robot.Shooter.Ringprocessor.LiftServoRight.setPower(1.0);  
                  //Robot.Shooter.Ringprocessor.LiftServoLeft.setPower(-1.0); 
                }
                else 
                { 
                  Robot.Shooter.Ringprocessor.StopLift();
                  //Robot.Shooter.Ringprocessor.LiftServoRight.setPower(0);  
                  //Robot.Shooter.Ringprocessor.LiftServoLeft.setPower(0);     
                }        
                
                if(gamepad2.dpad_left)
                  Robot.Shooter.Ringprocessor.LoaderServo.setPosition(Robot.Shooter.Ringprocessor.PushedPosition);    
                
                if(gamepad2.dpad_right)
                  Robot.Shooter.Ringprocessor.LoaderServo.setPosition(Robot.Shooter.Ringprocessor.DefaultPosition);    
                
                Robot.Intake.Slurp(-gamepad2.left_stick_y);
                
                if(gamepad2.right_stick_y < 0)
                    Robot.WobbleGoalgrabber.Arm.setPower(0.85*gamepad2.right_stick_y);
                else
                    Robot.WobbleGoalgrabber.Arm.setPower(0.5*gamepad2.right_stick_y);
                
                
                if(gamepad2.left_bumper)
                    Robot.WobbleGoalgrabber.GrabWobbleGoal();
                
                if(gamepad2.right_bumper)
                    Robot.WobbleGoalgrabber.DropWobbleGoal();                
                
                //--------------------------------------------------------------------
                //Shooting
                //--------------------------------------------------------------------
                    if(!Robot.Shooter.isShooting)
                    {
                        if(gamepad2.a)
                            Robot.Shooter.Prepare();
                        else
                            Robot.Shooter.StopPreparing();  //Stop motors from moving
                            
                        if(gamepad2.y)
                        {
                            Robot.Shooter.target.set(TARGET.HIGH_GOAL);
                            Robot.Shooter.StartShooting();
                            Robot.Drivetrain.setPower(0); //Prevents Robot from driving whilst shooting
                        }
                        else if(gamepad2.x)
                        {
                            Robot.Shooter.target.set(TARGET.POWER_SHOT);
                            Robot.Shooter.StartShooting();
                            Robot.Drivetrain.setPower(0); //Prevents Robot from driving whilst shooting
                        }
                    }
                    else
                    {
                        Robot.Shooter.ShootRings();
                        if(gamepad1.b || gamepad2.b)
                            Robot.Shooter.Stop();
                    }
                    
                    telemetry.addData("Ringshooter isShooting", Robot.Shooter.isShooting);
                    telemetry.addData("RingProcessor State", Robot.Shooter.Ringprocessor.State);
                    telemetry.addData("Shooter State", Robot.Shooter.shooter.State);
                    
                    telemetry.addData("Target", Robot.Shooter.target.get());
                    
                    telemetry.addData("ShooterSpeed", Robot.Shooter.getVelocity());
                //--------------------------------------------------------------------
                //Shooting
                //--------------------------------------------------------------------
                
                telemetry.addData("X", Robot.Odometry.getX());
                telemetry.addData("Y", Robot.Odometry.getY());      
                
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
                if(!Robot.Shooter.isShooting)
                {
                    Robot.Drivetrain.setStrafeValues(StrafeAngle, StrafeSpeed);
                    Robot.Drivetrain.addSpeed(-TurnSpeed, -TurnSpeed, TurnSpeed, TurnSpeed);            
                    
                    
                    //--------------------------------------------------------------------
                    //Gyro correction
                    //--------------------------------------------------------------------
                        if(gamepad1.right_stick_x != 0) 
                            AntiJerkTimer.Reset();
                            
                        if(AntiJerkTimer.getTime() < 0.25)
                            GyroCorrectionAngle = Robot.imu.getAngle();
                         
                        DeviationAngle = RobotAngle - GyroCorrectionAngle;             
                        
                        //If there are no big jumps in angle, we are not standing still, not turning and 250 seconds has elapsed with no turning:   
                        if(Math.abs(DeviationAngle) < 90 && StrafeSpeed != 0 && gamepad1.right_stick_x == 0 && AntiJerkTimer.getTime() > 0.25)              
                        {
                            if(DeviationAngle > -45 && DeviationAngle < 45){
                                CorrectionFactor = DeviationAngle/45;
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
                    
                    if(gamepad1.left_bumper)
                        Robot.Drivetrain.setSpeed(Robot.ColorSensors.getSpeedValues());
                    
                    if(gamepad1.right_bumper)
                        Robot.Drivetrain.MultiplySpeed(0.25);
                    
                    Robot.Drivetrain.setPower();
                    
                }
                
            //--------------------------------------------------------------------
            //Final calculations for the Drivetrain 
            //--------------------------------------------------------------------
            
        }
        
    //--------------------------------------------------------------------
    //Loop
    //--------------------------------------------------------------------          
}


