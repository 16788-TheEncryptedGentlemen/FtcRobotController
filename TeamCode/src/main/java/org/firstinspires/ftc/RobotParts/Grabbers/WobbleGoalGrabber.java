package org.firstinspires.ftc.RobotParts.Grabbers;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class WobbleGoalGrabber {

    public DcMotor Arm;
    public Servo Grabber;
    
    public WobbleGoalGrabber(HardwareMap hardwareMap)
    {
        Arm = hardwareMap.get(DcMotor.class, "EncoderX"); //Yep, we linken een odometry encoder aan een arm omdat we geen porten over hadden. Too bad!
        Arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODERS);  
        Grabber = hardwareMap.get(Servo.class, "Grabber");
    }
    
    public void GrabWobbleGoal()
    {
        Grabber.setPosition(0.6);
    }
    
    public void DropWobbleGoal()
    {
        Grabber.setPosition(1.0);
    }    
}
