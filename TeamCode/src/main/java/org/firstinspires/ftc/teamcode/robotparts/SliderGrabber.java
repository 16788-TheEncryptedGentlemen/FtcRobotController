package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class SliderGrabber {
    /** Servo of the grabber.*/
    public DcMotorEx motorSlider;

    public SliderGrabber(HardwareMap hardwareMap) {
        motorSlider = hardwareMap.get(DcMotorEx.class, "Slider");    }

    public void out() {
       motorSlider.setTargetPosition(100);
    }

    public void in() {
        motorSlider.setTargetPosition(0);
    }

    private void ToPosition(int sliderPosition) {
        motorSlider.setTargetPosition(sliderPosition);
    }
}
