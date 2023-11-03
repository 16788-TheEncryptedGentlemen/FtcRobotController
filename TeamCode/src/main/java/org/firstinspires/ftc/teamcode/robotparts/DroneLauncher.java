package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class DroneLauncher {

    public Servo droneLauncherGrabber;
    public DcMotorEx droneLauncherArm;

    public DroneLauncher(HardwareMap hardwareMap) {
        droneLauncherGrabber = hardwareMap.get(Servo.class, "droneLauncher");
        droneLauncherArm = hardwareMap.get(DcMotorEx.class,"droneLauncher");
    }

    public void MoveBackwards() {
        droneLauncherGrabber.setPosition(0.0);
        droneLauncherArm.setPower(-0.5);
    }

    public void Release() {
        droneLauncherGrabber.setPosition(0.5);
        droneLauncherArm.setPower(0.0);
    }

    public void Stop() {
        droneLauncherGrabber.setPosition(0.0);
        droneLauncherArm.setPower(0.0);
    }
}