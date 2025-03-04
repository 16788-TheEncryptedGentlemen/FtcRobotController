package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.otherclasses.Display;

public class MecanumDrivetrain {

    /** The LinearOpMode to start/stop the robot. */ // TODO: Explain what a LinearOpMode is.
    private LinearOpMode runningOpMode;
    /** An array to store the four different motor speeds. */
    public double[] motorSpeed = new double[4]; // TODO: figure out if this needs to be an array.
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

    /** The default MecanumDrivetrain constructor. */ //TODO: Figure out how to simplify this constructor.
    public MecanumDrivetrain(HardwareMap hardwareMap, Odometry _odometry, Imu _imu) {
        odometry = _odometry;
        imu = _imu;
        frontRight = hardwareMap.get(DcMotorEx.class, "FrontRight");
        backRight = hardwareMap.get(DcMotorEx.class, "BackRight");
        frontLeft = hardwareMap.get(DcMotorEx.class, "FrontLeft");
        backLeft = hardwareMap.get(DcMotorEx.class, "BackLeft");

        /** Reversing motors because they are mirrored. */
        frontRight.setDirection(DcMotorEx.Direction.REVERSE);
        backRight.setDirection(DcMotorEx.Direction.REVERSE);
        frontLeft.setDirection(DcMotorEx.Direction.FORWARD);
        backLeft.setDirection(DcMotorEx.Direction.FORWARD);

        /** Sets power of encoders to zero. */ // TODO: figure out why we use this. (Is it so the encoders don"t break and the wheels just roll?)
        frontRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        backRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        frontLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        backLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);

