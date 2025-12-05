package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;


public class Arm2Motor {

    /**
     * De positie
     */
    public int position = 0;
    public int offset = 0;
    public int SPEED = 3;
    /**
     * Ben ik al aan het stoppen?
     */
    boolean ikBenAanHetStoppen = false;
    public DcMotorEx motor1;
    public DcMotorEx motor2;


    public Arm2Motor(HardwareMap hardwareMap) {
        // Create arm motor.
        motor1 = hardwareMap.get(DcMotorEx.class, "Arm");
        motor2 = hardwareMap.get(DcMotorEx.class, "Arm2");

        // Set accuracy of position.
        // todo: 1 is een mooi getal volgens Jeroen. Controleer of dit klopt! was 11
        motor1.setTargetPositionTolerance(7);
        motor2.setTargetPositionTolerance(7);

        // onzin van bram
        motor1.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        /** Run all motors with encoders. */
        motor1.setTargetPosition(0);
        motor1.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        motor2.setTargetPosition(0);
        motor2.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    public void setPidValues() {
        // Get PID values
        PIDFCoefficients pid = motor1.getPIDFCoefficients(DcMotorEx.RunMode.RUN_TO_POSITION);
        // Halve p value
        pid.p = pid.p/2;
        // Restore PID values
        motor1.setPIDFCoefficients(DcMotorEx.RunMode.RUN_TO_POSITION, pid);
        motor2.setPIDFCoefficients(DcMotorEx.RunMode.RUN_TO_POSITION, pid);
    }

    // Move the arm the up.
    public void MoveArmUp() {
        motor1.setPower(1.0);
        motor2.setPower(1.0);
        motor1.setTargetPosition(position + offset);
        motor2.setTargetPosition(position+offset);
        ikBenAanHetStoppen = false;
        position+=SPEED;
    }

    // Move the arm the down.
    public void MoveArmDown() {
        motor1.setPower(1.0);
        motor2.setPower(1.0);
        motor1.setTargetPosition(position + offset);
        motor2.setTargetPosition(position + offset);
        ikBenAanHetStoppen = false;
        position-=SPEED;
    }

    // Stop the arm.
    public void StopArm() {
        motor1.setPower(1.0);
        motor2.setPower(1.0);
        if (ikBenAanHetStoppen) {
            //Setting the new target position
            motor1.setTargetPosition(position + offset);
            motor2.setTargetPosition(position + offset);
        } else {
            position = motor1.getCurrentPosition();
            position = motor2.getCurrentPosition();
            ikBenAanHetStoppen = true;
        }
    }

    public void ArmReset() {
        offset = motor1.getCurrentPosition();
        offset = motor2.getCurrentPosition();
    }

    public void ToPosition(int position) {
        motor1.setPower(1.0);
        motor2.setPower(1.0);
        this.position = position;
        motor1.setTargetPosition(position + offset);
        motor2.setTargetPosition(position + offset);
    }
}