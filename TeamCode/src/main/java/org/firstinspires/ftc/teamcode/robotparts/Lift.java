package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

// TODO: Test it!

/**
 * Lift with two DC motors. The lift is moved in a start/stop fashion in driver
 * controlled and uses preset positions in autonomous.
 */
public class Lift {
    /**
     * The lowest position of the lift. This is not zero to prevent overshoot.
     */
    public static final int FLOOR_POSITION = 10;

    /**
     * The position of the ground junction.
     */
    public static final int GROUND_JUNCTION_POSITION = 200;

    /**
     * The position of the low junction.
     */
    public static final int LOW_JUNCTION_POSITION = 400;

    /**
     * The position of the medium junction.
     */
    public static final int MEDIUM_JUNCTION_POSITION = 600;

    /**
     * The position of the high junction.
     */
    public static final int HIGH_JUNCTION_POSITION = 800;

    /**
     * The left DCMotor of the lift.
     */
    public DcMotorEx left;

    /**
     * The right DCMotor of the lift.
     */
    public DcMotorEx right;

    /**
     * Constructor for the lift.
     */
    public Lift(HardwareMap hardwareMap, boolean autonomous) {
        left = hardwareMap.get(DcMotorEx.class, "LeftLift");
        right = hardwareMap.get(DcMotorEx.class, "RightLift");

        if (autonomous) {
            left.setTargetPosition(0);
            left.setDirection(DcMotorEx.Direction.REVERSE);
            left.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            left.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            right.setTargetPosition(0);
            right.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
            right.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        } else {
            left.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            right.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        }
    }

    /**
     * Start moving the lift upwards.
     */
    public void up() {
        left.setPower(1.0);
        right.setPower(-1.0);
    }

    /**
     * Start moving the lift downwards.
     */
    public void down() {
        left.setPower(-1.0);
        right.setPower(1.0);
    }

    /**
     * Stop the lift.
     */
    public void stop() {
        left.setPower(0.0);
        right.setPower(0.0);
    }

    /**
     * Move the lift to the floor position.
     */
    public void toFloor() {
        left.setTargetPosition(FLOOR_POSITION);
        right.setTargetPosition(FLOOR_POSITION);
    }

    /**
     * Move the lift to the ground junction position.
     */
    public void toGroundJunction() {
        left.setTargetPosition(GROUND_JUNCTION_POSITION);
        right.setTargetPosition(GROUND_JUNCTION_POSITION);
    }

    /**
     * Move the lift to the low junction position.
     */
    public void toLowJunction() {
        left.setTargetPosition(LOW_JUNCTION_POSITION);
        right.setTargetPosition(LOW_JUNCTION_POSITION);
    }

    /**
     * Move the lift to the medium junction position.
     */
    public void toMediumJunction() {
        left.setTargetPosition(MEDIUM_JUNCTION_POSITION);
        right.setTargetPosition(MEDIUM_JUNCTION_POSITION);
    }

    /**
     * Move the lift to the high junction position.
     */
    public void toHighJunction() {
        left.setTargetPosition(HIGH_JUNCTION_POSITION);
        right.setTargetPosition(HIGH_JUNCTION_POSITION);
    }
}
