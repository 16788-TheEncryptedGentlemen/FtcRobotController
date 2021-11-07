package org.firstinspires.ftc.Testing;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.OtherObjects.Timer;

public class FastTestThread extends Thread
{
    public Servo Grabber;
    
    public FastTestThread(OpMode runningOpMode)
    {
        Grabber = runningOpMode.hardwareMap.get(Servo.class, "Grabber");
    }
    
    
    private boolean Dead = false;
    public void Kill()
    {
        Dead = true;
    }
    
    private Timer timer = new Timer();
    public void run()
    {
        timer.Reset();
        while(!Dead)
        {
            while(timer.getTime() < 2.0 && !Dead)
            {
                Grabber.setPosition(0.5);
            }
            while(timer.getTime() < 4.0 && !Dead)
            {
                Grabber.setPosition(0);
            }
            timer.Reset();
        }
    }
    
}