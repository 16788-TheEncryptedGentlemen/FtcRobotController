package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Timer;


public class AutoLift {

    /** The left DCMotor of the lift. */
    public DcMotorEx left;
    /** The right DCMotor of the lift. */
    public DcMotorEx right;
    /** The linear opmode for autonomous. */
    public LinearOpMode runningOpMode;
    /** Timer for measuring lift usage time. */
    private Timer liftTimer;


    /** The position values for levels. */
    private int groundPos = 80;
    private int groundJunctionPos = 700;
    private int lowPolePos = 3800;
    private int midPolePos = -500; // was 5300
    private int highPolePos = 4100;


    /** The lift has a right and left DCMotor. */
    public AutoLift(HardwareMap hardwareMap, LinearOpMode _runningOpMode)
    {
        left = hardwareMap.get(DcMotorEx.class, "LeftLift");
        right = hardwareMap.get(DcMotorEx.class, "RightLift");
        runningOpMode = _runningOpMode;
        liftTimer = new Timer();

        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }


   /** Reusable function for moving lift to a position up or down. */
    public void moveLiftToPosition(int position) {
        int currentPos = -left.getCurrentPosition();
        boolean targetAbove = currentPos < position;   // go up or go down?
        liftTimer.Reset();

        if (targetAbove){ // going up
            while (Math.abs(left.getCurrentPosition()) < position && !runningOpMode.isStopRequested() && liftTimer.getTime() < 3.0){
                left.setPower(-0.6);
                right.setPower(0.6);
            }
        } else { // going down
            while (Math.abs(left.getCurrentPosition()) > position && !runningOpMode.isStopRequested() && liftTimer.getTime() < 3.0){
                left.setPower(0.6);
                right.setPower(-0.6);
            }
        }
        left.setPower(0.0); // stop when reached
        right.setPower(0.0);
    }

    /** The functions for moving lift to a position. */
    public void liftGroundLevel() {
        moveLiftToPosition(groundPos);
    }

    public void liftGroundJunction() {
        moveLiftToPosition(groundJunctionPos);
    }

    public void liftLowPole() {
        moveLiftToPosition(lowPolePos);
    }

    public void liftMidPole() {
        moveLiftToPosition(midPolePos);
    }

    public void liftHighPole() {
        moveLiftToPosition(highPolePos);
    }

    public int currentEnc() {
        return left.getCurrentPosition();
    }


}
