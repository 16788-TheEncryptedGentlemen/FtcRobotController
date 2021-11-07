package org.firstinspires.ftc.RobotParts.Webcam;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import com.qualcomm.robotcore.hardware.HardwareMap;



public class Webcam {

    //---------------------------------------------------------------------
    //Used variabels:
    // * TFOD_MODEL_ASSET: Afblijven
    // * LABEL_FIRST_ELEMENT: De naam voor de situatie waarbij er een stapel is van 4 ringen.
    // * LABEL_SECOND_ELEMENT: De naam voor de situatie waarbij er een stapel is van 1 ring.
    // * VUFORIA_KEY, vuforia, tfod: Afblijven
    // * runningOpMode: The autonomous OpMode program that is currently running
    //---------------------------------------------------------------------
        private static final String TFOD_MODEL_ASSET = "UltimateGoal.tflite";
        private static final String LABEL_FIRST_ELEMENT = "4 ringen";
        private static final String LABEL_SECOND_ELEMENT = "1 ring";
        private static final String VUFORIA_KEY =  "AZnfUjn/////AAABmZ3KlxU/cEmQidGvlz1AcUccR0PcNN2CvTi5gvy+lEm7hMo60+7JT86kMJ++Ols8cw6g0QB+C65NxoJUVieq6kncaGDiInXY/SimaGI9EEN5a6QmD1jzO3j9WMSCHltAwW/qvQjoRFIDBgPTe9MsRlD7Z8S7eXMHluSCel+wwoMhSiCcJiVbhXcs8Ml89d1E7NkR1I8kI4wW8iCCB9KtBvrpx10YgaKCJCXsfiOZ7AMmBJAKLkAU5ytb2xL1XySKnjzapiyfY6Wv/VZu9yLruP3VfApnaIR1LyFjNtOsJC3EgHGzCYEhfBMk8idY+zzNretpXcDmwZ+0Djn7g6ALyi/c/e90oFZUO44eEjIvjhrc";
        private VuforiaLocalizer vuforia;
        private TFObjectDetector tfod;
        private LinearOpMode runningOpMode;
    //---------------------------------------------------------------------
    //Used variabels
    //---------------------------------------------------------------------
    
    
    
    
    
    //---------------------------------------------------------------------
    //Constructor
    //---------------------------------------------------------------------
        public Webcam(LinearOpMode _runningOpMode)
        {
            runningOpMode = _runningOpMode;
            
            initVuforia(runningOpMode.hardwareMap, "Webcam");
            initTfod(runningOpMode.hardwareMap);
    
            if (tfod != null) 
            {
                tfod.activate();
                tfod.setZoom(2.5, 16/9); //Setzoom is very important. Change the first value to increase or decrease zoom on the camera
            }
        }
    //---------------------------------------------------------------------
    //Constructor
    //---------------------------------------------------------------------    
    
    
    
    
    
    //---------------------------------------------------------------------
    //Initialization methods:
    // * initVurforia(): Inits vuforia, whatever that might mean.
    // * initTfod(): Inits Tfod, whatever that might do.
    //---------------------------------------------------------------------    
        private void initVuforia(HardwareMap hardwareMap, String ConfigNaam) 
        {
            VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
            parameters.vuforiaLicenseKey = VUFORIA_KEY;
            parameters.cameraName = hardwareMap.get(WebcamName.class, ConfigNaam);
            vuforia = ClassFactory.getInstance().createVuforia(parameters);
        }
    
        private void initTfod(HardwareMap hardwareMap) 
        {
            int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
            tfodParameters.minResultConfidence = 0.75f;
            tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
            tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
        }
    //---------------------------------------------------------------------
    //Initialization methods
    //---------------------------------------------------------------------          
    
    
    
    
    
    //---------------------------------------------------------------------
    //Methods:
    // * getNumberOfRings(): Gets the number of rings of the nearest ring stack the webcam detects.
    //---------------------------------------------------------------------      
        public int getNumberOfRings()
        {
            int AmountOfRings = 0;
            
            
            while (!runningOpMode.isStarted() && !runningOpMode.isStopRequested()) 
            {
                if (tfod != null) 
                {
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) 
                    {
                        runningOpMode.telemetry.addData("# Object Detected", updatedRecognitions.size());
                        
                        if(updatedRecognitions.size() > 0)
                        {
                            if(updatedRecognitions.get(0).getLabel().equals("4 ringen"))
                                AmountOfRings = 4;
                            else if(updatedRecognitions.get(0).getLabel().equals("1 ring"))
                                AmountOfRings = 1;
                        }
                        else
                            AmountOfRings = 0;
                    
                        runningOpMode.telemetry.addData("AmountOfRings",AmountOfRings);
                        runningOpMode.telemetry.update();
                    }
                }
            }
    
            if (tfod != null) 
                tfod.shutdown();
            
            return AmountOfRings;         
        }
    //---------------------------------------------------------------------
    //Methods
    //---------------------------------------------------------------------          

}

