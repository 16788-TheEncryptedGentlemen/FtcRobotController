package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;



public class Shooter {

    public DcMotor motorL;
    public DcMotor motorR;
    public Servo servo1;
    public Servo servo2;

    public static final double PowerL = 0.75;
    public static final double PowerR = -0.75;

    public Shooter(HardwareMap hardwareMap) {
        motorL = hardwareMap.get(DcMotor.class, "ShooterL");
        motorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorR = hardwareMap.get(DcMotor.class, "ShooterR");
        motorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        servo1 = hardwareMap.get(Servo.class, "servo1");
        servo2 = hardwareMap.get(Servo.class, "servo2");
    }
    public void shoot(double power) {
        motorL.setPower(PowerL);
        motorR.setPower(PowerR);
    }

    public void stopMotor()
    {
        motorL.setPower(0.0);
        motorR.setPower(0.0);

    }
    public void DeliverBall(){
        servo1.setPosition(1.0);
        //deze 2 moeten nog appart bewogen worden
        servo2.setPosition(-1.0);
    }

    public void NewBall(){
        servo1.setPosition(-1.0);
        servo2.setPosition(-1.0);
    }

}
