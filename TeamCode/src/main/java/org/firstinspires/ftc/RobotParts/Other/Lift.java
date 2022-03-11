package org.firstinspires.ftc.RobotParts.Other;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Lift {

    public DcMotor Lift;


    public Lift (HardwareMap hardwareMap)
    {
        Lift = hardwareMap.get(DcMotor.class, "EncoderX"); //Yep, we linken een odometry encoder aan een arm omdat we geen porten over hadden. Too bad!
        Lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Lift = hardwareMap.get(DcMotor.class, "Lift");
    }

    public void SlideUp()
    {
        Lift.setPower(0.5);
    }

    public void SlideDown()
    {
        Lift.setPower(-0.5);
    }

    public void StopLift (){
        Lift.setPower(0.0);
    }

}
