package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Pusher {

    public Servo left;
    public Servo right;

    public Pusher(HardwareMap hardwaremap) {
        left = hardwaremap.get(Servo.class, "PusherLeft");
        right = hardwaremap.get(Servo.class, "PusherRight");
    }

    public void grab() {
        right.setPosition(0.5);
        left.setPosition(0.5);
    }

    public void release() {
        left.setPosition(0.2);
        right.setPosition(0.8);
    }

    /** Position for when pixel is preloaded on left side of the robot. */
    public void preloadLeft() {
        left.setPosition(0.75);
        right.setPosition(1.0);
    }

    /** Position for when pixel is preloaded on right side of the robot. */
    public void preloadRight() {
        left.setPosition(0.0);
        right.setPosition(0.25);
    }

}
