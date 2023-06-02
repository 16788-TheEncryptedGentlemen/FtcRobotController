package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;


public class Lift {

    /** The left DCMotor of the lift. */
    public DcMotorEx left;
    /** The right DCMotor of the lift. */
    public DcMotorEx right;
    public TouchSensor touchSensor;
    private static final double LOWJUNCTION = 2200;
    private static final double MEDIUMJUNCTION = 3200;
    private static final double HIGHJUNCTION = 4000;
    private static final double DEFAULTPOWER = 0.6;


    /** The lift has a right and left DCMotor. */
    public Lift(HardwareMap hardwareMap)
    {
        left = hardwareMap.get(DcMotorEx.class, "leftLift");
        right = hardwareMap.get(DcMotorEx.class, "rightLift");
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        touchSensor = hardwareMap.touchSensor.get("touchSensor");
    }

    /** The robot lift goes up. */
    public void up() {
        up(DEFAULTPOWER);
    }
    public void up(double power)
    {
        left.setPower(-power);
        right.setPower(power);
        if (touchSensor.isPressed())  {
            resetPosition();
        }
    }

    /** The robot lift down. */
    public void down() {
        down(DEFAULTPOWER);
    }
    public void down(double power)
    {
        left.setPower(power);
        right.setPower(-power);
    }

   /** The robot lift stops. */
    public void stop()
    {
        left.setPower(0.0);
        right.setPower(0.0);
    }

    public int getPosition(){
        return right.getCurrentPosition();
    }

    public void resetPosition(){
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /** Reusable function for moving lift to a position up or down. */
    public boolean goToPosition(double destination){
        if(Math.abs(getPosition() - destination) < 50) {
            stop();
            return true;
        }
        double power = DEFAULTPOWER;
        if(Math.abs(getPosition() - destination) < 400) {
            // if we're close, slow down
            power = 0.3;
        }
        if(getPosition() > destination){
            down(power);
        } else{
            up(power);
        }
        return false;
    }

    /** The function for moving lift to a position. */
    public boolean goToLowJunction(){
        return goToPosition(LOWJUNCTION);
    }
    public boolean goToMediumJunction(){
        return goToPosition(MEDIUMJUNCTION);
    }
    public boolean goToHighJunction(){
        return goToPosition(HIGHJUNCTION);
    }
}


