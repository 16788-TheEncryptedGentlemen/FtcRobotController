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
        // Compensate for half a tooth offset
        right.setPosition(0.5 - 2.0/25.0/2);
        left.setPosition(0.5);
    }

    public void release() {
        left.setPosition(0.2);
        right.setPosition(0.8);
    }

    /** Position for when pixel is preloaded on left side of the robot. */
    public void preloadLeft() {
        left.setPosition(0.9);
        right.setPosition(0.8);
    }

    public void releasePreLoadLeft() {
        left.setPosition(0.9);
        right.setPosition(0.9);
    }

    /** Position for when pixel is preloaded on right side of the robot. */
    public void preloadRight() {
        left.setPosition(0.2);
        right.setPosition(0.1);
    }

    public void releasePreLoadRight() {
        left.setPosition(0.1);
        right.setPosition(0.1);
    }
}
