package org.firstinspires.ftc.RobotParts.Other;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Arm {

    public CRServo Arm;


    public Arm (HardwareMap hardwareMap)
    {
        Arm = hardwareMap.get(CRServo.class, "Arm");
    }

    public void MoveArmFront()
    {
        Arm.setPower(1.0);

    }
    public void MoveArmBack()
    {
        Arm.setPower(-1.0);
    }
    public void StopArm()
    {
        Arm.setPower(0.0);

    }

}
