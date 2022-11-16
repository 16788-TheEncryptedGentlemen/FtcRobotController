package org.firstinspires.ftc.teamcode.autonomousclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;

// TODO: Sanne: Add documentation to this class

public class BezierCurveRoute
{

    /** The distance between checkpoints on the curve. */
    private final double distanceBetweenCheckPoints = 5;
    /** The steps in t when calculating checkpoints. The lower the value, the higher the precision of the checkpoints, but the longer it takes to calculate. */
    private final double steplength = 0.01;

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
    private DRIVE_METHOD driveMethod;

    /** Constructor of the BezierCurveRoute class. The Checkpoint list is also generated here.*/
    public BezierCurveRoute(double[] XCoefficients, double[] YCoefficients, CompetitionRobot _Robot, double _Speed, DRIVE_METHOD _DriveMethod, LinearOpMode _runningOpMode)
    {
        bezierCurve = new BezierCurve(XCoefficients, YCoefficients);
        checkPoints = new CheckPoints(bezierCurve, distanceBetweenCheckPoints, steplength);
        robot = _Robot;
        speed = _Speed;
        driveMethod = _DriveMethod;
        runningOpMode = _runningOpMode;
    }


    /** The position of the robot on the field compared to the starting position. */
    public Point robotPosition = new Point(0,0);
    /** The line segment to the currently assigned checkpoint. */
    public LineSegment lineSegmentToNearestCheckPoint;
    /** The angle of direction compared to the starting position. The angle is measured from -180 to 180 degrees. */
    public double driveAngle;
    /** The array of checkpoints. The robot is done driving once the robot has driven all checkpoints in sequence. */
    public CheckPoint[] checkpointArray;


    public void execute()
    {
        /** The robot checks if the DriveMethod is Follow. If it is, it will turn towards the direction of heading. */
        turnRobotAtStart();
        /** Reset the odometry. */
        robot.odometry.reset();
        /** The Checkpoint List is converted to an array, because for some reason java won't do for each loops with lists. */
        checkpointArray = checkPoints.toArray();

        for(CheckPoint NextCheckpoint : checkpointArray)
        {
            /** We calculate a line segment towards the currently assigned checkpoint. */
            lineSegmentToNearestCheckPoint = new LineSegment(robotPosition, NextCheckpoint.toPoint());

            /** While the robot is not within 3 cm of the assigned checkpoint and the robot is not stopped: */
            while(lineSegmentToNearestCheckPoint.length > 3 && !runningOpMode.isStopRequested())
            {
                /** Update the robot position. */
                updateRobotPosition();
                /** Recalculate the line segments towards the assigned checkpoint. */
                lineSegmentToNearestCheckPoint = new LineSegment(robotPosition, NextCheckpoint.toPoint());
                /** Calculate the angle of movement for the robot. */
                driveAngle = lineSegmentToNearestCheckPoint.angle;
                /** Power the robot accordingly. */
                powerRobot();
            }
        }

        /** After all checkpoints have been visited, stop moving. */
        robot.drivetrain.stop();
        /** Reset odometry. */
        robot.odometry.reset();
    }


    /** The integer value that is associated with skipping the associated checkpoint. */
    public int skipPointValue;
    /** The integer value of the robot that is associated with skipping. */
    public int robotSkipValue;
    /** The line perpendicular to the Beziercurve on an Checkpoint. */
    LineSegment checkpointLine;
    /** The slope of the y-curve at an assigned checkpoint. */
    private double dydt;
    /** The slope of the x-curve at an assigned checkpoint. */
    private double dxdt;
    /** The slope of the bezier curve at a checkpoint. */
    private double slope;
    /** The point on the skip side of the checkpoint to calculate SkipPointvalue. */
    private Point examplePointOnSkipSide;


