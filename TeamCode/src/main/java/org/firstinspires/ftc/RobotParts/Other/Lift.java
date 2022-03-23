package org.firstinspires.ftc.RobotParts.Other;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Lift {

    public DcMotor Lift;


    public Lift (HardwareMap hardwareMap)
    {
        Lift = hardwareMap.get(DcMotor.class, "Lift");
        Lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void SlideUp()
    {
        Lift.setPower(10.5);
    }

    public void SlideDown()
    {
        Lift.setPower(-0.5);
    }

    public void StopLift (){
        Lift.setPower(0.0);
    }

}
