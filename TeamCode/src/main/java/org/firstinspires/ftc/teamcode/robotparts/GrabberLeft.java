package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class GrabberLeft {

    public Servo grabberLeft;

    public GrabberLeft(HardwareMap hardwareMap) {
        grabberLeft = hardwareMap.get(Servo.class, "grabberLeft");
    }

    public void Grab() {
        grabberLeft.setPosition(0.0);
    }

    public void Grab(double power) {
        grabberLeft.setPosition(-power);
    }

    public void drop() {
        grabberLeft.setPosition(0.4);
    }

    public void drop(double power) {
        grabberLeft.setPosition(power);
    }

    public void Stop() {
        grabberLeft.setPosition(0.0);
    }
}