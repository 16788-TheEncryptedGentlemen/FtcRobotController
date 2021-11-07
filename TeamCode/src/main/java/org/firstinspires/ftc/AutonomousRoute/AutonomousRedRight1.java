package org.firstinspires.ftc.AutonomousRoute;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.Robots.WedstrijdRobot;

import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.OtherObjects.Targets.TARGET_ENUM_CLASS.TARGET;
import org.firstinspires.ftc.OtherObjects.Timer;

@Autonomous

public class AutonomousRedRight1 extends LinearOpMode{
    
    public void runOpMode() {
        
        WedstrijdRobot Robot = new WedstrijdRobot(this); 
        Timer LiftTimer = new Timer();
        
        Robot.WobbleGoaldropper.Hold(); 
        
        double DriveSpeed = 0.85;
        
        
        
        
        //----------------------------------------------------------------------------
        //Routes
        //----------------------------------------------------------------------------
            BezierCurveRoute Ring0ToDropOff = new BezierCurveRoute(
                new double[] {104.204, -56.882}, //The x-coefficients 
                new double[] {-30.5920000000001, 188.332}, //The y-coefficients 
                Robot, 
                DriveSpeed, 
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW, 
                this 
            );        
            BezierCurveRoute Ring0ToShoot = new BezierCurveRoute(
                new double[] {-120.456, 0.955999999999989}, //The x-coefficients 
                new double[] {-58.316, 57.838}, //The y-coefficients 
                Robot, 
                DriveSpeed, 
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW, 
                this 
            );
            
            BezierCurveRoute Ring1ToDropOff = new BezierCurveRoute(
                new double[] {173.514, -157.74, -27.2459999999997}, //The x-coefficients 
                new double[] {11.4719999999998, 600.846, -405.822}, //The y-coefficients 
                Robot, 
                DriveSpeed, 
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW, 
                this 
            );        
            BezierCurveRoute Ring1ToShoot = new BezierCurveRoute(
                new double[] {8.60399999999981, -68.8319999999999}, //The x-coefficients 
                new double[] {-108.028, 58.794}, //The y-coefficients 
                Robot, 
                DriveSpeed, 
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW, 
                this 
            );
            
            BezierCurveRoute Ring4ToDropOff = new BezierCurveRoute(
                new double[] {105.16, -58.3160000000001}, //The x-coefficients 
                new double[] {-19.1200000000001, 283.932}, //The y-coefficients 
                Robot, 
                DriveSpeed, 
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW, 
                this 
            );
            BezierCurveRoute Ring4ToShoot = new BezierCurveRoute(
                new double[] {1.43399999999991, -2.86799999999982, -118.066}, //The x-coefficients 
                new double[] {-331.254, 329.82, -107.55}, //The y-coefficients 
                Robot, 
                DriveSpeed, 
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW, 
                this 
            );
        //----------------------------------------------------------------------------
        //Routes
        //----------------------------------------------------------------------------
        
        
        
        
        int NumerofRings;
        NumerofRings = Robot.webcam.getNumberOfRings();
        
        waitForStart();

        telemetry.addData("NumerofRings",NumerofRings);
        telemetry.update();
        
        switch(NumerofRings)
        {
            case 0:
                Ring0ToDropOff.ExecuteWithPointSkip();
                Robot.WobbleGoaldropper.Drop(); 
                Ring0ToShoot.ExecuteWithPointSkip();
                break;
            case 1:
                Ring1ToDropOff.ExecuteWithPointSkip();
                Robot.WobbleGoaldropper.Drop(); 
                Ring1ToShoot.ExecuteWithPointSkip();
                break;
            case 4:
                Ring4ToDropOff.ExecuteWithPointSkip();
                Robot.WobbleGoaldropper.Drop(); 
                Ring4ToShoot.ExecuteWithPointSkip();
                break;
        }
        
        
        //Position for the power shots and shoot the powershots
        Robot.Drivetrain.TurnRobotAO(0);        
        Robot.ColorSensors.AutonomousAlignRobot();
        Robot.Drivetrain.DriveStraight(-5, 0.4);
        
        Robot.Shooter.target.set(TARGET.POWER_SHOT);
        Robot.Shooter.StartShooting();    
        while(Robot.Shooter.isShooting)
            Robot.Shooter.ShootPowerShots();

        //Drive small bit forward
        Robot.Drivetrain.TurnRobotAO(0);
        Robot.Drivetrain.DriveStraight(10, 0.7);
        while(!Robot.Shooter.Ringprocessor.isLiftAtBottom() && !isStopRequested())
            Robot.Shooter.Ringprocessor.MoveLiftDown();
        
    }
}
