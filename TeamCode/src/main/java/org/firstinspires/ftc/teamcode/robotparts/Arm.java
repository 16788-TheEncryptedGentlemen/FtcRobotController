package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Arm {
    /** De positie */
    public int position = 0;

    /** Ben ik al aan het stoppen? */
    boolean ikBenAanHetStoppen = false;
    public DcMotorEx motor;


    public Arm(HardwareMap hardwareMap) {
        // Create arm motor.
        motor = hardwareMap.get(DcMotorEx.class, "Arm");

        // Set accuracy of position.
        // todo: 1 is een mooi getal volgens Jeroen. Controleer of dit klopt! was 11
        motor.setTargetPositionTolerance(5);

        // onzin van bram
        motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);


        /** Run all motors with encoders. */
        motor.setTargetPosition(0);
        motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    public void MoveArmUp() {
        motor.setPower(0.2);
        motor.setTargetPosition(position);
        ikBenAanHetStoppen = false;
        position++;
    }

    public void MoveArmDown() {
        motor.setPower(0.2);
        motor.setTargetPosition(position);
        ikBenAanHetStoppen = false;
        position--;
    }

    public void StopArm() {
        motor.setPower(0.2);
        if (ikBenAanHetStoppen) {
            motor.setTargetPosition(position);
        } else {
            position = motor.getCurrentPosition();
            ikBenAanHetStoppen = true;
        }




//        int currentPosition = motor.getCurrentPosition();
        //de positie waar de arm nu is, blijf in die positie staan
        //als je nu begint met stoppen pas de current position aan.
        // als je al gestopt was, laat de position zoals hij was.
//        if (ikBenAanHetStoppen) {
            // hier ben je gestopt.
            // laat de position zoals hij is
//            if(currentPosition < position - 25){
//                motor.setPower(0.2);
//            } else if (currentPosition > position + 25){
//                motor.setPower(-0.2);
//            } else {
//                motor.setPower(0.0);
//            }

//            motor.setTargetPosition(position);

//        } else {
            // hier ben jenog niet aan het stoppen.
/*            ikBenAanHetStoppen = true;
            // nu ben ik wel aan het stoppen.
            position = currentPosition;
            motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);*/
            // daar waar ik nu ben, moet ik blijven.
//        }
//        motor.setTargetPosition(position);

    }
}