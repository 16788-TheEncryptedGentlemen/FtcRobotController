package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class Intake {

    private DcMotor intakeMotor;

    public static final double Power = -1.0;
    public static final double POWER_OFF = 0;

    public Intake(HardwareMap hardwareMap){
       intake = hardwareMap.get(DcMotor.class, "IntakeMotor");
       intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void IntakeStart(double power) {
        intake.setPower(Power);
    }
    public void IntakeStop(){
        intake.setPower(0.0);
    }
}
