package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

// TODO: Make sure this program works!!!! Make different height for the different junctions.
// TODO: Test It!

public class SingleMotorLift {
    /** The power with which the lift goes up. */
    private static final double UP = 1.0;
    /** The power with which the lift goes down. */
    private static final double DOWN = -1.0;
    /** The DCMotor of the lift. */
    public DcMotorEx lift;

    /** The lift has a DCMotor. */
    public SingleMotorLift(HardwareMap hardwareMap)
    {
        lift = hardwareMap.get(DcMotorEx.class, "Lift");
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift.setDirection(DcMotorEx.Direction.REVERSE);
    }

    /** The robot lift goes up. */
    public void up()
    {
        lift.setPower(UP);
    }

    /** The robot lift goes down. */
    public void down()
    {
        lift.setPower(DOWN);
    }

    /** The robot lift stops. */
    public void stop()
    {
        lift.setPower(0.0);
     }
}
