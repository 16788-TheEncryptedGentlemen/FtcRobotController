package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ShooterV {

    public DcMotorEx MotorL;
    public DcMotorEx MotorR;


    public ShooterV(HardwareMap hardwareMap) {
        MotorL = hardwareMap.get(DcMotorEx.class, "ShooterL");
        MotorL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        MotorL.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        MotorR = hardwareMap.get(DcMotorEx.class, "ShooterR");
        MotorR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        MotorR.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

    }
    public void shootV(double velocity) {
        MotorL.setVelocity(1000);
        MotorR.setVelocity(1000);
    }

    public void StopMotor()
    {
        MotorL.setPower(0.0);
        MotorR.setPower(0.0);

    }
}
