package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Grabber {
    /** Servo of the grabber.*/
    public Servo servo;

    public Grabber(HardwareMap hardwaremap) {
        servo = hardwaremap.get(Servo.class, "Grabber");
    }

    // Picks up the pixel.
    public void grab() {
        servo.setPosition(0.8);
    }

    // Drops the pixel.
    public void drop() {
        servo.setPosition(0.2);
    }
}
