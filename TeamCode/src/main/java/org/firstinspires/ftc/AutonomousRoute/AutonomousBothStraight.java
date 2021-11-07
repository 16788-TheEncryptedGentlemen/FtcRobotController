package org.firstinspires.ftc.AutonomousRoute;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.Robots.WedstrijdRobot;

import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.OtherObjects.Targets.TARGET_ENUM_CLASS.TARGET;
import org.firstinspires.ftc.OtherObjects.Timer;

@Autonomous

public class AutonomousBothStraight extends LinearOpMode{
    
    public void runOpMode() {
        
        WedstrijdRobot Robot = new WedstrijdRobot(this); 
        Timer LiftTimer = new Timer();
        
        Robot.WobbleGoaldropper.Hold(); 
     
        waitForStart();
        
        sleep(3000);

        Robot.Drivetrain.DriveStraight(180, 0.85);
        Robot.WobbleGoaldropper.Drop();
       
    }
}
