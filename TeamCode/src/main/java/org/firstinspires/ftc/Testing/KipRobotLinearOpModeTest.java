package org.firstinspires.ftc.Testing;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.Robots.KipRobot;

@Autonomous
public class KipRobotLinearOpModeTest extends LinearOpMode
{
    public void runOpMode()
    {
        KipRobot Robot = new KipRobot(this);
        
        waitForStart();
        
        Robot.Drivetrain.Vooruit();
        sleep(2000);
        Robot.Drivetrain.Links();
        sleep(4000);
        Robot.Drivetrain.Achteruit();
        sleep(2000);
        Robot.Drivetrain.Rechts();
        sleep(4000);
        

    }
}