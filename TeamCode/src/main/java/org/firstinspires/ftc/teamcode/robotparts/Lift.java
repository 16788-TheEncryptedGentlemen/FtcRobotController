package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

// TODO: Make sure this program works!!!! Make different height for the different junctions.
// TODO: Aditi: Add fullstops to documentation and make sure spaces are correct!!!
// TODO: Test It!

public class Lift {

    /** The left DCMotor of the lift */
    public DcMotorEx left;
    /** The right DCMotor of the lift */
    public DcMotorEx right;

    //TODO: Aditi: change names for configureration so that the first letter is a lowercase letter.

    /** The lift has a right and left DCMotor */
    public Lift(HardwareMap hardwareMap, boolean autonomous)
    {
        left = hardwareMap.get(DcMotorEx.class, "LeftLift");
        right = hardwareMap.get(DcMotorEx.class, "RightLift");

        if (autonomous) {
            left.setTargetPosition(0);
            left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            right.setTargetPosition(0);
            right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        } else {
            left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    /** The robot lift goes up */
    public void up()
    {
        left.setPower(1);
        right.setPower(-1);
    }

    /** The robot lift down */
    public void down()
    {
        left.setPower(-1);
        right.setPower(1);
    }

   /** The robot lift stops. */
    public void stop()
    {
        left.setPower(0.0);
        right.setPower(0.0);
    }

    //TODO: ADITI: Make sure all the words except for first word in the function name start with capitals!!!
    public void liftGround() {
        left.setTargetPosition(-10); //not zero because we would overshoot and break something
        right.setTargetPosition(10);
    }
    public void liftLow() {
        left.setTargetPosition(-200);
        right.setTargetPosition(200);
    }
    public void liftLowPole() {
        left.setTargetPosition(-400);
        right.setTargetPosition(400);
    }
    public void liftMidPole() {
        left.setTargetPosition(-600);
        right.setTargetPosition(600);
    }
    public void liftHighPole() {
        left.setTargetPosition(-800);
        right.setTargetPosition(800);
    }
}



// TODO: Aditi: Add documentation to this class
