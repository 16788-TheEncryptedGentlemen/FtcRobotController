package org.firstinspires.ftc.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.Robots.WedstrijdRobot2022;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute.DRIVE_METHOD;



@Autonomous

public class AutonoomTest extends LinearOpMode{
    
    public void runOpMode() {
        
        WedstrijdRobot2022 Robot = new WedstrijdRobot2022(this); 
        
        waitForStart();
    
        
    //    Robot.TestGrabber.DoCoolThreadStuff();
        while(!isStopRequested())
        {
           // Robot.ColorSensors.AutonomousAlignRobot();
            Robot.Drivetrain.DriveStraight(40,0.2);
         //   telemetry.addData("Thread isStop", Robot.TestGrabber.isStopRequested());
           // telemetry.update();
        }
        Robot.Drivetrain.Stop();
        
    }
}
