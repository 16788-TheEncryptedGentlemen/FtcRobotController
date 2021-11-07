package org.firstinspires.ftc.Robots;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.RobotParts.Odometry.OdometryOmniWheels; 
import org.firstinspires.ftc.RobotParts.IMU;
import org.firstinspires.ftc.RobotParts.Drivetrains.MecanumDrivetrain;
import org.firstinspires.ftc.RobotParts.Intakes.CompliantIntake;
import org.firstinspires.ftc.RobotParts.Grabbers.*;
import org.firstinspires.ftc.RobotParts.Webcam.Webcam;
import org.firstinspires.ftc.RobotParts.Shooters.Ringshooter.RingShooter;
import org.firstinspires.ftc.RobotParts.Other.ShootPositionColorSensors;



public class WedstrijdRobot 
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
        public CompliantIntake Intake;
        public WobbleGoalDropper WobbleGoaldropper;
        public WobbleGoalGrabber WobbleGoalgrabber;
        public Webcam webcam;
        public RingShooter Shooter;
        public ShootPositionColorSensors ColorSensors;

        public LinearOpMode runningOpModeAut = null;
        public OpMode runningOpModeDC = null;
    //---------------------------------------------------------------------
    //Used variabels:
    //---------------------------------------------------------------------
    
    
    
    
    
    //---------------------------------------------------------------------
    //Constructors:
    // * LinearOpMode: The constructor for a LinearOpMode program
    // * OpMode: The constructor for an OpMode program
    //---------------------------------------------------------------------
        public WedstrijdRobot(LinearOpMode runningOpMode)
        { 
            runningOpModeAut = runningOpMode;
            HardwareMap hardwaremap = runningOpModeAut.hardwareMap;
            
            ShowLoading("Odometry");
            Odometry = new OdometryOmniWheels(hardwaremap);
            ShowLoading("IMU");    
            imu = new IMU(hardwaremap);
            ShowLoading("Drivetrain"); 
            Drivetrain = new MecanumDrivetrain(runningOpModeAut, Odometry, imu);
            ShowLoading("Intake");   
            Intake = new CompliantIntake(hardwaremap);   
            ShowLoading("Shooter");
            Shooter = new RingShooter(hardwaremap, Drivetrain);
            ShowLoading("ColorSensors");
            ColorSensors = new ShootPositionColorSensors(runningOpModeAut, Drivetrain);
            ShowLoading("Drop");
            WobbleGoaldropper = new WobbleGoalDropper(runningOpModeAut);
            ShowLoading("Webcam");
            webcam = new Webcam(runningOpModeAut);
            
            ShowLoading("Done initializing! Press start to play");
        }
        
        
        
        public WedstrijdRobot(OpMode runningOpmode)
        {
            runningOpModeDC = runningOpmode;
            HardwareMap hardwaremap = runningOpModeDC.hardwareMap;
            
            ShowLoading("Odometry");
            Odometry = new OdometryOmniWheels(hardwaremap);       
            ShowLoading("IMU");
            imu = new IMU(hardwaremap);  
            ShowLoading("Drivetrain"); 
            Drivetrain = new MecanumDrivetrain(hardwaremap, Odometry, imu); 
            ShowLoading("Intake");
            Intake = new CompliantIntake(hardwaremap);         
            ShowLoading("Shooter");
            Shooter = new RingShooter(hardwaremap, Drivetrain);
            ShowLoading("ColorSensors");
            ColorSensors = new ShootPositionColorSensors(hardwaremap, Drivetrain);
            ShowLoading("WobbleGoalgrabber");
            WobbleGoalgrabber = new WobbleGoalGrabber(hardwaremap);  
            
            ShowLoading("Done initializing! Press start to play");
        }
    //---------------------------------------------------------------------
    //Constructors
    //---------------------------------------------------------------------
    
    
    
    
    
    //---------------------------------------------------------------------
    //Other methods:
    // * ShowLoading(): Shows what object the robot is currently loading.
    //---------------------------------------------------------------------
        private void ShowLoading(String ObjectName)
        {
            if(runningOpModeAut != null)
            {
                runningOpModeAut.telemetry.addData("Loading", ObjectName);
                runningOpModeAut.telemetry.update();
            }
            else if(runningOpModeDC != null)
            {
                runningOpModeDC.telemetry.addData("Loading", ObjectName);
                runningOpModeDC.telemetry.update();
            }
        }
    //---------------------------------------------------------------------
    //Other methods
    //---------------------------------------------------------------------    
    
}
