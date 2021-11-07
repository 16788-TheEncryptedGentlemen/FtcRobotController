package org.firstinspires.ftc.AutonomousRoute;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.Robots.WedstrijdRobot;

import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.OtherObjects.Targets.TARGET_ENUM_CLASS.TARGET;
import org.firstinspires.ftc.OtherObjects.Timer;

@Autonomous

public class AutonomousBlueRight1 extends LinearOpMode{
    
    public void runOpMode() {
        
        WedstrijdRobot Robot = new WedstrijdRobot(this); 
        Timer LiftTimer = new Timer();
        
        Robot.WobbleGoaldropper.Hold(); 
        
        double DriveSpeed = 0.85;
        
        
        
        
        //----------------------------------------------------------------------------
        //Routes
        //----------------------------------------------------------------------------
            BezierCurveRoute blueStartTo0 = new BezierCurveRoute(
                new double[] {-21.51, 73.4925, -120.695}, //The x-coefficients 
                new double[] {367.4625, -256.3275, 40.6300000000001}, //The y-coefficients 
                Robot, 
                DriveSpeed, 
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW 
                this 
            );  
            
           BezierCurveRoute blue0ToPowerShot = new BezierCurveRoute(
                new double[] {117.7075}, //The x-coefficients 
                new double[] {-3.585}, //The y-coefficients 
                Robot, 
                DriveSpeed, 
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW 
                this 
            );
            
            BezierCurveRoute blueStartTo1 = new BezierCurveRoute(
                new double[] {215.1, -280.825, -376.425, 1186.0375, -752.85}, //The x-coefficients 
                new double[] {-11.9500000000002, -137.425, 3035.3, -4561.9125, 1888.6975}, //The y-coefficients 
                Robot, 
                DriveSpeed, 
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW 
                this 
            );
            
            BezierCurveRoute blue1ToPowerShot = new BezierCurveRoute(
                new double[] {-16.1325, 39.4349999999999, 34.6550000000001}, //The x-coefficients 
                new double[] {-197.175, 236.61, -93.8075000000001}, //The y-coefficients 
                Robot, 
                DriveSpeed, 
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW 
                this 
            );
            
            BezierCurveRoute blueStartTo4 = new BezierCurveRoute(
                new double[] {382.4, -1129.275, 1009.775, 268.875, -598.0975}, //The x-coefficients 
                new double[] {47.8000000000001, -89.625, 2312.325, -3157.7875, 1156.76}, //The y-coefficients 
                Robot, 
                DriveSpeed, 
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW 
                this 
            );

            BezierCurveRoute blue4ToPowerShot = new BezierCurveRoute(
                new double[] {281.4225, -227.6475, 62.7375}, //The x-coefficients 
                new double[] {-43.02, 96.795, -167.3}, //The y-coefficients 
                Robot, 
                DriveSpeed, 
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW 
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
                blueStartTo0.ExecuteWithPointSkip();
                Robot.WobbleGoaldropper.Drop(); 
                blue0ToPowerShot.ExecuteWithPointSkip();
                break;
            case 1:
                blueStartTo1.ExecuteWithPointSkip();
                Robot.WobbleGoaldropper.Drop(); 
                blue1ToPowerShot.ExecuteWithPointSkip();
                break;
            case 4:
                blueStartTo4.ExecuteWithPointSkip();
                Robot.WobbleGoaldropper.Drop(); 
                blue4ToPowerShot.ExecuteWithPointSkip();
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
