package org.firstinspires.ftc.Autonomousclasses;


public class Point {

    public double X; //The X-coordinate of the point
    public double Y; //The Y-coordinate of the point

    //Constructor of the Point
    public Point(double x, double y)
    {
        X = x;
        Y = y;
    }
    
    //Converts Point to CheckPoint and returns said Point.
    public CheckPoint ConvertToCheckPoint(double T) {
        return new CheckPoint(this.X, this.Y, T);
    }
}