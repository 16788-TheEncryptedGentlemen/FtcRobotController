package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ShooterV extends LinearOpMode {
    public DcMotorEx MotorL;
    public DcMotorEx MotorR;

    public ShooterV(HardwareMap hardwareMap) {
        MotorL = hardwareMap.get(DcMotorEx.class, "ShooterL");
        MotorL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        MotorL.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        MotorR = hardwareMap.get(DcMotorEx.class, "ShooterR");
        MotorR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        MotorR.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

    }


    @Override
    public void runOpMode() throws InterruptedException {

        MotorL = hardwareMap.get(DcMotorEx.class, "Motor");
        MotorR = hardwareMap.get(DcMotorEx.class, "Motor");

        // Reset the encoder during initialization
        MotorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MotorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        // Switch to RUN_TO_POSITION mode
        MotorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        MotorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Start the MotorL moving by setting the max velocity to 200 ticks per second
        MotorL.setVelocity(200);
        MotorR.setVelocity(200);

        // While the Op Mode is running, show the MotorL's status via telemetry
        while (opModeIsActive()) {
            telemetry.addData("velocity", MotorL.getVelocity());
            telemetry.addData("position", MotorL.getCurrentPosition());
            telemetry.addData("is at target", !MotorL.isBusy());
            telemetry.update();
        }


    }

    public void shootV(double velocity) {
        //todo: check these values
        MotorL.setVelocity(1500);
        MotorR.setVelocity(-1500);
    }

    public void StopMotor()
    {
        MotorL.setPower(0.0);
        MotorR.setPower(0.0);

    }
}

