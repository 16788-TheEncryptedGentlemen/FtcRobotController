package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.CRServo;

public class PixelWheel {

    public CRServo crservo;


    public PixelWheel(HardwareMap hardwareMap) {
       crservo = hardwareMap.get(CRServo.class, "PixelWheel");
    }

    public void PixelWheelIn(){
        crservo.setPower(1.0);
    }

    public void PixelWheelOut() {
        crservo.setPower(-1.0);
    }

    public void PixelWheelStop() {
        crservo.setPower(0.0);
    }
}
