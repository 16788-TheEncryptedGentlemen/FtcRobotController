package org.firstinspires.ftc.teamcode.autonomousclasses;


public class CheckPoint
{
    /** X coordinate of the Checkpoint. */
    public double X;
    /** Y coordinate of the Checkpoint. */
    public double Y;
    /** T value on the Bezier Curve on the checkpoint. */
    public double T;



    /** CheckPoint constructor. */
    public CheckPoint(double _X, double _Y, double _T)
    {
        X = _X;
        Y = _Y;
        T = _T;
    }

    /** Empty CheckPoint constructor. */
    public CheckPoint() {}





    //Converts Checkpoint to Point and returns said Point
    public Point toPoint()
    {
        return new Point(this.X,this.Y);
    }


}