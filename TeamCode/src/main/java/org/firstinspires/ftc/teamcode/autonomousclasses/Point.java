package org.firstinspires.ftc.teamcode.autonomousclasses;


public class Point {

    /** The X-coordinate of the point. */
    public double x;
    /** The Y-coordinate of the point. */
    public double y;

    /** Constructor of the point. */
    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /** Converts point to checkPoint and returns said point. */
    public CheckPoint convertToCheckPoint(double T) {
        return new CheckPoint(this.x, this.y, T);
    }
}