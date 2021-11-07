package org.firstinspires.ftc.RobotParts.Grabbers;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.OtherObjects.Timer;

import org.firstinspires.ftc.opmodeThreads.opmodeThread;


public class TestThreadGrabber extends opmodeThread
{
    public Servo Grabber;
    
    
    public TestThreadGrabber(OpMode _runningOpMode)
    {
        runningOpMode = _runningOpMode; 
        Grabber = runningOpMode.hardwareMap.get(Servo.class, "Grabber");
    }
    public TestThreadGrabber(LinearOpMode _runningLinearOpMode)
    {
        runningLinearOpMode = _runningLinearOpMode;
        Grabber = runningLinearOpMode.hardwareMap.get(Servo.class, "Grabber");
    }
    
    private Timer timer = new Timer();
    public void run()
    {
        timer.Reset();
        while(!isStopRequested())
        {
            while(timer.getTime() < 2.0 && !isStopRequested())
            {
                Grabber.setPosition(0.5);
            }
            while(timer.getTime() < 4.0 && !isStopRequested())
            {
                Grabber.setPosition(0);
            }
            timer.Reset();
        }
    }
    
    public void DoCoolThreadStuff()
    {
        start();
    }
  
}
