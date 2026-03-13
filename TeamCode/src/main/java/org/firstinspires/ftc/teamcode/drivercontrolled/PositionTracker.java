package org.firstinspires.ftc.teamcode.drivercontrolled;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import org.firstinspires.ftc.teamcode.robotparts.Imu;
import org.firstinspires.ftc.teamcode.robotparts.Odometry;

public class PositionTracker {
    double forward = 0.0;
    double right = 0.0;
    private double previousX = 0.0; // cm
    private double previousY = 0.0; // cm
    public double heading = 0.0; // deg
    private final Odometry odometry;
    private final Imu imu;
    boolean invertX = false;
    boolean invertHeading = true;

    PositionTracker(Odometry odometry, Imu imu) {
        this.odometry = odometry;
        this.imu = imu;
    }

    void update() {
        double x = (invertX ? -1 : 1) * odometry.getX(); // positive is right
        double y = odometry.getY(); // positive is forward
        heading = (invertHeading ? -1 : 1) * imu.getAngle(); // positive is clockwise
        double heading_rad = heading * PI / 180;

        double dx = x - previousX;
        double dy = y - previousY;

        forward += dy * cos(heading_rad) + dx * sin(heading_rad);
        right += dy * sin(heading_rad) + dx * cos(heading_rad);

        // update values.
        previousX = x;
        previousY = y;
    }

    /**
     * Set the parameters for correct reading of values.
     *
     * @param invertX       Set to {@code true} if moving left increases x.
     * @param invertHeading Set to {@code true} if counter-clockwise rotation is positive.
     */
    public void setParameters(boolean invertX, boolean invertHeading) {
        this.invertX = invertX;
        this.invertHeading = invertHeading;
    }

    public void reset() {
        previousX = 0.0;
        previousY = 0.0;
        heading = 0.0;
        imu.reset();
        odometry.reset();
    }
}
