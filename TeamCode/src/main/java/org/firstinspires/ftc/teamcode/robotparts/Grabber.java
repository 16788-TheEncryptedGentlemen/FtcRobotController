package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Grabber {

    public Servo servo;

    public Grabber(HardwareMap hardwaremap) {
        servo = hardwaremap.get(Servo.class, "Grabber");
    }

    public void grab() {
        servo.setPosition(0.7);
    }

    public void drop() {
        servo.setPosition(0.5);
    }
}
