package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ShooterV {
    private final DcMotorEx MotorL;
    private final DcMotorEx MotorR;
    private int revsPerMinute = 1050;
    private boolean on;

    public ShooterV(HardwareMap hardwareMap) {
        MotorL = hardwareMap.get(DcMotorEx.class, "ShooterL");
        MotorL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        MotorL.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        MotorL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        MotorR = hardwareMap.get(DcMotorEx.class, "ShooterR");
        MotorR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        MotorR.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        MotorR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        MotorR.setDirection(DcMotor.Direction.REVERSE);

        on = false;
    }

    public void start() {
        on = true;
        MotorL.setVelocity(revsPerMinute);
        MotorR.setVelocity(revsPerMinute);
    }

    public void stop() {
        on = false;
        MotorL.setPower(0.0);
        MotorR.setPower(0.0);
    }

    public void toggle() {
        if (on) {
            stop();
        } else {
            start();
        }
    }

    public void changeRPM(int revsPerMinute) {
        this.revsPerMinute = revsPerMinute;
    }

    public void faster() {
        revsPerMinute += 5;
    }

    public void slower() {
        revsPerMinute -= 5;
    }
}

