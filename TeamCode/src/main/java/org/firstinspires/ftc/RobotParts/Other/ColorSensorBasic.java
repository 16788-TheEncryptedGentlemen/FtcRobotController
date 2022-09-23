package org.firstinspires.ftc.RobotParts.Other;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ColorSensor;

import android.graphics.Color;

public class ColorSensorBasic {

    //---------------------------------------------------------------------
    //Used variables:
    // * ColourSensor: The actual color sensor object from the ColorSensor class created by FIRST
    // * hsvValues: The array of hsv values
    // * SCALE_FACTOR: The scale factor of the values
    //---------------------------------------------------------------------
    public ColorSensor colourSensor;
    public float[] hsvValues = {0F, 0F, 0F};
    public final double SCALE_FACTOR = 255;

    //---------------------------------------------------------------------
    //Constructor
    //---------------------------------------------------------------------
    public ColorSensorBasic(HardwareMap hardwaremap, String Name) {
        colourSensor = hardwaremap.get(ColorSensor.class, Name);
    }

    //---------------------------------------------------------------------
    //Methods:
    // * getHSVValue(): Gets the hsv value of the color sensor. This can be seen as the brightness of a color.
    //                  Black has a very low HSV and white has a very high HSV.
    //---------------------------------------------------------------------     
    public double getHSVValue() {
        Color.RGBToHSV((int) (colourSensor.red() * SCALE_FACTOR),
                (int) (colourSensor.green() * SCALE_FACTOR),
                (int) (colourSensor.blue() * SCALE_FACTOR),
                hsvValues);

        return hsvValues[0];
    }
}
