package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class TriangleDrivetrain
{

    public DcMotor MotorLF;
    public DcMotor MotorRF;
    public DcMotor MotorB;

    public TriangleDrivetrain(OpMode runningOpMode)
    {
        MotorLF = runningOpMode.hardwareMap.dcMotor.get("LF");
        MotorRF = runningOpMode.hardwareMap.dcMotor.get("RF");
        MotorB = runningOpMode.hardwareMap.dcMotor.get("B");

//        MotorKipRobotLV.setDirection(DcMotor.Direction.REVERSE);
//        MotorKipRobotLA.setDirection(DcMotor.Direction.REVERSE);
    }
//    public TriangleDrivetrain(LinearOpMode runningLinearOpMode)
//    {
//        MotorKipRobotLV = runningLinearOpMode.hardwareMap.dcMotor.get("LV");
//        MotorKipRobotRV = runningLinearOpMode.hardwareMap.dcMotor.get("RV");
//        MotorKipRobotLA = runningLinearOpMode.hardwareMap.dcMotor.get("LA");
//
//        MotorKipRobotLV.setDirection(DcMotor.Direction.REVERSE);
//        MotorKipRobotLA.setDirection(DcMotor.Direction.REVERSE);
//    }


    public void calculateMotorValues(double leftX, double leftY, double rightX){

        double LFout;
        double RFout;
        double Bout;

        LFout = (leftY + leftX + (rightX/2))/2;
        RFout = (-leftY + leftX + (rightX/2))/2;
        Bout =  (-leftX + (rightX/2))/2;

        setPowers(LFout, RFout, Bout);

    }

    public void setPowers(double LF, double RF, double B){
        MotorLF.setPower(LF);
        MotorRF.setPower(RF);
        MotorB.setPower(B);
    }



}