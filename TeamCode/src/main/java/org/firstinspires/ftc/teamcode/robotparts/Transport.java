package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Transport {
    private final DcMotor CoreHexMotor;
    private static final double POWER = 1.0;

    public Transport(HardwareMap hardwareMap) {
        CoreHexMotor = hardwareMap.get(DcMotor.class, "CoreHexMotor");
        CoreHexMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void deliverBall() {
        CoreHexMotor.setPower(POWER);
    }

    public void reverseBall() {
        CoreHexMotor.setPower(-POWER);
    }

    public void stop() {
        CoreHexMotor.setPower(0.0);
    }
}
