package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class FlowerArm {

    public DcMotorEx ArmMotor;
    public Servo Grabber;

    private int groundPos = 0/*some number here*/ ;
    private int airPos = 365/*some sumber here*/ ;
    private double open = 0/*some number here*/ ;
    private double closed = 0.5/*some number here*/ ;

    public FlowerArm (HardwareMap hardwareMap){
        ArmMotor = hardwareMap.get(DcMotorEx.class, "ArmMotor");
        ArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ArmMotor.setTargetPosition(0);
        ArmMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ArmMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Grabber = hardwareMap.get(Servo.class, "Grabber");
    }

    public void moveArmToTop(){
        ArmMotor.setTargetPosition(airPos);
        ArmMotor.setPower(0.3);
    }
    public void moveArmToBotom(){
        ArmMotor.setTargetPosition(groundPos);
        ArmMotor.setPower(0.3);
    }
    public void openBallGrabber(){
        Grabber.setPosition(open);
    }
    public void closeBallGrabber(){
        Grabber.setPosition(closed);
    }

}
