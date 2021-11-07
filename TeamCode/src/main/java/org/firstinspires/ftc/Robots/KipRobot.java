package org.firstinspires.ftc.Robots;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.RobotParts.Shooters.KipShooter;
import org.firstinspires.ftc.RobotParts.Grabbers.KipRobotGrabber;
import org.firstinspires.ftc.RobotParts.Intakes.KipIntake;
import org.firstinspires.ftc.RobotParts.Drivetrains.KipDrivetrain;

public class KipRobot 
{
    public KipShooter Shooter;
    public KipRobotGrabber Grabber;
    public KipIntake Intake;
    public KipDrivetrain Drivetrain;
    
    
    public KipRobot(OpMode runningOpMode)
    {
        Shooter = new KipShooter(runningOpMode);
        Grabber = new KipRobotGrabber(runningOpMode);
        Intake = new KipIntake(runningOpMode);
        Drivetrain = new KipDrivetrain(runningOpMode);
    }
    
    public KipRobot(LinearOpMode runningLinearOpMode)
    {
        Shooter = new KipShooter(runningLinearOpMode);
        Grabber = new KipRobotGrabber(runningLinearOpMode);
        Intake = new KipIntake(runningLinearOpMode);
        Drivetrain = new KipDrivetrain(runningLinearOpMode);
    }
    
}
