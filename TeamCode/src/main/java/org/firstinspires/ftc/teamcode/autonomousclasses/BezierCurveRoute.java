package org.firstinspires.ftc.teamcode.autonomousclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;

// TODO: Sanne: Add documentation to this class

public class BezierCurveRoute
{

    /** The distance between checkpoints on the curve. */
    private final double DistanceBetweenCheckPoints = 5;
    /** The steps in t when calculating checkpoints. The lower the value, the higher the precision of the checkpoints, but the longer it takes to calculate. */
    private final double Steplength = 0.01;

    /** The beziercurve the robot has to drive. */
    public BezierCurve bezierCurve;
    /** The list of checkpoints along the curve. */
    public CheckPoints checkPoints;
    /** The entire Robot. This class can take control of the robot. We mainly need: Drivetrain, Odometry and IMU. */
    private CompetitionRobot robot;
    /** The currently running LinearOpMode. runningOpMode is used to stop the robot if stop is requested. */
    public LinearOpMode runningOpMode;
    /** The speed of the robot from 0 (no movement) to 1 (max speed). */
    public double speed;

    /** An enum that decides on the movement method (either strafing or following the curve). */
    public enum DRIVE_METHOD
    {
        STRAFE,
        FOLLOW
    }
    private DRIVE_METHOD DriveMethod;

    /** Constructor of the BezierCurveRoute class. The Checkpoint list is also generated here.*/
    public BezierCurveRoute(double[] XCoefficients, double[] YCoefficients, CompetitionRobot _Robot, double _Speed, DRIVE_METHOD _DriveMethod, LinearOpMode _runningOpMode)
    {
        bezierCurve = new BezierCurve(XCoefficients, YCoefficients);
        checkPoints = new CheckPoints(bezierCurve, DistanceBetweenCheckPoints, Steplength);
        robot = _Robot;
        speed = _Speed;
        DriveMethod = _DriveMethod;
        runningOpMode = _runningOpMode;
    }


    /** The position of the robot on the field compared to the starting position. */
    public Point RobotPosition = new Point(0,0);
    /** The line segment to the currently assigned checkpoint. */
    public LineSegment LineSegmentToNearestCheckPoint;
    /** The angle of direction compared to the starting position. The angle is measured from -180 to 180 degrees. */
    public double DriveAngle;
    /** The array of checkpoints. The robot is done driving once the robot has driven all checkpoints in sequence. */
    public CheckPoint[] CheckpointArray;


    public void Execute()
    {
        /** The robot checks if the DriveMethod is Follow. If it is, it will turn towards the direction of heading. */
        TurnRobotAtStart();
        /** Reset the odometry. */
        robot.odometry.Reset();
        /** The Checkpoint List is converted to an array, because for some reason java won't do for each loops with lists. */
        CheckpointArray = checkPoints.toArray();

        for(CheckPoint NextCheckpoint : CheckpointArray)
        {
            /** We calculate a line segment towards the currently assigned checkpoint. */
            LineSegmentToNearestCheckPoint = new LineSegment(RobotPosition, NextCheckpoint.toPoint());

            /** While the robot is not within 3 cm of the assigned checkpoint and the robot is not stopped: */
            while(LineSegmentToNearestCheckPoint.Length > 3 && !runningOpMode.isStopRequested())
            {
                /** Update the robot position. */
                updateRobotPosition();
                /** Recalculate the line segments towards the assigned checkpoint. */
                LineSegmentToNearestCheckPoint = new LineSegment(RobotPosition, NextCheckpoint.toPoint());
                /** Calculate the angle of movement for the robot. */
                DriveAngle = LineSegmentToNearestCheckPoint.Angle;
                /** Power the robot accordingly. */
                PowerRobot();
            }
        }

        /** After all checkpoints have been visited, stop moving. */
        robot.drivetrain.Stop();
        /** Reset odometry. */
        robot.odometry.Reset();
    }


    /** The integer value that is associated with skipping the associated checkpoint. */
    public int SkipPointValue;
    /** The integer value of the robot that is associated with skipping. */
    public int RobotSkipValue;
    /** The line perpendicular to the Beziercurve on an Checkpoint. */
    LineSegment CheckpointLine;
    /** The slope of the y-curve at an assigned checkpoint. */
    private double dydt;
    /** The slope of the x-curve at an assigned checkpoint. */
    private double dxdt;
    /** The slope of the bezier curve at a checkpoint. */
    private double Slope;
    /** The point on the skip side of the checkpoint to calculate SkipPointvalue. */
    private Point ExamplePointOnSkipSide;


