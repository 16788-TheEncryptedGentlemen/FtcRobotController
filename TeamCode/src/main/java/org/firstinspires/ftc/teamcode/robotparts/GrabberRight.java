package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class GrabberRight {

    public Servo grabberLeft;

    public GrabberRight(HardwareMap hardwareMap) {
        grabberLeft = hardwareMap.get(Servo.class, "grabberLeft");
    }

    public void Grab() {
        grabberLeft.setPosition(0.0);
    }

    public void Grab(double power) {
        grabberLeft.setPosition(-power);
    }

    public void Drop() {
        grabberLeft.setPosition(0.4);
    }

    public void Drop(double power) {
        grabberLeft.setPosition(power);
    }

    public void Stop() {
        grabberLeft.setPosition(0.0);
    }
}