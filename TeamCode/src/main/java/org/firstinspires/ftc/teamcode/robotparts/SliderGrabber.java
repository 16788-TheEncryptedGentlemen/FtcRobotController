package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class SliderGrabber {
    /** Servo of the grabber.*/
    public DcMotor motorSlider;

    public SliderGrabber(HardwareMap hardwareMap) {
        motorSlider = hardwareMap.get(DcMotor.class, "Slider");
        motorSlider.getTargetPosition();
    }

    public void out() {
       motorSlider.setPower(1.0);
    }

    public void in() {
        motorSlider.setPower(-1.0);
    }

    private void ToPosition(int sliderPosition) {
        motorSlider.setTargetPosition(sliderPosition);
    }
}
