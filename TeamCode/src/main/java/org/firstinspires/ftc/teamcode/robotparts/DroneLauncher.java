package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class DroneLauncher {

    public Servo servo;

    public DroneLauncher(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "droneLauncher");
    }

    public void launch() {
        servo.setPosition(0.6);
    }

    public void reverse () {
        servo.setPosition(1.0);
    }

}