package org.firstinspires.ftc.RobotParts.Other;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ColorSensor;
import android.graphics.Color;

public class ColorSensorBasic {

    //---------------------------------------------------------------------
    //Used variabels:
    // * Colorsensor: The actual color sensor object from the ColorSensor class created by FIRST
    // * hsvValues: The array of hsv values
    // * values: The default set of hsv values
    // * SCALE_FACTOR: The scale factor of the values
    //---------------------------------------------------------------------
        public ColorSensorBasic ColorSensorBasic;
        public ColorSensorBasic(HardwareMap hardwareMap){
            hardwareMap.get(ColorSensor.class, "sensor_color_distance");
        }
        public float[] hsvValues = {0F, 0F, 0F};
        public final float[] values = hsvValues;
        public final double SCALE_FACTOR = 255;
    //---------------------------------------------------------------------
    //Used variabels
    //---------------------------------------------------------------------





    //---------------------------------------------------------------------
    //Constructor
//    ---------------------------------------------------------------------
//        public ColorSensorBasic(HardwareMap hardwaremap, String Name)
//        {
//            ColorSensorBasic = hardwaremap.get(ColorSensor.class, Name);
//        }
//    ---------------------------------------------------------------------
    //Constructor
    //--------------------------------------------------------------------- 
    
    
    
    
    
    //---------------------------------------------------------------------
    //Methods:
    // * getHSVvalue(): Gets the hsv value of the color sensor. This can be seen as the brightness of a color. 
    //                  Black has a very low HSV and white has a very high HSV.
    //---------------------------------------------------------------------     
    public double getHSVvalue()
    {
        //TODO: find color values.
//        Color.RGBToHSV((int) (ColorSensor.red() * SCALE_FACTOR),
//                        (int) (ColorSensor.green() * SCALE_FACTOR),
//                        (int) (ColorSensor.blue() * SCALE_FACTOR),
//                        hsvValues);
                        
        return hsvValues[0];
    }
    //---------------------------------------------------------------------
    //Methods
    //---------------------------------------------------------------------   
}
