package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;



public class Shooter {

    public DcMotor motorL;
    public DcMotor motorR;
    public CRServo servo1;
    public CRServo servo2;
    public static final double PowerL = 0.20;
    public static final double PowerR = -0.20;

    public Shooter(HardwareMap hardwareMap) {
        motorL = hardwareMap.get(DcMotor.class, "ShooterL");
        motorL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motorL.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motorR = hardwareMap.get(DcMotor.class, "ShooterR");
        motorR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motorR.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        servo1 = hardwareMap.get(CRServo.class, "servo1");
        servo2 = hardwareMap.get(CRServo.class, "servo2");
    }
    public void shoot(double power) {
        motorL.setPower(PowerL);
        motorR.setPower(PowerR);
    }

    public void shootFaster(double power){
        motorL.setPower(1);
        motorR.setPower(-1);
    }

    public void stopMotor()
    {
        motorL.setPower(0.0);
        motorR.setPower(0.0);

    }
    public void DeliverBall(){
        servo1.setPower(-1.0);
        servo2.setPower(-1.0);
    }

    public void ReverseBall(){
        servo1.setPower(1.0);
        servo2.setPower(1.0);
    }

    public void NewBall(){
        servo1.setPower(0);
        servo2.setPower(0);
    }

}
