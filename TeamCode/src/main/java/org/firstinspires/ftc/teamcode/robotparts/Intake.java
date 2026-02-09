package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class Intake {

    public DcMotor intakeMotor;

    public static final double POWER = -1.0;

    public Intake(HardwareMap hardwareMap){
        intakeMotor = hardwareMap.get(DcMotor.class, "IntakeMotor");
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void start() {
        intakeMotor.setPower(POWER);
    }

    public void stop() {
        intakeMotor.setPower(0.0);
    }
}
