package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.HashMap;

public class MechanicDriveTrain {

    /** The LinearOpMode to start/stop the robot. */
    private LinearOpMode runningOpMode;
    /** An array to store the four different motor speeds. */
    public HashMap<DcMotorEx, Double> motorSpeed = new HashMap<>();
    /** Front right motor. */
    public DcMotorEx frontRight;
    /** Back right motor. */
    public DcMotorEx backRight;
    /** Front left motor. */
    public DcMotorEx frontLeft;
    /** Back left motor . */
    public DcMotorEx backLeft;
    /** The odometry of the robot. */
    public Odometry odometry;
    /** The Integrated Measurement Unit of the robot. */
    public Imu imu;

    /** The MechanicDriveTrain constructor. */
    public MechanicDriveTrain(HardwareMap hardwareMap, Odometry _odometry, Imu _imu) {
        odometry = _odometry;
        imu = _imu;

        //#region Map the hardwareMap values.
        frontRight = hardwareMap.get(DcMotorEx.class, "FrontRight");
        backRight = hardwareMap.get(DcMotorEx.class, "BackRight");
        frontLeft = hardwareMap.get(DcMotorEx.class, "FrontLeft");
        backLeft = hardwareMap.get(DcMotorEx.class, "BackLeft");
        //#endregion

        //#region Reversing motors because they are mirrored.
        frontRight.setDirection(DcMotorEx.Direction.REVERSE);
        backRight.setDirection(DcMotorEx.Direction.REVERSE);
        frontLeft.setDirection(DcMotorEx.Direction.FORWARD);
        backLeft.setDirection(DcMotorEx.Direction.FORWARD);
        //#endregion

        //#region Populate the motorSpeed HashMap
        motorSpeed.put(frontRight, (double) 0);
        motorSpeed.put(backRight, (double) 0);
        motorSpeed.put(frontLeft, (double) 0);
        motorSpeed.put(backLeft, (double) 0);
        //#endregion

        setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);

