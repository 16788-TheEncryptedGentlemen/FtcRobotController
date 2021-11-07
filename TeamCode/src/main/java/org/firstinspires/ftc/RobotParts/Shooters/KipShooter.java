package org.firstinspires.ftc.RobotParts.Shooters;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class KipShooter 
{
    public DcMotor MotorKipShooter;
    
    
    
    public KipShooter(OpMode runningOpMode)
    {
        MotorKipShooter = runningOpMode.hardwareMap.dcMotor.get("Shooter");
    }
    public KipShooter(LinearOpMode runningLinearOpMode)
    {
        MotorKipShooter = runningLinearOpMode.hardwareMap.dcMotor.get("Shooter");
    }
    
    
    
    public void Shoot()
    {
        MotorKipShooter.setPower(-0.3);
    }
    public void Stop()
    {
        MotorKipShooter.setPower(0);
    }
}