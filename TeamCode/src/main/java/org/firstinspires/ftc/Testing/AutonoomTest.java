package org.firstinspires.ftc.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.Robots.WedstrijdRobot;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute.DRIVE_METHOD;



@Autonomous

public class AutonoomTest extends LinearOpMode{

    public void runOpMode() {
        
        WedstrijdRobot Robot = new WedstrijdRobot(this); 
        /*
        double DriveSpeed = 0.85;        
        
        
        BezierCurveRoute ROUTE_0RING = new BezierCurveRoute(
            new double[] {82.4550000000006, -60.9450000000006}, //The x-coefficients 
            new double[] {135.035, 22.705}, //The y-coefficients   
            Robot,
            DriveSpeed,
            DRIVE_METHOD.STRAFE,
            this
        );            
        */
        
        
        waitForStart();
        
        while(opModeIsActive())
        {
            //Robot.Shooter.shooter.setVelocity(1700);
            telemetry.addData("Speed", Robot.Shooter.getVelocity());
            telemetry.update();
        }

    }
}
