package org.firstinspires.ftc.teamcode.autonomousclasses;


public class CheckPoint
{
    /** X coordinate of the Checkpoint. */
    public double x;
    /** Y coordinate of the Checkpoint. */
    public double y;
    /** T value on the Bezier Curve on the checkpoint. */
    public double t;



    /** CheckPoint constructor. */
    public CheckPoint(double _X, double _Y, double _T)
    {
        x = _X;
        y = _Y;
        t = _T;
    }

    /** Empty CheckPoint constructor. */
    public CheckPoint() {}





    /** Converts Checkpoint to Point and returns said Point. */
    public Point toPoint()
    {
        return new Point(this.x,this.y);
    }


}