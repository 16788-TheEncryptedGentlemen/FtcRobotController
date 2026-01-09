package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Odometry {
    /** Counts per rotation of the encoder. */
    public final static int countsPerRotation = 2000;
    /** Diameter of the odometry wheels in cm. */
    public final static double diameterOdometryWheel = 3.2;
    /** Amount of cm that the robot drives in one count. */
    public final static double cmPerCount = (diameterOdometryWheel * Math.PI) / countsPerRotation;

    /** A correction value for the vertical odometry wheel. */
    public final double verticalCorrection = 1.0;
    /** A correction value for the horizontal odometry wheel. */
    public final double horizontalCorrection = 1.0;

    /** The odometry wheel in the horizontal direction. */
    private final DcMotorEx xWheel;
    /** The odometry wheel in the vertical direction. */
    private final DcMotorEx yWheel;


    /**
     * Constructor for {@link Odometry}.
     *
     * @param hardwareMap The hardware map of the robot.
     */
    public Odometry(HardwareMap hardwareMap) {
        xWheel = hardwareMap.get(DcMotorEx.class, "CoreHexMotor");
        yWheel = hardwareMap.get(DcMotorEx.class, "IntakeMotor ");

        reset();

        /** Reverses yWheel, because the encoder is mounted in reverse. */
//        yWheel.setDirection(DcMotorEx.Direction.FORWARD);
    }

    /** Returns the x position of the odometry. */
    public double getX() {
        // Minus cmPerCount because the values are revered. Drive to left= - and drive to right = +.
        return -cmPerCount * xWheel.getCurrentPosition() * horizontalCorrection;
    }

    /** Returns the y position of the odometry. */
    public double getY() {
        return cmPerCount * yWheel.getCurrentPosition() * verticalCorrection;
    }

    /** Gets distance from the original starting position. */
    public double getDistanceFromStart() {
        /** Use Pythagoras */
        return Math.hypot(getY(), getX());
    }

    /** Resets the encoders. */
    public void reset() {
        xWheel.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        yWheel.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        xWheel.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        yWheel.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }
}