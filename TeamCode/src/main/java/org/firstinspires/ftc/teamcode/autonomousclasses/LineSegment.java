package org.firstinspires.ftc.teamcode.autonomousclasses;


public class LineSegment
{

    /** The starting point of the line segment. */
    public final Point point1;
    /** The end point of the line segment. */
    public final Point point2;
    /** The x distance of the line segment. */
    public final double deltaX;
    /** The y distance of the line segment. */
    public final double deltaY;
    /** The length of the line segment. */
    public final double length;
    /** The slope of the line segment (assumes line segment is a line). */
    public final double a;
    /** The offset value of the line segment (assumes line segment is a line). */
    public final double b;
    /** The angle of the line segment compared to the y-axis (From point1 to point 2). */
    public final double angle;


    /** Two arguments: point1 and point2. */
    public LineSegment(Point _point1, Point _point2)
    {
        point1 = _point1;
        point2 = _point2;
        deltaX = point2.x - point1.x;
        deltaY = point2.y - point1.y;
        length = Math.hypot(deltaX, deltaY);

        a = (point2.x - point1.x)/(point2.y - point1.y);
        b = point1.y - a*point1.x;

        angle = GetAngle();
    }

    /** Two arguments: point1 and the slope. */
    public LineSegment(Point _point1, double _a)
    {
        point1 = _point1;
        a = _a;
        b = point1.y - a * point1.x;

        point2 = new Point(_point1.x + 1, _point1.y + a); //Create a point on the line to satisfy point2

        deltaX = point2.x - point1.x;
        deltaY = point2.y - point1.y;
        length = Math.hypot(deltaX, deltaY);

        angle = GetAngle();
    }


    /** Gets the angle of the line from point1 to point2. */
    private double GetAngle() {
        double Angle;

        Angle = Math.signum(deltaX)*Math.toDegrees(Math.acos(deltaY / length));

        return Angle;
    }

    /** Gets the y-value of the linesegment at an x value (even outside of point1 and point2). */
    public double Output(double x)
    {
        return a*x + b;
    }


}