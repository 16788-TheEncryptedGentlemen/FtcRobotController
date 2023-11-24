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
        servo.setPosition(0.2);
    }

    public void TiltMechanismDown() {
        servo.setPosition(0.6);
    }

    public void StopTiltMechanism() {
        servo.setPosition(0.0);
    }
}