        //#region Run all motors without encoders.
        frontRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        //#endregion
    }

    public void setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior brake) {
        // Sets power of encoders to zero.
        frontRight.setZeroPowerBehavior(brake);
        backRight.setZeroPowerBehavior(brake);
        frontLeft.setZeroPowerBehavior(brake);
        backLeft.setZeroPowerBehavior(brake);
    }


    /** Constructor for MechanicDriveTrain with an LinearOpMode as argument instead of hardwareMap. */
    public MechanicDriveTrain(LinearOpMode _runningOpMode, Odometry _Odometry, Imu _imu) {
        this(_runningOpMode.hardwareMap, _Odometry, _imu);
        runningOpMode = _runningOpMode;
    }

    /** Sets speed values to the MotorSpeed array.
     * @param speedFrontRight Set the speed of the frontRight motor.
     * @param speedBackRight Set the speed of the backRight motor.
     * @param speedFrontLeft Set the speed of the frontLeft motor.
     * @param speedBackLeft Set the speed of the backLeft motor.
     */
    public void setSpeed(double speedFrontRight, double speedBackRight, double speedFrontLeft, double speedBackLeft) {
        motorSpeed.put(frontRight, speedFrontRight);
        motorSpeed.put(backRight, speedBackRight);
        motorSpeed.put(frontLeft, speedFrontLeft);
        motorSpeed.put(backLeft, speedBackLeft);
    }

    /** Sets speed values to the MotorSpeed array.
     * @param speed A single double to set every motor to.
     */
    public void setSpeed(double speed) {
        motorSpeed.put(frontRight, speed);
        motorSpeed.put(backRight, speed);
        motorSpeed.put(frontLeft, speed);
        motorSpeed.put(backLeft, speed);
    }

    /** Sets speed values to the MotorSpeed array.
     * @param speedArray An array of speed values set in the order frontRight, backRight,
     *                   frontLeft, backLeft.
     */
    public void setSpeed(double[] speedArray) {
        motorSpeed.put(frontRight, speedArray[0]);
        motorSpeed.put(backRight, speedArray[1]);
        motorSpeed.put(frontLeft, speedArray[2]);
        motorSpeed.put(backLeft, speedArray[3]);
    }

    /** Adds speed values to the MotorSpeed array.
     * @param speedFrontRight Add to the current speed of the frontRight motor.
     * @param speedBackRight Add to the current speed of the backRight motor.
     * @param speedFrontLeft Add to the current speed of the frontLeft motor.
     * @param speedBackLeft Add to the current speed of the backLeft motor.
     */
    public void addSpeed(double speedFrontRight, double speedBackRight, double speedFrontLeft, double speedBackLeft) {
        motorSpeed.put(frontRight, motorSpeed.get(frontRight) + speedFrontRight);
        motorSpeed.put(backRight, motorSpeed.get(backRight) + speedBackRight);
        motorSpeed.put(frontLeft, motorSpeed.get(frontLeft) + speedFrontLeft);
        motorSpeed.put(backLeft, motorSpeed.get(backLeft) + speedBackLeft);
    }

    /** Adds speed values to the MotorSpeed array.
     *
     */
    public void addSpeed(double speed) {
        motorSpeed.put(frontRight, motorSpeed.get(frontRight) + speed);
        motorSpeed.put(backRight, motorSpeed.get(backRight) + speed);
        motorSpeed.put(frontLeft, motorSpeed.get(frontLeft) + speed);
        motorSpeed.put(backLeft, motorSpeed.get(backLeft) + speed);
    }

    /** Adds speed values to the MotorSpeed array.
     * @param speedArray An array of speed values that get added in the order of frontRight,
     *                   backRight, frontLeft, backLeft.
     */
    public void addSpeed(double[] speedArray) {
        motorSpeed.put(frontRight, motorSpeed.get(frontRight) + speedArray[0]);
        motorSpeed.put(backRight, motorSpeed.get(backRight) + speedArray[1]);
        motorSpeed.put(frontLeft, motorSpeed.get(frontLeft) + speedArray[2]);
        motorSpeed.put(backLeft, motorSpeed.get(backLeft) + speedArray[3]);
    }

    /** Sets power to the motors. Always run this when you update speed values. */
    public void updateMotorPower() {
        frontRight.setPower(motorSpeed.get(frontRight));
        backRight.setPower(motorSpeed.get(backRight));
        frontLeft.setPower(motorSpeed.get(frontLeft));
        backLeft.setPower(motorSpeed.get(backLeft));
    }

    /** Stops the motors and calls {@link MechanicDriveTrain#updateMotorPower()} */
    public void stop() {
        setSpeed(0.0);
        updateMotorPower();
    }

    /** Multiplies all speed values by a factor. */
    public void multiplySpeed(double factor) {
        motorSpeed.put(frontRight, motorSpeed.get(frontRight) * factor);
        motorSpeed.put(backRight, motorSpeed.get(backRight) * factor);
        motorSpeed.put(frontLeft, motorSpeed.get(frontLeft) * factor);
        motorSpeed.put(backLeft, motorSpeed.get(backLeft) * factor);
    }

    /** Drives the robot forward a certain amount of cm with a given Speed. */
    public void driveStraight(double distance, double power) {
        odometry.reset();

        driveStraightDirect(distance, power);

        stop();
    }

    /** Drives the robot forward a certain amount of cm with a given Speed. */
    public void driveStraightDirect(double distance, double power) {
        double originYPos = odometry.getY();
        double endDistance = Math.abs(distance + originYPos);

        while (Math.abs(odometry.getY()) < endDistance && !runningOpMode.isStopRequested()) {
            setSpeed(power * Math.signum(distance));
            runningOpMode.telemetry.addData("Odometry x", odometry.getX());
            runningOpMode.telemetry.addData("Odometry y", odometry.getY());
            runningOpMode.telemetry.update();
            updateMotorPower();
        }
    }

    /** Sets speed values to the robot for turning a certain amount of degrees in the absolute orientation plane. */
    public void setSpeedValuesTurnRobot(double NewAngle) {
        double[] SpeedValues = imu.getTurnCorrectionValues(NewAngle, 20, 0.3);
        setSpeed(SpeedValues);
    }

    /** The robot will follow a theoretical line with a specific angle. Note that this method does not loop. */
    public void follow(double NewAngle, double Speed) {
        setSpeedValuesTurnRobot(NewAngle);
        addSpeed(Speed);
        fixMotorSpeedOverflow();
        updateMotorPower();
    }

    /** Turns the robot on the absolute orientation plane with a certain angle. */
    public void turnRobotAO(double angle) {
        turnRobotAO(angle, -0.3);
        stop();
    }

    /** Turns the robot on the absolute orientation plane with a certain angle. */
    public void turnRobotAO(double Angle, double speed) {
        while (Math.abs(Angle - imu.getAngle()) > 2 && !runningOpMode.isStopRequested()) {
            double[] speedValues = imu.getTurnCorrectionValues(Angle, 10, speed);
            setSpeed(speedValues);
            updateMotorPower();
        }
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
        double[] CorrectionFactors = imu.getTurnCorrectionValues(DesiredAngle, 10, -0.2);
        addSpeed(CorrectionFactors);
    }

    /** powerStrafeValues(): powers robot to strafe in the desired direction. This method has two isomorphic methods:
     - Angle and speed value: Powers the motors to associated values for strafing.
     - Angle, speed and Desired angle: Powers the motor values to associated values for strafing and corrects for a desired angle in the absolute orientation plane. */
    public void powerStrafeValues(double StrafeAngle, double Speed) {
        setStrafeValues(StrafeAngle, Speed);
        updateMotorPower();
    }

    /** powerStrafeValues(): powers robot to strafe in the desired direction. This method has two isomorphic methods:
     - Angle and speed value: Powers the motors to associated values for strafing.
     - Angle, speed and Desired angle: Powers the motor values to associated values for strafing and corrects for a desired angle in the absolute orientation plane. */
    public void powerStrafeValues(double StrafeAngle, double Speed, double DesiredAngle) {
        setStrafeValues(StrafeAngle, Speed, DesiredAngle);
        fixMotorSpeedOverflow();
        updateMotorPower();
    }

    /** Drives the robot sideways a certain amount of cm with a given Power. Positive Distance == right, lookingDirection == absolute front angle*/
    public void strafeStraight(double Distance, double Power, double lookingDirection) {
        odometry.reset();
        double direction = Math.signum(Distance);

        double originXPos = odometry.getX();
        double endDistance = Math.abs(Distance + originXPos);

        while (Math.abs(odometry.getX()) < endDistance && !runningOpMode.isStopRequested()){
              powerStrafeValues(direction*90, Power, lookingDirection);
        }
        setSpeed(0);
        updateMotorPower();
    }


    /** Decreases all values to be under a value of 1 if needed. */
    public void fixMotorSpeedOverflow() {
        double max = 1;
        for (int i = 0; i < motorSpeed.size(); i++) {
            if (Math.abs(motorSpeed.get(motorSpeed.keySet().toArray()[i])) > max)
                max = Math.abs(motorSpeed.get(motorSpeed.keySet().toArray()[i]));
        }
        multiplySpeed(1.0 / max);
    }
        /** Sets all values in MotorSpeed to 0 */
   //     public void emptyMotorSpeedValues () {
     //     motorSpeed = new double[4];

}