        /** Run all motors with encoders. */
//        frontRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
//        backRight.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
//        frontLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
//        backLeft.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        /** Run all motors without encoders. */
        frontRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        frontLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }


    /** Constructor for MecanumDrivetrain with an LinearOpMode as argument instead of hardwareMap. */
    public MecanumDrivetrain(LinearOpMode _runningOpMode, Odometry _Odometry, Imu _imu) {
        this(_runningOpMode.hardwareMap, _Odometry, _imu);
        runningOpMode = _runningOpMode;
    }

    //TODO: Figure out how to simplify this constructor.
    /** Sets speed values to the MotorSpeed array. The following arguments can be entered:
     4 doubles, 1 double, 1 array. */
    public void setSpeed(double SpeedFrontRight, double SpeedBackRight, double SpeedFrontLeft, double SpeedBackLeft) {
        motorSpeed[0] = SpeedFrontRight;
        motorSpeed[1] = SpeedBackRight;
        motorSpeed[2] = SpeedFrontLeft;
        motorSpeed[3] = SpeedBackLeft;
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
    //TODO: Figure out how to simplify this constructor.
    /** Adds speed values to the MotorSpeed array. The following arguments can be entered:
     4 doubles, 1 double, 1 array. */
    public void addSpeed(double SpeedFrontRight, double SpeedBackRight, double SpeedFrontLeft, double SpeedBackLeft) {
        motorSpeed[0] += SpeedFrontRight;
        motorSpeed[1] += SpeedBackRight;
        motorSpeed[2] += SpeedFrontLeft;
        motorSpeed[3] += SpeedBackLeft;
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
     1 double, 1 array. */ //TODO; figure out if we can make this work without the array.
    public void setPower() {
        frontRight.setPower(motorSpeed[0]);
        backRight.setPower(motorSpeed[1]);
        frontLeft.setPower(motorSpeed[2]);
        backLeft.setPower(motorSpeed[3]);
    }

    /** Sets power to the motors. The following arguments can be entered:
     No arguments. This will cause the motors to be powered with the values in the MotorSpeed array,4 doubles
     1 double, 1 array. */
    public void setPower(double SpeedFrontRight, double SpeedBackRight, double SpeedFrontLeft, double SpeedBackLeft) {
        frontRight.setPower(SpeedFrontRight);
        backRight.setPower(SpeedBackRight);
        frontLeft.setPower(SpeedFrontLeft);
        backLeft.setPower(SpeedBackLeft);
    }

    /** Sets power to the motors. The following arguments can be entered:
     No arguments. This will cause the motors to be powered with the values in the MotorSpeed array,4 doubles
     1 double, 1 array. */
    public void setPower(double Speed) {
        frontRight.setPower(Speed);
        backRight.setPower(Speed);
        frontLeft.setPower(Speed);
        backLeft.setPower(Speed);
    }

    /** Sets power to the motors. The following arguments can be entered:
     No arguments. This will cause the motors to be powered with the values in the MotorSpeed array,4 doubles
     1 double, 1 array. */
    public void setPower(double[] Speedarr) {
        frontRight.setPower(Speedarr[0]);
        backRight.setPower(Speedarr[1]);
        frontLeft.setPower(Speedarr[2]);
        backLeft.setPower(Speedarr[3]);
    }

    /** Stops the motors. */
    public void stop() {
        setPower(0.0);
    }

    /** Multiplies all speed values in the MotorSpeed array with a factor. */
    public void multiplySpeed(double factor) {
        motorSpeed[0] *= factor;
        motorSpeed[1] *= factor;
        motorSpeed[2] *= factor;
        motorSpeed[3] *= factor;
    }

    /** Returns an array of the velocities of the motors. */ // Todo: figure out what is the difference between velocities and  speeds.
    public double[] getVelocities() {
        return new double[]{frontRight.getVelocity(), backRight.getVelocity(), frontLeft.getVelocity(), backLeft.getVelocity()};
    }

    /** Returns an array of the power values of the motors. */ // Todo: figure out what is the difference between powers and  speeds.
    public double[] getPowers() {
        return new double[]{frontRight.getPower(), backRight.getPower(), frontLeft.getPower(), backLeft.getPower()};
    }


    /** Drives the robot forward a certain amount of cm with a given Speed. */
    public void driveStraight(double distance, double power) {
        odometry.reset();

        driveStraightDirect(distance, power);

        setPower(0);
    }

    // Testing to see if the robot can drive backwards.
    public void driveBackwards(double distance, double power) {
        odometry.reset(); // reset odometry to start from the current position

        // set motor power backwards
        setPower(-power);

        while (odometry.getY() > -distance && !runningOpMode.isStopRequested()) {
            runningOpMode.telemetry.addData("Odometry x", odometry.getX());
            runningOpMode.telemetry.addData("Odometry y", odometry.getY());
            runningOpMode.telemetry.update();
        }
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    /** Drives the robot forward a certain amount of cm with a given Speed. */
    public void driveStraightDirect(double distance, double power) {
        double originYPos = odometry.getY();
        double endDistance = Math.abs(distance + originYPos);

        while (Math.abs(odometry.getY()) < endDistance && !runningOpMode.isStopRequested()) {
            setPower(power * Math.signum(distance));
            runningOpMode.telemetry.addData("Odometry x", odometry.getX());
            runningOpMode.telemetry.addData("Odometry y", odometry.getY());
            runningOpMode.telemetry.update();
        }
    }

    /** Start op power en vertraag naar power 0.1 over distance cm. */
    public void driveStraightEnd(double distance, double power) {
        double originYPos = odometry.getY();
        double endDistance = Math.abs(distance + originYPos);

        while (Math.abs(odometry.getY()) < endDistance && !runningOpMode.isStopRequested()) {
            double fraction = (endDistance - Math.abs(odometry.getY()))/endDistance;
            double scaledPower = (power-0.1) * fraction + 0.1;
            setPower(scaledPower * Math.signum(distance));
        }
    }

    /** Sets speed values to the robot for turning a certain amount of degrees in the absolute orientation plane. */
    public void setSpeedValuesTurnRobot(double NewAngle) {
        double[] SpeedValues = imu.getTurnCorrectionValues(NewAngle, 20, 0.3);
        setSpeed(SpeedValues);
    }

    /** Turns the robot to the robot a certain degrees without a while loop. */
    public void turnRobotNoLoop(double NewAngle) {
        setSpeedValuesTurnRobot(NewAngle);
        setPower();
    }

    /** The robot will follow a theoretical line with a specific angle. Note that this method does not loop. */
    public void follow(double NewAngle, double Speed) {
        setSpeedValuesTurnRobot(NewAngle);
        addSpeed(Speed);
        fixMotorSpeedOverflow();
        setPower();
    }

    /** Turns the robot on the absolute orientation plane with a certain angle. */
    public void turnRobotAO(double angle) {
        turnRobotAO(angle, 0.3);
        stop();
    }

    /** Turns the robot on the absolute orientation plane with a certain angle. */
    public void turnRobotAO(double Angle, double speed) {
        while (Math.abs(Angle - imu.getAngle()) > 2 && !runningOpMode.isStopRequested()) {
            double[] SpeedValues = imu.getTurnCorrectionValues(Angle, 20, speed);
            setPower(SpeedValues);
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
        double[] CorrectionFactors = imu.getTurnCorrectionValues(DesiredAngle, 30, 0.8);
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
        fixMotorSpeedOverflow();
        setPower();
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
        setPower(0);
    }


    /** Decreases all values to be under a value of 1 if needed. */
    public void fixMotorSpeedOverflow() {
        double max = 1;
        for (int i = 0; i < 4; i++) {
            if (Math.abs(motorSpeed[i]) > max)
                max = Math.abs(motorSpeed[i]);
        }
        multiplySpeed(1.0 / max);
    }
        /** Sets all values in MotorSpeed to 0 */
   //     public void emptyMotorSpeedValues () {
     //     motorSpeed = new double[4];

}

