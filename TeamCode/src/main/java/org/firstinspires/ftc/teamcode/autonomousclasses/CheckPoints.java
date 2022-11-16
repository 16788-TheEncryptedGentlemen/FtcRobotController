package org.firstinspires.ftc.teamcode.autonomousclasses;
import java.util.ArrayList;


public class CheckPoints {

    /** A List of checkpoints. */
    private ArrayList<CheckPoint> checkPoints = new ArrayList<CheckPoint>();
    /** The index of the point that the robot is driving to. */
    public int currentPointIndex;
    /** The bezier curve to calculate the checkpoints. */
    public final BezierCurve bezierCurve;
    /** The distance between the checkpoints on the curve. */
    double distanceBetweenCheckpoints;
    /** The steps in T to calculate the new Checkpoint. */
    double steplength;


    /** Constructor of CheckPoints class. */
    public CheckPoints(BezierCurve _Beziercurve, double _DistanceBetweenCheckpoints, double _Steplength)
    {
        bezierCurve = _Beziercurve;
        steplength = _Steplength;
        distanceBetweenCheckpoints = _DistanceBetweenCheckpoints;

        calculateCheckpoints();
    }


    /** Calculates all checkpoints and adds them to the CheckPoints list. */
    private void calculateCheckpoints()
    {
        /** Sets initialized values. */
        Point point1 = new Point(0,0);
        Point point2;

        double CurrentDistanceToCheckpoint = 0;
        double CurrentTValue = 0;

        /** While the T value is less than one: */
        while(CurrentTValue < 1)
        {
            /** While the current distance between checkpoints is less than the wanted distance between checkpoints: */
            while(distanceBetweenCheckpoints > CurrentDistanceToCheckpoint && CurrentTValue < 1)
            {
                point2 = bezierCurve.getPosition(CurrentTValue);

                /** Add the small distance to the current distance to checkpoint. */
                CurrentDistanceToCheckpoint += Math.hypot(point2.x - point1.x, point2.y - point1.y);

                point1 = point2;

                if(distanceBetweenCheckpoints > CurrentDistanceToCheckpoint)
                    CurrentTValue += steplength;
            }
            if(CurrentTValue < 1) {
                checkPoints.add(point1.convertToCheckPoint(CurrentTValue));
                CurrentDistanceToCheckpoint = 0;
            }
            else {
                point1 = bezierCurve.getPosition(1);
                checkPoints.add(point1.convertToCheckPoint(CurrentTValue));
            }
        }
    }


    /** Converts CheckPoints list to an Array and returns said array. */
    public CheckPoint[] toArray()
    {
        int AmountOfCheckPoints = checkPoints.size();
        CheckPoint[] Checkpoints = new CheckPoint[AmountOfCheckPoints];

        for(int i = 0; i < AmountOfCheckPoints; i++){
            Checkpoints[i] = checkPoints.get(i);
        }

        return Checkpoints;
    }


}