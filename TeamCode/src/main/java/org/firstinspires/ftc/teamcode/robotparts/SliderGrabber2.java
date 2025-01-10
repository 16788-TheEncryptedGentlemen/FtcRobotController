package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class SliderGrabber2 {
    /** De positie */
    public int position = 0;
    public int offset = 0;

    /** Ben ik al aan het stoppen? */
    boolean ikBenAanHetStoppen = false;
    public DcMotorEx motorSlider2;


    public SliderGrabber2(HardwareMap hardwareMap) {
        // Create slider motor.
        motorSlider2 = hardwareMap.get(DcMotorEx.class, "Slider");

        // Set accuracy of position.
        // todo: 1 is een mooi getal volgens Jeroen. Controleer of dit klopt! was 11
        motorSlider2.setTargetPositionTolerance(5);

        motorSlider2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        // onzin van bram
        motorSlider2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);


        /** Run all motors with encoders. */
        motorSlider2.setTargetPosition(0);
        motorSlider2.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    public void MoveSliderOut() {
        motorSlider2.setPower(1.0);
        motorSlider2.setTargetPosition(position+offset);
        ikBenAanHetStoppen = false;
        position++;
    }

    public void MoveSliderIn() {
        motorSlider2.setPower(1.0);
        motorSlider2.setTargetPosition(position+offset);
        ikBenAanHetStoppen = false;
        position--;
    }

    public void StopSlider() {
        motorSlider2.setPower(0.0);
    }

    public void SliderReset(){
        offset = motorSlider2.getCurrentPosition();
    }

    public void AutoSliderInPosition(){
        motorSlider2.setPower(0.3);
        motorSlider2.setTargetPosition(170);
    }

    public void SliderToInPosition(){
        motorSlider2.setPower(1.0);
        position = -9;
        motorSlider2.setTargetPosition(position);
    }
    public void SliderToOutPosition(){
        motorSlider2.setPower(1.0);
        position = 40 ;
        motorSlider2.setTargetPosition(position);
    }
    public void SliderToRestPosition(){
        motorSlider2.setPower(1.0);
        position = 10;
        motorSlider2.setTargetPosition(position);
    }

}