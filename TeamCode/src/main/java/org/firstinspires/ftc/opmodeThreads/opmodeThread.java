package org.firstinspires.ftc.opmodeThreads;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class opmodeThread extends Thread
{
    public LinearOpMode runningLinearOpMode = null;
    public OpMode runningOpMode = null;    
    
    
    private boolean KillThread = false;
    public void Kill()
    {
        KillThread = true;
    }
    
    
    public boolean isStopRequested()
    {
        if(runningLinearOpMode == null)
        {
            if(KillThread)
                return true;
            else
                return false;
        }
        else
        {
            if(runningLinearOpMode.isStopRequested())
                return true;
            else
                return false;
        }
    }    
    
    //-----------------------------------------
    
    public abstract void run();
}
