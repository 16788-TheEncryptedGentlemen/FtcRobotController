package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class DroneLauncher {
    /** Servo of the drone launcher. */
    public Servo servo;

    public DroneLauncher(HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, "droneLauncher");
    }
    // Launches the drone.
    public void launch() {
        servo.setPosition(1.0);
    }
    // Returns the servo to original position.
    public void reverse () {
        servo.setPosition(0.4);
    }

}