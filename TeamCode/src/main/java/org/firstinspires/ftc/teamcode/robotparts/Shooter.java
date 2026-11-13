package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;



public class Shooter {

    public DcMotor motor;
    public DcMotor CoreHexMotor;
    public static final double Power = 0.20;

    public Shooter(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotor.class, "Shooter");
        motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void shoot() {
        motor.setPower(Power);
    }

    public void shootFaster(){motor.setPower(1);}

    public void stopMotor(){motor.setPower(0.0);}
    public void DeliverBall(){
        CoreHexMotor.setPower(-1.0);
    }

    public void ReverseBall(){
        CoreHexMotor.setPower(1.0);
    }

    public void NewBall(){
        CoreHexMotor.setPower(0);
    }

}
