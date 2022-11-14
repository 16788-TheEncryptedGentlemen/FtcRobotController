package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDrivetrain {

    /** The LinearOpMode to stop the robot. */
    private LinearOpMode runningOpMode;
    /** An array to store the four different motor speeds. */
    public double[] motorSpeed = new double[4];
    /** Front right motor (RechtsVoor). */
    public DcMotorEx rv;
    /** Back right motor (RechtsAchter). */
    public DcMotorEx ra;
    /** Front left motor (LinksVoor). */
    public DcMotorEx lv;
    /** Back left motor (LinksAchter). */
    public DcMotorEx la;
    /** The odometry of the robot. */
    private final Odometry odometry;
    /** The Integrated Measurement Unit of the robot. */
    public IMU imu;


    /** the default MecanumDrivetrain constructor. */
    public MecanumDrivetrain(HardwareMap hardwareMap, Odometry _Odometry, IMU _imu) {
        odometry = _Odometry;
        imu = _imu;
        rv = hardwareMap.get(DcMotorEx.class, "RV");
        ra = hardwareMap.get(DcMotorEx.class, "RA");
        lv = hardwareMap.get(DcMotorEx.class, "LV");
        la = hardwareMap.get(DcMotorEx.class, "LA");

        /** Reversing the left motors because they are mirrored. */
        rv.setDirection(DcMotorEx.Direction.REVERSE);
        ra.setDirection(DcMotorEx.Direction.REVERSE);

        rv.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        ra.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        lv.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        la.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);

        /** Run all motors with encoders. */
        rv.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        ra.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        lv.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        la.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }


    /** Constructor for MecanumDrivetrain with an LinearOpMode as argument instead of hardwareMap. */
    public MecanumDrivetrain(LinearOpMode _runningOpMode, Odometry _Odometry, IMU _imu) {
        this(_runningOpMode.hardwareMap, _Odometry, _imu);
        runningOpMode = _runningOpMode;
    }


    /** Sets speed values to the MotorSpeed array. The following arguments can be entered:
     4 doubles, 1 double, 1 array. */
    public void setSpeed(double SpeedRV, double SpeedRA, double SpeedLV, double SpeedLA) {
        motorSpeed[0] = SpeedRV;
        motorSpeed[1] = SpeedRA;
        motorSpeed[2] = SpeedLV;
        motorSpeed[3] = SpeedLA;
    }

    /** Sets speed values to the MotorSpeed array. The following arguments can be entered:
     4 doubles, 1 double, 1 array. */
    public void setSpeed(double Speed) {
        motorSpeed[0] = Speed;
        motorSpeed[1] = Speed;
        motorSpeed[2] = Speed;
        motorSpeed[3] = Speed;
    }

    /** Sets speed values to the MotorSpeed array. The following arguments can be entered:
     4 doubles, 1 double, 1 array. */
    public void setSpeed(double[] Speedarr) {
        motorSpeed[0] = Speedarr[0];
        motorSpeed[1] = Speedarr[1];
        motorSpeed[2] = Speedarr[2];
        motorSpeed[3] = Speedarr[3];
    }

    /** Adds speed values to the MotorSpeed array. The following arguments can be entered:
    4 doubles, 1 double, 1 array. */
    public void addSpeed(double SpeedRV, double SpeedRA, double SpeedLV, double SpeedLA) {
        motorSpeed[0] += SpeedRV;
        motorSpeed[1] += SpeedRA;
        motorSpeed[2] += SpeedLV;
        motorSpeed[3] += SpeedLA;
    }

    /** Adds speed values to the MotorSpeed array. The following arguments can be entered:
     4 doubles, 1 double, 1 array. */
    public void addSpeed(double Speed) {
        motorSpeed[0] += Speed;
        motorSpeed[1] += Speed;
        motorSpeed[2] += Speed;
        motorSpeed[3] += Speed;
    }

    /** Adds speed values to the MotorSpeed array. The following arguments can be entered:
     4 doubles, 1 double, 1 array. */
    public void addSpeed(double[] Speedarr) {
        motorSpeed[0] += Speedarr[0];
        motorSpeed[1] += Speedarr[1];
        motorSpeed[2] += Speedarr[2];
        motorSpeed[3] += Speedarr[3];
    }

    /** Sets power to the motors. The following arguments can be entered:
    No arguments. This will cause the motors to be powered with the values in the MotorSpeed array,4 doubles
    1 double, 1 array. */
    public void setPower() {
        rv.setPower(motorSpeed[0]);
        ra.setPower(motorSpeed[1]);
        lv.setPower(motorSpeed[2]);
        la.setPower(motorSpeed[3]);
    }

    /** Sets power to the motors. The following arguments can be entered:
     No arguments. This will cause the motors to be powered with the values in the MotorSpeed array,4 doubles
     1 double, 1 array. */
    public void setPower(double SpeedRV, double SpeedRA, double SpeedLV, double SpeedLA) {
        rv.setPower(SpeedRV);
        ra.setPower(SpeedRA);
        lv.setPower(SpeedLV);
        la.setPower(SpeedLA);
    }

    /** Sets power to the motors. The following arguments can be entered:
     No arguments. This will cause the motors to be powered with the values in the MotorSpeed array,4 doubles
     1 double, 1 array. */
    public void setPower(double Speed) {
        rv.setPower(Speed);
        ra.setPower(Speed);
        lv.setPower(Speed);
        la.setPower(Speed);
    }

    /** Sets power to the motors. The following arguments can be entered:
     No arguments. This will cause the motors to be powered with the values in the MotorSpeed array,4 doubles
     1 double, 1 array. */
    public void setPower(double[] Speedarr) {
        rv.setPower(Speedarr[0]);
        ra.setPower(Speedarr[1]);
        lv.setPower(Speedarr[2]);
        la.setPower(Speedarr[3]);
    }

    /** Stops the motors. */
    public void Stop() {
        setPower(0.0);
    }

    /** Multiplies all speed values in the MotorSpeed array with a factor. */
    public void MultiplySpeed(double factor) {
        motorSpeed[0] *= factor;
        motorSpeed[1] *= factor;
        motorSpeed[2] *= factor;
        motorSpeed[3] *= factor;
    }

    /** Returns an array of the velocities of the motors. */
    public double[] getVelocities() {
        return new double[]{rv.getVelocity(), ra.getVelocity(), lv.getVelocity(), la.getVelocity()};
    }

    /** Returns an array of the power values of the motors. */
    public double[] getPowers() {
        return new double[]{rv.getPower(), ra.getPower(), lv.getPower(), la.getPower()};
    }

    /** TODO: Figure out what this means.
     * @param Distance The distance in cm
     * @param Power    Power value in range [0..1]
     */


    /** Drives the robot forward a certain amount of cm with a given Speed. */
    public void DriveStraight(double Distance, double Power) {
        odometry.Reset();

        double OriginYPos = odometry.getY();
        double endDistance = Math.abs(Distance + OriginYPos);

        while (Math.abs(odometry.getY()) < endDistance && !runningOpMode.isStopRequested())
            setPower(Power * Math.signum(Distance));

        setPower(0);
    }

    /** Sets speed values to the robot for turning a certain amount of degrees in the absolute orientation plane. */
    public void setSpeedValuesTurnRobot(double NewAngle) {
        double[] SpeedValues = imu.getTurnCorrectionValues(NewAngle, 20);
        setSpeed(SpeedValues);
    }

    /** Turns the robot to the robot a certain degrees without a while loop. */
    public void TurnRobotNoLoop(double NewAngle) {
        setSpeedValuesTurnRobot(NewAngle);
        setPower();
    }

    /** The robot will follow a theoretical line with a specific angle. Note that this method does not loop. */
    public void Follow(double NewAngle, double Speed) {
        setSpeedValuesTurnRobot(NewAngle);
        addSpeed(Speed);
        FixMotorSpeedOverflow();
        setPower();
    }

    /** Turns the robot on the absolute orientation plane with a certain angle. */
    public void TurnRobotAO(double Angle) {
        while (Math.abs(Angle - imu.getAngle()) > 2 && !runningOpMode.isStopRequested()) {
            double[] SpeedValues = imu.getTurnCorrectionValues(Angle, 20);
            setPower(SpeedValues);
        }
        Stop();
    }


    /** Gets the strafe values of the motors for a certain Angle and speed value. */
    public double[] getStrafeValues(double StrafeAngle, double speed) {
        double MotorSpeedStrafe;

        if (StrafeAngle >= 0 && StrafeAngle <= 90) {
            MotorSpeedStrafe = (speed * (45 - StrafeAngle)) / 45;
            return new double[]{MotorSpeedStrafe, speed, speed, MotorSpeedStrafe};
        } else if (StrafeAngle >= 90 && StrafeAngle <= 180) {
            MotorSpeedStrafe = (speed * (135 - StrafeAngle)) / 45;
            return new double[]{-speed, MotorSpeedStrafe, MotorSpeedStrafe, -speed};
        } else if (StrafeAngle >= -90 && StrafeAngle <= 0) {
            MotorSpeedStrafe = (speed * (45 + StrafeAngle)) / 45;
            return new double[]{speed, MotorSpeedStrafe, MotorSpeedStrafe, speed};
        } else {
            MotorSpeedStrafe = (speed * (135 + StrafeAngle)) / 45;
            return new double[]{MotorSpeedStrafe, -speed, -speed, MotorSpeedStrafe};
        }
    }

    /** sets speed values to robot for strafing. This method has two isomorphic methods:
     - Angle and speed value: Sets the MotorSpeed array values to associated values for strafing.
     - Angle, speed and Desired angle: Sets the MotorSpeed array values to associated values for strafing and corrects for a desired angle in the absolute orientation plane. */
    public void setStrafeValues(double StrafeAngle, double Speed) {
        setSpeed(getStrafeValues(StrafeAngle, Speed));
    }

    /** sets speed values to robot for strafing. This method has two isomorphic methods:
     - Angle and speed value: Sets the MotorSpeed array values to associated values for strafing.
     - Angle, speed and Desired angle: Sets the MotorSpeed array values to associated values for strafing and corrects for a desired angle in the absolute orientation plane. */
    public void setStrafeValues(double StrafeAngle, double Speed, double DesiredAngle) {
        setStrafeValues(StrafeAngle, Speed);
        double[] CorrectionFactors = imu.getTurnCorrectionValues(DesiredAngle, 30);
        addSpeed(CorrectionFactors);
    }

    /** powerStrafeValues(): powers robot to strafe in the desired direction. This method has two isomorphic methods:
    - Angle and speed value: Powers the motors to associated values for strafing.
    - Angle, speed and Desired angle: Powers the motor values to associated values for strafing and corrects for a desired angle in the absolute orientation plane. */
    public void powerStrafeValues(double StrafeAngle, double Speed) {
        setStrafeValues(StrafeAngle, Speed);
        setPower();
    }

    /** powerStrafeValues(): powers robot to strafe in the desired direction. This method has two isomorphic methods:
     - Angle and speed value: Powers the motors to associated values for strafing.
     - Angle, speed and Desired angle: Powers the motor values to associated values for strafing and corrects for a desired angle in the absolute orientation plane. */
    public void powerStrafeValues(double StrafeAngle, double Speed, double DesiredAngle) {
        setStrafeValues(StrafeAngle, Speed, DesiredAngle);
        FixMotorSpeedOverflow();
        setPower();
    }


    /** Decreases all values to be under a value of 1 if needed. */
    public void FixMotorSpeedOverflow() {
        double max = 1;
        for (int i = 0; i < 4; i++) {
            if (Math.abs(motorSpeed[i]) > max)
                max = Math.abs(motorSpeed[i]);
        }
        MultiplySpeed(1.0 / max);
    }

    /** Sets all values in MotorSpeed to 0 */
    public void EmptyMotorSpeedValues() {
        motorSpeed = new double[4];
    }


}
