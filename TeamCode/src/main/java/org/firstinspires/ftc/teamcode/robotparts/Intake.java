package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class Intake {

    private DcMotor intakeMotor;

    public static final double POWER = -1.0;

    public Intake(HardwareMap hardwareMap){
        intakeMotor = hardwareMap.get(DcMotor.class, "IntakeMotor");
        intakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void IntakeStart() {
        intakeMotor.setPower(POWER);
    }
    public void IntakeStop(){
        intakeMotor.setPower(0.0);
    }
}
