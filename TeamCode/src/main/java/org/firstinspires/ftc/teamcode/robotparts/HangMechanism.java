package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

//TODO: To be tested + values need to be finetuned.

public class HangMechanism {

    public DcMotor motor;
    public Servo servo;
    private static final double Power = 0.6;

    public HangMechanism(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotor.class, "Lift");
        servo = hardwareMap.get(Servo.class, "Lift");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void open() {
        open(Power);
    }

    public void open(double Power) {
        motor.setPower(Power);
    }

    public void stopMotor()
    {
        motor.setPower(0.0);
    }

    public void block() {
        servo.setPosition(0.5);
    }
}

