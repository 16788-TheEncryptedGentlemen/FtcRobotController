package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

// TODO: Aditi: Add fullstops to documentation and make sure spaces are correct!!!

public class Lift {

    /** The left DCMotor of the lift */
    public DcMotorEx left;
    /** The right DCMotor of the lift */
    public DcMotorEx right;

    //TODO: Aditi: change names for conficureration so that the first letter is a lowercase letter.

    /** The lift has a right and left DCMotor */
    public Lift(HardwareMap hardwareMap)
    {
        left = hardwareMap.get(DcMotorEx.class, "LeftLift");
        right = hardwareMap.get(DcMotorEx.class, "RightLift");
    }

    /** The robot lift closes */
    public void up()
    {
        left.setPower(1);
        right.setPower(-1);
    }
    /** The robot lift open */
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

}

// TODO: Aditi: Add documentation to this class