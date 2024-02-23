package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Arm {
    /** De positie */
    public int position = 0;
    public int offset = 0;

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
    // Move the arm the up.
    public void MoveArmUp() {
        motor.setPower(1.0);
        motor.setTargetPosition(position+offset);
        ikBenAanHetStoppen = false;
        position++;
    }

    // Move the arm the down.
    public void MoveArmDown() {
        motor.setPower(1.0);
        motor.setTargetPosition(position+offset);
        ikBenAanHetStoppen = false;
        position--;
    }

    // Stop the arm.
    public void StopArm() {
        motor.setPower(1.0);
        if (ikBenAanHetStoppen) {
            motor.setTargetPosition(position+offset);
        } else {
            position = motor.getCurrentPosition();
            ikBenAanHetStoppen = true;
        }
    }

    public void ArmReset(){
        offset = motor.getCurrentPosition();
    }

    public void AutoArmToBoardPosition(){
        motor.setPower(0.3);
        motor.setTargetPosition(170);
    }

    public void ArmToLowestPosition(){
        motor.setPower(1.0);
        position = 0;
        motor.setTargetPosition(position+offset);
    }
    public void ArmToNeutralPosition(){
        motor.setPower(1.0);
        position = 60 ;
        motor.setTargetPosition(position+offset);
    }
    public void ArmToStageDoorPosition(){
        motor.setPower(1.0);
        position = 90 ;
        motor.setTargetPosition(position+offset);
    }

}