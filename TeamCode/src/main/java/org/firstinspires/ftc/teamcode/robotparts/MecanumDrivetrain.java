package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDrivetrain {
    //-----------------------------------------------------------
    //Used Variables:
    // * runningOpMode: The LinearOpMode to stop the robot
    // * MotorSpeed: An array to store the four different motor speeds
    // * RV: Front right motor (RechtsVoor)
    // * RA: Back right motor  (RechtsAchter)
    // * LV: Front left motor (LinksVoor)
    // * LA: Back left motor (LinksAchter)
    // * Odometry: The odometry of the robot
    // * IMU: The Integrated Measurement Unit of the robot
    //-----------------------------------------------------------
    private LinearOpMode runningOpMode;
    public double[] MotorSpeed = new double[4];
    public DcMotorEx rv;
    public DcMotorEx ra;
    public DcMotorEx lv;
    public DcMotorEx la;
    private final Odometry odometry;
    public IMU imu;
    //-----------------------------------------------------------
    //Used Variables
    //-----------------------------------------------------------


    //-----------------------------------------------------------
    //Constructors
    // * The first constructor is the default MecanumDrivetrain constructor
    // * The second constructor calls the first constructor with an added LinearOpMode for autonomous
    //-----------------------------------------------------------
    public MecanumDrivetrain(HardwareMap hardwareMap, Odometry _Odometry, IMU _imu) {
        odometry = _Odometry;
        imu = _imu;
        rv = hardwareMap.get(DcMotorEx.class, "RV");
        ra = hardwareMap.get(DcMotorEx.class, "RA");
        lv = hardwareMap.get(DcMotorEx.class, "LV");
        la = hardwareMap.get(DcMotorEx.class, "LA");

        //Reversing the left motors because they are mirrored
        rv.setDirection(DcMotorEx.Direction.REVERSE);
        ra.setDirection(DcMotorEx.Direction.REVERSE);

        rv.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        ra.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        lv.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        la.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);


        //Run all motors with encoders
        rv.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        ra.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        lv.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        la.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
    }


    //Constructor for MecanumDrivetrain with an LinearOpMode as argument instead of hardwareMap
    public MecanumDrivetrain(LinearOpMode _runningOpMode, Odometry _Odometry, IMU _imu) {
        this(_runningOpMode.hardwareMap, _Odometry, _imu);
        runningOpMode = _runningOpMode;
    }
    //-----------------------------------------------------------
    //Constructors
    //-----------------------------------------------------------


    //---------------------------------------------------------
    //Set, add, power and other speed methods:
    // * setSpeed(): Sets speed values to the MotorSpeed array. The following arguments can be entered:
    // * - 4 doubles
    // * - 1 double
    // * - 1 array
    // * addSpeed(): Adds speed values to the MotorSpeed array. The following arguments can be entered:
    // * - 4 doubles
    // * - 1 double
    // * - 1 array
    // * setPower(): Sets power to the motors. The following arguments can be entered:
    // * - No arguments. This will cause the motors to be powered with the values in the MotorSpeed array
    // * - 4 doubles
    // * - 1 double
    // * - 1 array
    // * Stop(): Stops the motors
    // * MultiplySpeed(): Multiplies all speed values in the MotorSpeed array with a factor
    // * GetVelocity(): Returns an array of the velocities of the motors
    // * GetPower(): Returns an array of the power values of the motors
    //---------------------------------------------------------
    public void setSpeed(double SpeedRV, double SpeedRA, double SpeedLV, double SpeedLA) {
        MotorSpeed[0] = SpeedRV;
        MotorSpeed[1] = SpeedRA;
        MotorSpeed[2] = SpeedLV;
        MotorSpeed[3] = SpeedLA;
    }

    public void setSpeed(double Speed) {
        MotorSpeed[0] = Speed;
        MotorSpeed[1] = Speed;
        MotorSpeed[2] = Speed;
        MotorSpeed[3] = Speed;
    }

    public void setSpeed(double[] Speedarr) {
        MotorSpeed[0] = Speedarr[0];
        MotorSpeed[1] = Speedarr[1];
        MotorSpeed[2] = Speedarr[2];
        MotorSpeed[3] = Speedarr[3];
    }


    public void addSpeed(double SpeedRV, double SpeedRA, double SpeedLV, double SpeedLA) {
        MotorSpeed[0] += SpeedRV;
        MotorSpeed[1] += SpeedRA;
        MotorSpeed[2] += SpeedLV;
        MotorSpeed[3] += SpeedLA;
    }

    public void addSpeed(double Speed) {
        MotorSpeed[0] += Speed;
        MotorSpeed[1] += Speed;
        MotorSpeed[2] += Speed;
        MotorSpeed[3] += Speed;
    }

    public void addSpeed(double[] Speedarr) {
        MotorSpeed[0] += Speedarr[0];
        MotorSpeed[1] += Speedarr[1];
        MotorSpeed[2] += Speedarr[2];
        MotorSpeed[3] += Speedarr[3];
    }


    public void setPower() {
        rv.setPower(MotorSpeed[0]);
        ra.setPower(MotorSpeed[1]);
        lv.setPower(MotorSpeed[2]);
        la.setPower(MotorSpeed[3]);
    }

    public void setPower(double SpeedRV, double SpeedRA, double SpeedLV, double SpeedLA) {
        rv.setPower(SpeedRV);
        ra.setPower(SpeedRA);
        lv.setPower(SpeedLV);
        la.setPower(SpeedLA);
    }

    public void setPower(double Speed) {
        rv.setPower(Speed);
        ra.setPower(Speed);
        lv.setPower(Speed);
        la.setPower(Speed);
    }

    public void setPower(double[] Speedarr) {
        rv.setPower(Speedarr[0]);
        ra.setPower(Speedarr[1]);
        lv.setPower(Speedarr[2]);
        la.setPower(Speedarr[3]);
    }


    public void Stop() {
        setPower(0.0);
    }

    public void MultiplySpeed(double factor) {
        MotorSpeed[0] *= factor;
        MotorSpeed[1] *= factor;
        MotorSpeed[2] *= factor;
        MotorSpeed[3] *= factor;
    }

    public double[] getVelocities() {
        return new double[]{rv.getVelocity(), ra.getVelocity(), lv.getVelocity(), la.getVelocity()};
    }

    public double[] getPowers() {
        return new double[]{rv.getPower(), ra.getPower(), lv.getPower(), la.getPower()};
    }
    //---------------------------------------------------------
    //Set, add and power speed methods
    //---------------------------------------------------------


    /**
     * Drives the robot forward a certain amount of cm with a given Speed
     *
     * @param Distance The distance in cm
     * @param Power    Power value in range [0..1]
     */
    //---------------------------------------------------------
    //Simple drive methods:
    // * DriveStraight(): Drives the robot forward a certain amount of cm with a given Speed
    // * setSpeedValuesTurnRobot(): Sets speed values to the robot for turning a certain amount of degrees in the absolute orientation plane
    // * TurnRobotNoLoop(): Turns the robot to the robot a certain degrees without a while loop
    // * Follow(): The robot will follow a theoretical line with a specific angle. Note that this method does not loop
    // * TurnRobotAO(): Turns the robot on the absolute orientation plane with a certain angle
    //---------------------------------------------------------
    public void DriveStraight(double Distance, double Power) {
        odometry.Reset();

        double OriginYPos = odometry.getY();
        double endDistance = Math.abs(Distance + OriginYPos);

        while (Math.abs(odometry.getY()) < endDistance && !runningOpMode.isStopRequested())
            setPower(Power * Math.signum(Distance));

        setPower(0);
    }

    public void setSpeedValuesTurnRobot(double NewAngle) {
        double[] SpeedValues = imu.getTurnCorrectionValues(NewAngle, 20);
        setSpeed(SpeedValues);
    }

    public void TurnRobotNoLoop(double NewAngle) {
        setSpeedValuesTurnRobot(NewAngle);
        setPower();
    }

    public void Follow(double NewAngle, double Speed) {
        setSpeedValuesTurnRobot(NewAngle);
        addSpeed(Speed);
        FixMotorSpeedOverflow();
        setPower();
    }

    public void TurnRobotAO(double Angle) {
        while (Math.abs(Angle - imu.getAngle()) > 2 && !runningOpMode.isStopRequested()) {
            double[] SpeedValues = imu.getTurnCorrectionValues(Angle, 20);
            setPower(SpeedValues);
        }
        Stop();
    }

    //---------------------------------------------------------
    //Simple drive functions
    //---------------------------------------------------------


    //---------------------------------------------------------
    //Strafing methods:
    // * getStrafeValues(): Gets the strafe values of the motors for a certain Angle and speed value
    // * setStrafeValues(): sets speed values to robot for strafing. This method has two isomorphic methods:
    // * - Angle and speed value: Sets the MotorSpeed array values to associated values for strafing
    // * - Angle, speed and Desired angle: Sets the MotorSpeed array values to associated values for strafing and corrects for a desired angle in the absolute orientation plane
    // * powerStrafeValues(): powers robot to strafe in the desired direction. This method has two isomorphic methods:
    // * - Angle and speed value: Powers the motors to associated values for strafing
    // * - Angle, speed and Desired angle: Powers the motor values to associated values for strafing and corrects for a desired angle in the absolute orientation plane
    //---------------------------------------------------------
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


    public void setStrafeValues(double StrafeAngle, double Speed) {
        setSpeed(getStrafeValues(StrafeAngle, Speed));
    }

    public void setStrafeValues(double StrafeAngle, double Speed, double DesiredAngle) {
        setStrafeValues(StrafeAngle, Speed);
        double[] CorrectionFactors = imu.getTurnCorrectionValues(DesiredAngle, 30);
        addSpeed(CorrectionFactors);
    }


    public void powerStrafeValues(double StrafeAngle, double Speed) {
        setStrafeValues(StrafeAngle, Speed);
        setPower();
    }

    public void powerStrafeValues(double StrafeAngle, double Speed, double DesiredAngle) {
        setStrafeValues(StrafeAngle, Speed, DesiredAngle);
        FixMotorSpeedOverflow();
        setPower();
    }

    //---------------------------------------------------------
    //Strafing methods
    //---------------------------------------------------------


    //---------------------------------------------------------
    //Other methods
    // * FixMotorSpeedOverflow(): Decreases all values to be under a value of 1 if needed
    // * EmptyValues(): Sets all values in MotorSpeed to 0
    //---------------------------------------------------------

    public void FixMotorSpeedOverflow() {
        double max = 1;
        for (int i = 0; i < 4; i++) {
            if (Math.abs(MotorSpeed[i]) > max)
                max = Math.abs(MotorSpeed[i]);
        }
        MultiplySpeed(1.0 / max);
    }

    public void EmptyMotorSpeedValues() {
        MotorSpeed = new double[4];
    }

//---------------------------------------------------------
//Other methods
//---------------------------------------------------------

}
