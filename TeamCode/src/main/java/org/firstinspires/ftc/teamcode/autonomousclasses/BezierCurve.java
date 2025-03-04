package org.firstinspires.ftc.teamcode.autonomousclasses;


public class BezierCurve
{
    /** The coefficients of the parametric curve in the form: a0*T + a1*T^2 + a2*T^3 ... */
    public final double[] XCoefficients;
    /** The coefficients of the parametric curve in the form: a0*T + a1*T^2 + a2*T^3 ... */
    public final double[] YCoefficients;

    /** Constructor of the BezierCurve class. */
    public BezierCurve(double[] _XCoefficients, double[] _YCoefficients)
    {
        XCoefficients = _XCoefficients;
        YCoefficients = _YCoefficients;
    }


    /** Returns x-coordinate of the bezier curve at a given T value. */
    public double getX(double T)
    {
        double X = 0;
        for(int i = 1; i < XCoefficients.length + 1; i++)
            X += XCoefficients[i - 1]*Math.pow(T,i);

        return X;
    }

    /** Returns y-coordinate of the bezier curve at a given T value. */
    public double getY(double T)
    {
        double Y = 0;
        for(int i = 1; i < XCoefficients.length + 1; i++)
            Y += YCoefficients[i - 1]*Math.pow(T,i);

        return Y;
    }

    /** Returns an estimate of the slope of the x-curve at a given T value. */
    public double getXSlope(double T)
    {
        return ((getX(T + 0.01) - getX(T)))/0.01;
    }

    /** Returns an estimate of the slope of the y-curve at a given T value. */
    public double getYSlope(double T)
    {
        return ((getY(T + 0.01) - getY(T)))/0.01;
    }

    /** Returns the slope of the bezier curve at a given T value. */
    public double getSlope(double T)
    {
        return (getYSlope(T))/(getXSlope(T));
    }

    /** Returns a point on the curve at a given T value. */
    public Point getPosition(double T)
    {
        return new Point(getX(T), getY(T));
    }

    /** Returns a checkpoint on the curve at a given T value. */
    public CheckPoint getCheckPointPosition(double T)
    {
        return new CheckPoint(getX(T), getY(T), T);
    }

}