    /** This method has the same goal as Execute(), but it accounts of missing points (Overshooting). */
    public void ExecuteWithPointSkip()
    {
        /** The robot checks if the DriveMethod is Follow. If it is, it will turn towards the direction of heading. */
        TurnRobotAtStart();
        /** Reset the odometry. */
        robot.odometry.Reset();
        /** The Checkpoint List is converted to an array, because for some reason java won't do for each loops with lists. */
        CheckpointArray = checkPoints.toArray();

        for(CheckPoint NextCheckpoint : CheckpointArray)
        {
            LineSegmentToNearestCheckPoint = new LineSegment(RobotPosition, NextCheckpoint.toPoint());

            /** dydt, dxdt and Slope are calculated using the Beziercurve. */
            dydt = bezierCurve.getYSlope(NextCheckpoint.t);
            dxdt = bezierCurve.getXSlope(NextCheckpoint.t);
            Slope = dydt/dxdt;

            /** The CheckpointLine and ExamplePointOnSkipSide are calculated using dydt, dxdt and Slope. */
            CheckpointLine = new LineSegment(NextCheckpoint.toPoint(), -1/Slope);
            ExamplePointOnSkipSide = new Point(NextCheckpoint.x + dxdt, NextCheckpoint.y + dydt);

            /** SkipPointValue and RobotSkipValue are calculated using CheckpointLine and RobotSkipValue. */
            SkipPointValue = getSkipPointValue(CheckpointLine, ExamplePointOnSkipSide);
            RobotSkipValue = getSkipPointValue(CheckpointLine, RobotPosition);

            /** The loop will also break if the SkipPointValue is equal to RobotSkipValue. */
            while(LineSegmentToNearestCheckPoint.Length > 3 && !runningOpMode.isStopRequested() && RobotSkipValue != SkipPointValue)
            {
                /** RobotSkipValue is updated every cycle. */
                updateRobotPosition();
                RobotSkipValue = getSkipPointValue(CheckpointLine, RobotPosition);
                LineSegmentToNearestCheckPoint = new LineSegment(RobotPosition, NextCheckpoint.toPoint());
                DriveAngle = LineSegmentToNearestCheckPoint.Angle;
                PowerRobot();
            }

        }

        /**  /** After all checkpoints have been visited, stop moving. */ */
        robot.drivetrain.Stop();
        /** Reset odometry. */
        robot.odometry.Reset();
    }


    /** Turn the robot to face the direction of the Bezier Curve. */
    public void TurnRobotAtStart()
    {
        if(DriveMethod == DRIVE_METHOD.FOLLOW)
        {
            double XslopeStart = bezierCurve.getXSlope(0);
            double YslopeStart = bezierCurve.getYSlope(0);
            double HypotSlope = Math.hypot(XslopeStart,YslopeStart);
            double StartAngle = Math.signum(XslopeStart)*Math.toDegrees(Math.acos(YslopeStart/HypotSlope));
            robot.drivetrain.TurnRobotAO(StartAngle);
        }
    }


    /** Small distance that the y-encoder has traveled in one loop. */
    double ds;
    /** Distance driven since last check. */
    double DistanceDrivenLastCheck = 0;
    /** Small distance in the x direction that the robot has traveled. */
    double dx;
    /** Small distance in the x direction that the robot has traveled. */
    double dy;

    /** Updates the robot position to the RobotPosition (Point) Object. */
    private void updateRobotPosition()
    {
        if(DriveMethod == DRIVE_METHOD.STRAFE)
        {
            RobotPosition.X = robot.odometry.getX();
            RobotPosition.Y = robot.odometry.getY();
        }
        else if(DriveMethod == DRIVE_METHOD.FOLLOW)
        {
            ds = robot.odometry.getY() - DistanceDrivenLastCheck;
            DistanceDrivenLastCheck = robot.odometry.getY();

            dy = ds*Math.cos(Math.toRadians(DriveAngle));
            dx = ds*Math.sin(Math.toRadians(DriveAngle));
            RobotPosition.X += dx;
            RobotPosition.Y += dy;
        }
    }


    /** Powers the robot accordingly. */
    private void PowerRobot()
    {
        if(DriveMethod == DRIVE_METHOD.STRAFE)
            robot.drivetrain.powerStrafeValues(DriveAngle, speed, 0.0);
        else if(DriveMethod == DRIVE_METHOD.FOLLOW)
            robot.drivetrain.Follow(DriveAngle, speed);
    }


    /** Gets the value of the side on which the point lies compared to a checkpointline. */
    private int getSkipPointValue(LineSegment line, Point Robotpoint)
    {
        return (int)(Math.signum((line.point2.X - line.point1.X)*(Robotpoint.Y - line.point1.Y) - (line.point2.Y - line.point1.Y)*(Robotpoint.X - line.point1.X)));
    }

}