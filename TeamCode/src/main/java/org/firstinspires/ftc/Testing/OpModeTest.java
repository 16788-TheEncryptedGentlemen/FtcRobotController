package org.firstinspires.ftc.Testing;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.Robots.WedstrijdRobot;
import org.firstinspires.ftc.Testing.FastTestThread;
import org.firstinspires.ftc.RobotParts.Grabbers.TestThreadGrabber;

@TeleOp
public class OpModeTest extends OpMode
{
    WedstrijdRobot Robot;
    FastTestThread TestThread;
    
    @Override
    public void init()
    {
        Robot = new WedstrijdRobot(this);
    }

    @Override
    public void stop() 
    {
        //TestThread.Kill();
        Robot.KillAllThreads();
    }
    
    @Override
    public void start()
    {
        Robot.TestGrabber.DoCoolThreadStuff();
    }
    
    public boolean FirstRun = true;
    @Override
    public void loop()
    {
        /*
        if(FirstRun)
        {
            Robot.TestGrabber.start();
            FirstRun = false;
        }
        */
        
        if(gamepad1.a)
            Robot.Drivetrain.setPower(0.5);
        else
            Robot.Drivetrain.Stop();
        
    }
    
}
