package org.firstinspires.ftc.RobotParts.Drivetrains;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class KipDrivetrain 
{

    public DcMotor MotorKipRobotLV;
    public DcMotor MotorKipRobotRV;
    public DcMotor MotorKipRobotLA;
    public DcMotor MotorKipRobotRA;
    
    
     public KipDrivetrain(OpMode runningOpMode)
    {
        MotorKipRobotLV = runningOpMode.hardwareMap.dcMotor.get("LV");
        MotorKipRobotRV = runningOpMode.hardwareMap.dcMotor.get("RV");
        MotorKipRobotLA = runningOpMode.hardwareMap.dcMotor.get("LA");
        MotorKipRobotRA = runningOpMode.hardwareMap.dcMotor.get("RA");
        
        MotorKipRobotLV.setDirection(DcMotor.Direction.REVERSE);
        MotorKipRobotLA.setDirection(DcMotor.Direction.REVERSE);
    }
    public KipDrivetrain(LinearOpMode runningLinearOpMode)
    {
        MotorKipRobotLV = runningLinearOpMode.hardwareMap.dcMotor.get("LV");
        MotorKipRobotRV = runningLinearOpMode.hardwareMap.dcMotor.get("RV");
        MotorKipRobotLA = runningLinearOpMode.hardwareMap.dcMotor.get("LA");
        MotorKipRobotRA = runningLinearOpMode.hardwareMap.dcMotor.get("RA");
        
        MotorKipRobotLV.setDirection(DcMotor.Direction.REVERSE);
        MotorKipRobotLA.setDirection(DcMotor.Direction.REVERSE);
    }
    
    
    
    public void Vooruit()
    {
        MotorKipRobotLA.setPower(0.6);
        MotorKipRobotLV.setPower(0.6);
        MotorKipRobotRV.setPower(0.6);
        MotorKipRobotRA.setPower(0.6);
    }
    
     public void Achteruit()
    {
        MotorKipRobotLA.setPower(-0.6);
        MotorKipRobotLV.setPower(-0.6);
        MotorKipRobotRV.setPower(-0.6);
        MotorKipRobotRA.setPower(-0.6);
    }
    
    
     public void Rechts()
    {
        MotorKipRobotLA.setPower(-0.6);
        MotorKipRobotLV.setPower(0.6);
        MotorKipRobotRV.setPower(-0.6);
        MotorKipRobotRA.setPower(0.6);
    }

    
     public void Links()
    {
        MotorKipRobotLA.setPower(0.6);
        MotorKipRobotLV.setPower(-0.6);
        MotorKipRobotRV.setPower(0.6);
        MotorKipRobotRA.setPower(-0.6);
    }
     
     
     public void setPower(double Power)
     {
        MotorKipRobotLA.setPower(Power);
        MotorKipRobotLV.setPower(Power);
        MotorKipRobotRV.setPower(Power);
        MotorKipRobotRA.setPower(Power);
     }
       
       public void Strafe(double Power)
    {
        MotorKipRobotLA.setPower(-Power);
        MotorKipRobotLV.setPower(Power);
        MotorKipRobotRV.setPower(-Power);
        MotorKipRobotRA.setPower(Power);
    }
    
    public void Stop()
    {
        MotorKipRobotLA.setPower(0);
        MotorKipRobotLV.setPower(0);
        MotorKipRobotRV.setPower(0);
        MotorKipRobotRA.setPower(0);        
    }

     
}