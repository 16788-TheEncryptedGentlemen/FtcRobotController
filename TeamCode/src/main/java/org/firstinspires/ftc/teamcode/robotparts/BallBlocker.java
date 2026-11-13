package org.firstinspires.ftc.teamcode.robotparts;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class BallBlocker {

    public Servo BallBlocker;

    public double open = 0;
    public double closed = 0.5;

    public BallBlocker(HardwareMap hardwareMap){
        BallBlocker = hardwareMap.get(Servo.class, "BallBlocker");
    }

    public void blockBall(){
        BallBlocker.setPosition(closed);
    }

    public void unblockBall(){
        BallBlocker.setPosition(open);
    }
}
