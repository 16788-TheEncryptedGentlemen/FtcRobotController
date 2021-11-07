package org.firstinspires.ftc.Robots;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class LoaderDisplay 
{
    //---------------------------------------------------------------------
    //Used variabels:
    // * runningOpModeAut: A LinearOpMode that is associative to the main program, if the main program is a LinearOpMode
    // * runningOpModeDC: An OpMode that is associative to the main program, if the main program is an OpMode  
    //---------------------------------------------------------------------    
        public LinearOpMode runningLinearOpMode = null;
        public OpMode runningOpMode = null; 
    //---------------------------------------------------------------------
    //Used variabels
    //---------------------------------------------------------------------
    
    
    
    
    
    //---------------------------------------------------------------------
    //Methods:
    //ShowLoading(): Shows the Object that is currently being loaded
    //---------------------------------------------------------------------
        public void ShowLoading(String objectName)
        {
            if(runningLinearOpMode != null)
            {
                runningLinearOpMode.telemetry.addData("Loading", objectName);
                runningLinearOpMode.telemetry.update();
            }
            else if(runningOpMode != null)
            {
                runningOpMode.telemetry.addData("Loading", objectName);
                runningOpMode.telemetry.update();
            }
        } 
}