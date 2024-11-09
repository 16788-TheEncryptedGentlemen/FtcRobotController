package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class BlockGrabber {
    /** Servo of the grabber.*/
    public Servo servo;

    public BlockGrabber(HardwareMap hardwaremap) {
        servo = hardwaremap.get(Servo.class, "BlockGrabber");
    }

    public void grab() {
       servo.setPosition(0.8);
    }

    public void drop() {
        servo.setPosition(-0.2);
    }
    public void stop(){
        servo.setPosition(0.0);
    }
}
