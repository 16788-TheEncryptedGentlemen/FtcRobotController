package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;



public class Shooter {

    public DcMotor motorL;
    public DcMotor motorR;
    public DcMotor CoreHexMotor;
    public static final double PowerL = 0.20;
    public static final double PowerR = -0.20;

    public Shooter(HardwareMap hardwareMap) {
        motorL = hardwareMap.get(DcMotor.class, "ShooterL");
        motorL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motorL.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        motorR = hardwareMap.get(DcMotor.class, "ShooterR");
        motorR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motorR.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        CoreHexMotor = hardwareMap.get(DcMotor.class, "CoreHexMotor");

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
        CoreHexMotor.setPower(-1.0);
    }

    public void ReverseBall(){
        CoreHexMotor.setPower(1.0);
    }

    public void NewBall(){
        CoreHexMotor.setPower(0);
    }

}
