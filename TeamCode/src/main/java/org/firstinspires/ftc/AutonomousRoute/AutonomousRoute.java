package org.firstinspires.ftc.AutonomousRoute;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.Robots.WedstrijdRobot;

import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute.DRIVE_METHOD;

@Autonomous

public class AutonomousRoute extends LinearOpMode{
    
    public void runOpMode() {
        
        WedstrijdRobot Robot = new WedstrijdRobot(this); 
        
        Robot.WobbleGoaldropper.Hold(); 
        
        double DriveSpeed = 0.85;
        
        
        BezierCurveRoute ROUTE_0RING = new BezierCurveRoute(
            new double[] {83.172, -38.24}, //The x-coefficients 
            new double[] {61.1840000000001, 94.1659999999999}, //The y-coefficients 
            Robot,
            DriveSpeed,
            DRIVE_METHOD.FOLLOW,
            this
        );    
        
        BezierCurveRoute ROUTE_0RING_NAAR_SCHIETPUNT = new BezierCurveRoute(
            new double[] {-24.8560000000001, -96.556}, //The x-coefficients 
            new double[] {-76.48, 75.524}, //The y-coefficients 
            Robot,
            DriveSpeed,
            DRIVE_METHOD.FOLLOW,
            this
        );              

        BezierCurveRoute ROUTE_1RING = new BezierCurveRoute(
            new double[] {205.062, -453.144, 237.566}, //The x-coefficients 
            new double[] {428.766, -411.558, 184.508}, //The y-coefficients    
            Robot,
            DriveSpeed,
            DRIVE_METHOD.STRAFE,
            this
        );  
        
        BezierCurveRoute ROUTE_1RING_NAAR_SCHIETPUNT = new BezierCurveRoute(
            new double[] {-6.69199999999992, -53.536}, //The x-coefficients 
            new double[] {-131.928, 89.386}, //The y-coefficients 
            Robot,
            DriveSpeed,
            DRIVE_METHOD.STRAFE,
            this
        );           
        
        BezierCurveRoute ROUTE_4RINGEN = new BezierCurveRoute(
            new double[] {94.644, -51.1459999999999}, //The x-coefficients 
            new double[] {116.632, 152.004}, //The y-coefficients    
            Robot,
            DriveSpeed,
            DRIVE_METHOD.STRAFE,
            this
        );            
        
        BezierCurveRoute ROUTE_4RINGEN_NAAR_SCHIETPUNT = new BezierCurveRoute(
            new double[] {-9.56000000000012, -106.116}, //The x-coefficients 
            new double[] {-122.368, 9.08200000000005}, //The y-coefficients 
            Robot,
            DriveSpeed,
            DRIVE_METHOD.STRAFE,
            this
        );          
        
        int NumerofRings;
        NumerofRings = Robot.webcam.getNumberOfRings();
        
        waitForStart();

        telemetry.addData("NumerofRings",NumerofRings);
        telemetry.update();
        
        switch(NumerofRings){
            case 0:
                ROUTE_0RING.ExecuteWithPointSkip();
                Robot.WobbleGoaldropper.Drop(); 
                ROUTE_0RING_NAAR_SCHIETPUNT.ExecuteWithPointSkip();
                break;
            case 1:
                //ROUTE_1RING.ExecuteWithPointSkip();
                Robot.WobbleGoaldropper.Drop(); 
                //ROUTE_1RING_NAAR_SCHIETPUNT.ExecuteWithPointSkip();
                break;
            case 4:
                //ROUTE_4RINGEN.ExecuteWithPointSkip();
                Robot.WobbleGoaldropper.Drop(); 
                //ROUTE_4RINGEN_NAAR_SCHIETPUNT.ExecuteWithPointSkip();
                break;
        }
        
        Robot.Drivetrain.TurnRobotAO(0);        
        
        //Shoot the rings
        //Robot.ColorSensors.AutonomousAlignRobot();
        
        //Robot.Drivetrain.DriveBackward(5, 0.4);
        
        /* FIX THIS SHIT
        Robot.Shooter.setToBusyShooting("POWER_SHOT"); 
        
        while(!rings.isEmpty() && !isStopRequested())
        {
            Robot.Shooter.ShootPowerShots();
        }
        */
        
        //Drive small bit forward
        Robot.Drivetrain.TurnRobotAO(0);
        Robot.Drivetrain.DriveStraight(10, 0.7);
        
    }
}
