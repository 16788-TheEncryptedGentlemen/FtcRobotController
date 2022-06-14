package org.firstinspires.ftc.RobotParts.Other;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Arm {

    public CRServo Arm;


    public Arm (HardwareMap hardwareMap)
    {
        Arm = hardwareMap.get(CRServo.class, "Arm");
    }

    public void MoveArmFront()
    {
        Arm.setPower(0.9);

    }
    public void MoveArmBack()
    {
        Arm.setPower(-0.9);
    }
    public void StopArm()
    {
        Arm.setPower(0.0);

    }

}


/*public class Arm {

    public Servo Arm;


    public Arm (HardwareMap hardwareMap)
    {
        Arm = hardwareMap.get(Servo.class, "Arm");
    }

    public void MoveArmFront()
    {
        Arm.setPosition(0.9);

    }
    public void MoveArmBack()
    {
        Arm.setPosition(0.0);
    }
}
 */
