package org.firstinspires.ftc.RobotParts.Other;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.RobotParts.Drivetrains.MecanumDrivetrain;
import org.firstinspires.ftc.OtherObjects.Timer;
import org.firstinspires.ftc.RobotParts.Other.ColorSensorBasic;



public class ShootPositionColorSensors 
{
    //-------------------------------------------------------
    //Used variables:
    // * Right: The right colorsensor on the robot
    // * Left: The left colorsensor on the robot
    // * runningOpMode: The LinearOpMode in case of an autonomous program
    // * Drivetrain: The drivetrain of the robot for autonomous programs.
    // * timer: The timer used to correct to check if the robot is long enough in position
    // * LefthsvValues, Leftvalues, RighthsvValues, Rightvalues: The hsv values of the colorsensors.
    // * SCALE_FACTOR: The scale factor of the RGB values or something like that :)
    // * RightLine: Is true if the right color sensor detects the shooting line 
    // * LeftLine: Is true if the left color sensor detects the shooting line
    // * isDriverControlled: A boolean to account for driver controlled
    //-------------------------------------------------------
        public ColorSensorBasic Right;
        public ColorSensorBasic Left;
        private LinearOpMode runningOpMode;
        private MecanumDrivetrain Drivetrain;    
        private Timer timer = new Timer();
        
        public boolean RightLine = false;
        public boolean LeftLine = false;
        private boolean isDriverControlled;
    //-------------------------------------------------------
    //Used variables:   
    //-------------------------------------------------------    
    
    
    
    
    
    //-----------------------------------------------------------------------
    //Constructors:
    // * HardwareMap as first argument: The driver controlled variant of the Color Sensors.     
    // * LinearOpMode as first argument: The autonomous variant of the Color Sensors. 
    //-----------------------------------------------------------------------
        public ShootPositionColorSensors(HardwareMap hardwareMap, MecanumDrivetrain _Drivetrain)
        {
            isDriverControlled = true;
            
            Left = new ColorSensorBasic(hardwareMap, "Left");
            Right = new ColorSensorBasic(hardwareMap, "Right");
            Drivetrain = _Drivetrain;
        }
        
        public ShootPositionColorSensors(LinearOpMode _runningOpMode, MecanumDrivetrain _Drivetrain)
        {
            this(_runningOpMode.hardwareMap, _Drivetrain);
            runningOpMode = _runningOpMode;
            isDriverControlled = false;
        }
    //-----------------------------------------------------------------------
    //Constructor
    //-----------------------------------------------------------------------





    //-----------------------------------------------------------------------
    //Methods:
    // - AutonomousAlignRobot(): Aligns the robot autonomously on the shootingline
    // - update(): Updates the color values 
    // - getSpeedValues(): Returns speed values for the motor to align on the shootingline as array {RV, RA, LV, LA}
    // - inPosition(): Returns true if the robot is certainly on the shooting line
    //-----------------------------------------------------------------------
        public void AutonomousAlignRobot()
        {
            while(!runningOpMode.isStopRequested() && !inPosition())
            {
               Drivetrain.setPower(getSpeedValues());
            }
            Drivetrain.DriveStraight(-3, 0.15);
        }
        
        public double[] getSpeedValues()
        {
            if(Left.getHSVvalue() > 110)
                LeftLine = true;
            else 
                LeftLine = false;
            
            if(Right.getHSVvalue() > 110) 
                RightLine = true;
            else 
                RightLine = false;
            
            if(isDriverControlled)
            {
                if (!LeftLine && !RightLine)
                    return new double[] {0.3, 0.3, 0.3, 0.3};
                else if (LeftLine && !RightLine)
                    return new double[] {0.15, 0.15, -0.3, -0.3};
                else if (!LeftLine && RightLine)
                    return new double[] {-0.3, -0.3, 0.15, 0.15};
                else
                    return new double[4];             
            }
            else
            {
                if (!LeftLine && !RightLine)
                    return new double[] {0.15, 0.15, 0.15, 0.15};
                else if (LeftLine && !RightLine)
                    return new double[] {0.1, 0.1, -0.2, -0.2};
                else if (!LeftLine && RightLine)
                    return new double[] {-0.2, -0.2, 0.1, 0.1};
                else
                    return new double[4]; 
            }
        }
        
        public boolean inPosition()
        {
            if(!(LeftLine && RightLine))
                timer.Reset();
            
            if(timer.getTime() > 1)
                return true;
                
            return false;
        }
    //-----------------------------------------------------------------------
    //Methods  
    //-----------------------------------------------------------------------
    
}
