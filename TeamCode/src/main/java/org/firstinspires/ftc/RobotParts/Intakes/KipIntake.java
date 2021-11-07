package org.firstinspires.ftc.RobotParts.Intakes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class KipIntake {

    public DcMotor MotorKipIntake;
    
    
    public KipIntake(OpMode runningOpMode)
    {
        MotorKipIntake = runningOpMode.hardwareMap.get(DcMotor.class, "Intake");
    }
    public KipIntake(LinearOpMode runningLinearOpMode)
    {
        MotorKipIntake = runningLinearOpMode.hardwareMap.get(DcMotor.class, "Intake");
    }
    
    public void In ()
    {
        MotorKipIntake.setPower(0.3);
    }
    
    public void In (double Power)
    {
        MotorKipIntake.setPower(Power);
    }
    
    public void Out ()
    {
        MotorKipIntake.setPower(-0.3);
    }
    public void Stop()
    {
        MotorKipIntake.setPower(0);        
    }

}