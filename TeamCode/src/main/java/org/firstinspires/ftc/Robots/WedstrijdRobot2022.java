package org.firstinspires.ftc.Robots;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.RobotParts.Odometry.OdometryOmniWheels; 
import org.firstinspires.ftc.RobotParts.IMU;
import org.firstinspires.ftc.RobotParts.Drivetrains.MecanumDrivetrain;
import org.firstinspires.ftc.RobotParts.Intakes.TestIntake;
import org.firstinspires.ftc.RobotParts.Grabbers.*;
import org.firstinspires.ftc.RobotParts.Webcam.Webcam;
import org.firstinspires.ftc.RobotParts.Shooters.Ringshooter.RingShooterOld;
import org.firstinspires.ftc.RobotParts.Other.ShootPositionColorSensors;


public class WedstrijdRobot2022 extends LoaderDisplay
{
    //---------------------------------------------------------------------
    //Used variabels:
    // * Odometry: The odometry of the robot to calculate the position of the robot
    // * IMU: The integrated measurement unit inside of the Expansion hub that we use as gyroscope
    // * Drivetrain: The drivetrain of the robot to move the robot
    // * Intake: The intake of the robot to take in rings
    // * WobbleGoaldropper: The grabber in front of the robot for autonomous to drop the wobblegoal
    // * WobbleGoalgrabber: The grabber that grabs the wobble goal and places it outside the field
    // * webcam: The webcam of the robot to measure the amount of rings at the start of autonomous
    // * Shooter: The shooter of the robot, including the processing system and the actual shooter
    // * ColorSensors: The color sensors to help align the robot on the shooting line
    // * runningOpModeAut: A LinearOpMode that is associative to the main program, if the main program is a LinearOpMode
    // * runningOpModeDC: An OpMode that is associative to the main program, if the main program is an OpMode
    //---------------------------------------------------------------------
        public OdometryOmniWheels Odometry;
        public IMU imu;
        public MecanumDrivetrain Drivetrain; 
      //  public Webcam webcam;
      //  public ShootPositionColorSensors ColorSensors;
        public TestIntake Intake;
    //---------------------------------------------------------------------
    //Used variabels:
    //---------------------------------------------------------------------
    
    
    
    
    
    //---------------------------------------------------------------------
    //Constructors:
    // * LinearOpMode: The constructor for a LinearOpMode program
    // * OpMode: The constructor for an OpMode program
    //---------------------------------------------------------------------
        public WedstrijdRobot2022(LinearOpMode _runningLinearOpMode)
        { 
            runningLinearOpMode = _runningLinearOpMode;
            HardwareMap hardwaremap = runningLinearOpMode.hardwareMap;
            
            ShowLoading("Odometry");
            Odometry = new OdometryOmniWheels(hardwaremap);
            ShowLoading("imu");    
            imu = new IMU(hardwaremap);
            ShowLoading("Drivetrain"); 
            Drivetrain = new MecanumDrivetrain(runningLinearOpMode, Odometry, imu);
        //    ShowLoading("ColorSensors");
           // ColorSensors = new ShootPositionColorSensors(runningLinearOpMode, Drivetrain);
          //  ShowLoading("webcam");
            //webcam = new Webcam(runningLinearOpMode);
          //  ShowLoading("Intake"); 
        //    Intake = new TestIntake (hardwaremap);
            
            ShowLoading("Done initializing! Press start to play");
        }
        
        
        
        public WedstrijdRobot2022(OpMode _runningOpmode)
        {
            runningOpMode = _runningOpmode;
            HardwareMap hardwaremap = runningOpMode.hardwareMap;
            
            ShowLoading("Odometry");
            Odometry = new OdometryOmniWheels(hardwaremap);       
            ShowLoading("imu");
            imu = new IMU(hardwaremap);  
            ShowLoading("Drivetrain"); 
            Drivetrain = new MecanumDrivetrain(hardwaremap, Odometry, imu); 
            //ShowLoading("ColorSensors");
           // ColorSensors = new ShootPositionColorSensors(hardwaremap, Drivetrain);
            
            ShowLoading("Done initializing! Press start to play");
        }
    //---------------------------------------------------------------------
    //Constructors
    //---------------------------------------------------------------------
    
    
    
    
    
    //---------------------------------------------------------------------
    //Other methods
    //---------------------------------------------------------------------
    
    
    
    //---------------------------------------------------------------------
    //Other methods
    //---------------------------------------------------------------------
}
