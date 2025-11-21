package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class Intake {

    public DcMotor Intake;

    public static final double Power = -1.0;

    public Intake(HardwareMap hardwareMap){
       Intake = hardwareMap.get(DcMotor.class, "IntakeMotor");
       Intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       Intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void IntakeStart(double power) {
        Intake.setPower(Power);
    }
    public void IntakeStop(){
        Intake.setPower(0.0);
    }
}
