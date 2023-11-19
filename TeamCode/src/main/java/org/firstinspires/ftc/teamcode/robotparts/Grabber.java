package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Grabber {

    public Servo servo;

    public Grabber(HardwareMap hardwaremap) {
        servo = hardwaremap.get(Servo.class, "Grabber");
    }

    public void Grab() {
        servo.setPosition(-0.4);
    }

    public void Grab(double power) {
        servo.setPosition(-power);
    }

    public void Drop() {
        servo.setPosition(0.4);
    }

    public void Drop(double power) {
        servo.setPosition(power);
    }

    public void Stop() {
        servo.setPosition(0.0);
    }
}