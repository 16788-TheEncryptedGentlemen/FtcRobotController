package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class SliderGrabber2 {
    private int position = 0;
    private int offset = 0;
    private boolean stop = false;
    private final DcMotorEx motorSlider;


    public SliderGrabber2(HardwareMap hardwareMap) {
        // Create slider motor.
        motorSlider = hardwareMap.get(DcMotorEx.class, "Slider");

        // Set accuracy of position.
        motorSlider.setTargetPositionTolerance(5);

        motorSlider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        // onzin van bram
        motorSlider.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);


        /** Run all motors with encoders. */
        motorSlider.setTargetPosition(0);
        motorSlider.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    public void MoveSliderOut() {
        motorSlider.setPower(1.0);
        motorSlider.setTargetPosition(position+offset);
        stop = false;
        position++;
    }

    public void MoveSliderIn() {
        motorSlider.setPower(1.0);
        motorSlider.setTargetPosition(position+offset);
        stop = false;
        position--;
    }

    public void StopSlider() {
        motorSlider.setPower(0.0);
    }

    public void SliderReset(){
        offset = motorSlider.getCurrentPosition();
    }

    public void AutoSliderInPosition(){
        motorSlider.setPower(0.3);
        motorSlider.setTargetPosition(170);
    }

    public void SliderToInPosition(){
        motorSlider.setPower(1.0);
        position = -9;
        motorSlider.setTargetPosition(position);
    }
    public void SliderToOutPosition(){
        motorSlider.setPower(1.0);
        position = 40 ;
        motorSlider.setTargetPosition(position);
    }
    public void SliderToRestPosition(){
        motorSlider.setPower(1.0);
        position = 10;
        motorSlider.setTargetPosition(position);
    }

    public boolean isStopped() {
        return stop;
    }
}