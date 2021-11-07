package org.firstinspires.ftc.RobotParts.Grabbers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class KipRobotGrabber 

{

    public DcMotor MotorKipGrabber;
    public Servo ServoKipGrabber;
    
    
    
    public KipRobotGrabber (OpMode runningOpMode)
    {
        MotorKipGrabber = runningOpMode.hardwareMap.get(DcMotor.class, "ArmMotor");
        ServoKipGrabber = runningOpMode.hardwareMap.get(Servo.class,"Grabber");
         
    }
    public KipRobotGrabber (LinearOpMode runningLinearOpMode)
    {
        MotorKipGrabber = runningLinearOpMode.hardwareMap.get(DcMotor.class, "ArmMotor");
        ServoKipGrabber = runningLinearOpMode.hardwareMap.get(Servo.class,"Grabber");
    }
    


    public void Open ()
    {
        ServoKipGrabber.setPosition(0);
    }
    public void Close ()
    {
        ServoKipGrabber.setPosition(0.5);
    }
    
    public void MoveIn ()
    {
        MotorKipGrabber.setPower(0.3);
    }
    public void MoveOut ()
    {
        MotorKipGrabber.setPower(-0.3);
    }
    public void Stop()
    {
       MotorKipGrabber.setPower(0.0);
    }
    

}