    /** This method has the same goal as Execute(), but it accounts of missing points (Overshooting). */
    public void executeWithPointSkip()
    {
        /** The robot checks if the DriveMethod is Follow. If it is, it will turn towards the direction of heading. */
        turnRobotAtStart();
        /** Reset the odometry. */
        robot.odometry.reset();
        /** The Checkpoint List is converted to an array, because for some reason java won't do for each loops with lists. */
        checkpointArray = checkPoints.toArray();

        for(CheckPoint NextCheckpoint : checkpointArray)
        {
            lineSegmentToNearestCheckPoint = new LineSegment(robotPosition, NextCheckpoint.toPoint());

            /** dydt, dxdt and Slope are calculated using the Beziercurve. */
            dydt = bezierCurve.getYSlope(NextCheckpoint.t);
            dxdt = bezierCurve.getXSlope(NextCheckpoint.t);
            slope = dydt/dxdt;

            /** The CheckpointLine and ExamplePointOnSkipSide are calculated using dydt, dxdt and Slope. */
            checkpointLine = new LineSegment(NextCheckpoint.toPoint(), -1/ slope);
            examplePointOnSkipSide = new Point(NextCheckpoint.x + dxdt, NextCheckpoint.y + dydt);

            /** SkipPointValue and RobotSkipValue are calculated using CheckpointLine and RobotSkipValue. */
            skipPointValue = getSkipPointValue(checkpointLine, examplePointOnSkipSide);
            robotSkipValue = getSkipPointValue(checkpointLine, robotPosition);

            /** The loop will also break if the SkipPointValue is equal to RobotSkipValue. */
            while(lineSegmentToNearestCheckPoint.length > 3 && !runningOpMode.isStopRequested() && robotSkipValue != skipPointValue)
            {
                /** RobotSkipValue is updated every cycle. */
                updateRobotPosition();
                robotSkipValue = getSkipPointValue(checkpointLine, robotPosition);
                lineSegmentToNearestCheckPoint = new LineSegment(robotPosition, NextCheckpoint.toPoint());
                driveAngle = lineSegmentToNearestCheckPoint.angle;
                powerRobot();
            }

        }

        /** After all checkpoints have been visited, stop moving. */
        robot.drivetrain.stop();
        /** Reset odometry. */
        robot.odometry.reset();
    }


    /** Turn the robot to face the direction of the Bezier Curve. */
    public void turnRobotAtStart()
    {
        if(driveMethod == DRIVE_METHOD.FOLLOW)
        {
            double XslopeStart = bezierCurve.getXSlope(0);
            double YslopeStart = bezierCurve.getYSlope(0);
            double HypotSlope = Math.hypot(XslopeStart,YslopeStart);
            double StartAngle = Math.signum(XslopeStart)*Math.toDegrees(Math.acos(YslopeStart/HypotSlope));
            robot.drivetrain.turnRobotAO(StartAngle);
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
        if(driveMethod == DRIVE_METHOD.STRAFE)
        {
            robotPosition.x = robot.odometry.getX();
            robotPosition.y = robot.odometry.getY();
        }
        else if(driveMethod == DRIVE_METHOD.FOLLOW)
        {
            ds = robot.odometry.getY() - DistanceDrivenLastCheck;
            DistanceDrivenLastCheck = robot.odometry.getY();

            dy = ds*Math.cos(Math.toRadians(driveAngle));
            dx = ds*Math.sin(Math.toRadians(driveAngle));
            robotPosition.x += dx;
            robotPosition.y += dy;
        }
    }


    /** Powers the robot accordingly. */
    private void powerRobot()
    {
        if(driveMethod == DRIVE_METHOD.STRAFE)
            robot.drivetrain.powerStrafeValues(driveAngle, speed, 0.0);
        else if(driveMethod == DRIVE_METHOD.FOLLOW)
            robot.drivetrain.follow(driveAngle, speed);
    }


    /** Gets the value of the side on which the point lies compared to a checkpointline. */
    private int getSkipPointValue(LineSegment line, Point Robotpoint)
    {
        return (int)(Math.signum((line.point2.x - line.point1.x)*(Robotpoint.y - line.point1.y) - (line.point2.y - line.point1.y)*(Robotpoint.x - line.point1.x)));
    }

}