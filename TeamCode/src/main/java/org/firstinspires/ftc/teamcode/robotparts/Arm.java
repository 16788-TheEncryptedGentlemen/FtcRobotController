package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;


public class Arm {
    /** De positie */
    int position;

    /** Ben ik al aan het stoppen? */
    boolean ikBenAanHetStoppen = false;
    public DcMotorEx motor;


    public Arm(HardwareMap hardwareMap) {
        // Create arm motor.
        motor = hardwareMap.get(DcMotorEx.class, "Arm");

        // Set accuracy of position.
        // todo: 1 is een mooi getal volgens Jeroen. Controleer of dit klopt!
        motor.setTargetPositionTolerance(11);
    }

    public void MoveArmUp() {
        motor.setPower(0.3);
        ikBenAanHetStoppen = false;

    }

    public void MoveArmDown() {
        motor.setPower(-0.3);
        ikBenAanHetStoppen = false;
    }

    public void StopArm() {
        int currentPosition = motor.getCurrentPosition();
        //de positie waar de arm nu is, blijf in die positie staan
        //als je nu begint met stoppen pas de current position aan.
        // als je al gestopt was, laat de position zoals hij was.
        if (ikBenAanHetStoppen) {
            // hier ben je gestopt.
            // laat de position zoals hij is.
            position = position;
        } else {
            // hier ben jenog niet aan het stoppen.
            ikBenAanHetStoppen = true;
            // nu ben ik wel aan het stoppen.
            position = currentPosition;
            // daar waar ik nu ben, moet ik blijven.
        }
        motor.setTargetPosition(position);

    }
}