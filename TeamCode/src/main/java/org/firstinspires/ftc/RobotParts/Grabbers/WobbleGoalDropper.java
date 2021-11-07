package org.firstinspires.ftc.RobotParts.Grabbers;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class WobbleGoalDropper {
    
    public Servo wobbleGoalDropper;
    LinearOpMode runningOpmode;
    
    public WobbleGoalDropper(LinearOpMode _runningOpmode)
    {
        runningOpmode = _runningOpmode;
        wobbleGoalDropper = runningOpmode.hardwareMap.get(Servo.class, "Drop");
    }
    
    public void Hold()
    {
        wobbleGoalDropper.setPosition(0.55);
    }
    
    public void Drop()
    {
        wobbleGoalDropper.setPosition(1);
    }
    
}
