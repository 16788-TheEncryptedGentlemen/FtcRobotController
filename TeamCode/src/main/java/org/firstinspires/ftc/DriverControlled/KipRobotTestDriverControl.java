package org.firstinspires.ftc.DriverControlled;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.Robots.KipRobot;

@TeleOp
public class KipRobotTestDriverControl extends OpMode
{
    KipRobot Robot;
    
    @Override
    public void init()
    {
        Robot = new KipRobot(this);
    }
    
    @Override
    public void loop()
    {
        if (gamepad1.b)
        {
            Robot.Grabber.Open();
        }
        if (gamepad1.x)
        {
            Robot.Grabber.Close();
        }
        
        if(-gamepad1.left_stick_y > 0)
        {
            Robot.Grabber.MoveIn();
        }
        else if(-gamepad1.left_stick_y < 0)
        {
            Robot.Grabber.MoveOut();
        }
        else
        {
            Robot.Grabber.Stop();
        }
    }
    
}
