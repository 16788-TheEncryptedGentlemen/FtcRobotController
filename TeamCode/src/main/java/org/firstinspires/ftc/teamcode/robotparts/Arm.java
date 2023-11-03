package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Arm {

    public DcMotorEx Arm;


    public Arm (HardwareMap hardwareMap)
    {
        Arm = hardwareMap.get(DcMotorEx.class, "Arm");
    }

    public void MoveArmUp()
    {
        Arm.setPower(0.3);
    }
    public void MoveArmDown()
    {
        Arm.setPower(-0.3);
    }
    public void StopArm()
    {
        Arm.setPower(0.0);

    }

}
