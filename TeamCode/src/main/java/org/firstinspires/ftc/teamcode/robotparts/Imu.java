package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


public class Imu {
    /** The actual IMU in this object. */
    private BNO055IMU imu;
    /** The parameters of the IMU. */
    BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
    /** The offset from the absolute orienation plane. */
    private double angularOffset = 0;


    public Imu(HardwareMap hardwareMap) {
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.loggingTag = "IMU";  //Loggin tag and config name ARE NOT THE SAME (I think) TODO: Figure out if luc was correct

        imu = hardwareMap.get(BNO055IMU.class, "IMU");
        imu.initialize(parameters);
    }


    /** Gets the angle of the robot on the absolute orientation plane from -180 to 180 degrees. */
    public double getAngle() {
        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        double angle = -angles.firstAngle - angularOffset;

        if (angle > 180) {
            angle -= 360;
        } else if (angle < -180) {
            angle += 360;
        }
        return angle;
    }

    /** Gets the deviation from a given angle. */
    public double getDeviation(double DesiredAngle) {
        double deviationAngle = getAngle() - DesiredAngle;

        if (deviationAngle > 180) {
            deviationAngle -= 360;
        } else if (deviationAngle < -180) {
            deviationAngle += 360;
        }
        return deviationAngle;
    }

    /** Returns an array with correction values for the motors to turn. */
    public double[] getTurnCorrectionValues(double DesiredAngle, double P, double speedMultiplier) // P: the minimum angle that will result in a full speed turn. Lower values will result into slower turning.
    {
        double correctionValue = 0;
        double DeviationAngle = getDeviation(DesiredAngle);

        if (Math.abs(DeviationAngle) <= P) {
            correctionValue = DeviationAngle / P;

        } else {
            if (DeviationAngle > 0) {
                correctionValue = 1;
            } else {
                correctionValue = -1;
            }
        }
        correctionValue *= speedMultiplier; // turning speed multiplier
        return new double[]{correctionValue, correctionValue, -correctionValue, -correctionValue};
    }


    /** Resets the entire IMU. This takes up a second. */
    public void reset() {
        imu.initialize(parameters);
    }

    /** Sets AngularOffset, which will result in a resetted angular orientation. */
    public void resetAngularOrientation() {
        angularOffset = getAngle();
    }


}