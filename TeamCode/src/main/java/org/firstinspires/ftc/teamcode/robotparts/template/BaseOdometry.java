package org.firstinspires.ftc.teamcode.robotparts.template;

public class BaseOdometry {
    public BaseOdometry() {};
    /** Returns the x position of the pinpointodometry. */
    public double getX() { return 0; }

    /** Returns the y position of the pinpointodometry. */
    public double getY() { return 0; }

    /// get straight distance from startpoint
    public double getDistance() {
        /** Use Pythagoras */
        return Math.hypot(getY(), getX());
    }

    /** Resets the encoders. */
    public void reset() { }
}