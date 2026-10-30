package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TestShooter extends LinearOpMode {

    public DcMotorEx test_shooter;


    private static final double TARGET_VELOCITY = 1800;
    //private double CALCULATE_VELOCITY =


    public TestShooter(HardwareMap hardwareMap) {
        test_shooter = hardwareMap.get(DcMotorEx.class, "Testshooter");
        test_shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        test_shooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    @Override
    public void runOpMode() throws InterruptedException {

        test_shooter = hardwareMap.get(DcMotorEx.class, "Motor");

        test_shooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        test_shooter.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        test_shooter.setVelocity(TARGET_VELOCITY);

        while (opModeIsActive()) {
            telemetry.addData("velocity", test_shooter.getVelocity());
            telemetry.addData("position", test_shooter.getCurrentPosition());
            telemetry.addData("is at target", !test_shooter.isBusy());
            telemetry.update();
        }

    }

    public void start(double velocity) {
        test_shooter.setVelocity(TARGET_VELOCITY);
    }

    public void stopMotor (){
        test_shooter.setVelocity(0);
    }
}