package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


public class Imu {
    /** The actual IMU in this object. */
    private IMU imu;
    /** The parameters of the IMU. */
    IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
            RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
            RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
    ));


    public Imu(HardwareMap hardwareMap) {
        imu = hardwareMap.get(IMU.class, "IMU");
        imu.initialize(parameters);
    }


    /** Gets the angle of the robot on the absolute orientation plane from -180 to 180 degrees. */
    public double getAngle() {
        double angle = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

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
}