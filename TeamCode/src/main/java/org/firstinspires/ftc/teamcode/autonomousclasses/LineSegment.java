package org.firstinspires.ftc.teamcode.autonomousclasses;

// TODO: Sanne: Add documentation to this class

public class LineSegment
{
    //-------------------------------------------------------------------
    //Used Variables:
    // * point1: The starting point of the line segment.
    // * point2: The end point of the line segment.
    // * DeltaX: The x distance of the line segment.
    // * DeltaY: The y distance of the line segment.
    // * Length: The length of the line segment.
    // * a: The slope of the line segment (assumes line segment is a line)
    // * b: The offset value of the line segment (assumes line segment is a line)
    // * Angle: The angle of the line segment compared to the y-axis (From point1 to point 2)
    //-------------------------------------------------------------------
    public final Point point1;
    public final Point point2;
    public final double DeltaX;
    public final double DeltaY;
    public final double Length;
    public final double a;
    public final double b;
    public final double Angle;
    //-------------------------------------------------------------------
    //Used Variables
    //-------------------------------------------------------------------





    //-------------------------------------------------------------------
    //Constructors:
    // * Two arguments: point1 and point2
    // * Two arguments: point1 and the slope
    //-------------------------------------------------------------------
    public LineSegment(Point _point1, Point _point2)
    {
        point1 = _point1;
        point2 = _point2;
        DeltaX = point2.X - point1.X;
        DeltaY = point2.Y - point1.Y;
        Length = Math.hypot(DeltaX, DeltaY);

        a = (point2.X - point1.X)/(point2.Y - point1.Y);
        b = point1.Y - a*point1.X;

        Angle = GetAngle();
    }

    public LineSegment(Point _point1, double _a)
    {
        point1 = _point1;
        a = _a;
        b = point1.Y - a * point1.X;

        point2 = new Point(_point1.X + 1, _point1.Y + a); //Create a point on the line to satisfy point2

        DeltaX = point2.X - point1.X;
        DeltaY = point2.Y - point1.Y;
        Length = Math.hypot(DeltaX, DeltaY);

        Angle = GetAngle();
    }
    //-------------------------------------------------------------------
    //Constructors
    //-------------------------------------------------------------------





    //-------------------------------------------------------------------
    //Methods:
    // * GetAngle(): Gets the angle of the line from point1 to point2
    // * Output(): Gets the y-value of the linesegment at an x value (even outside of point1 and point2)
    //-------------------------------------------------------------------
    private double GetAngle() {
        double Angle;

        Angle = Math.signum(DeltaX)*Math.toDegrees(Math.acos(DeltaY/Length));

        return Angle;
    }

    public double Output(double x)
    {
        return a*x + b;
    }
    //-------------------------------------------------------------------
    //Methods
    //-------------------------------------------------------------------

}