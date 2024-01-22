package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TiltMechanism {
    /** Servo */
    public Servo servo;


    public TiltMechanism(HardwareMap hardwareMap) {
        // Create tilt mechanism servo.
        servo = hardwareMap.get(Servo.class, "TiltMechanism");
    }

    public void TiltMechanismUp() {
        servo.setPosition(0.25);
    }

    public void TiltMechanismDown() {
        servo.setPosition(0.05);
    }

    public void TiltMechanismStartPosition() {
        servo.setPosition(0.25);
    }
}