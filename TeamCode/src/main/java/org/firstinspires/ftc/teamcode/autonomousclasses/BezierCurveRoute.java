package org.firstinspires.ftc.teamcode.autonomousclasses;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

// TODO: Sanne: Add documentation to this class

public class BezierCurveRoute {
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
    public enum DRIVE_METHOD {
        STRAFE,
        FOLLOW
    }

    private DRIVE_METHOD driveMethod;

    /** Constructor of the BezierCurveRoute class. The Checkpoint list is also generated here. */
    public BezierCurveRoute(double[] XCoefficients, double[] YCoefficients, CompetitionRobot _Robot, double _Speed, DRIVE_METHOD _DriveMethod, LinearOpMode _runningOpMode) {
        bezierCurve = new BezierCurve(XCoefficients, YCoefficients);
        checkPoints = new CheckPoints(bezierCurve, distanceBetweenCheckPoints, steplength);
        robot = _Robot;
        speed = _Speed;
        driveMethod = _DriveMethod;
        runningOpMode = _runningOpMode;
    }

    /** The position of the robot on the field compared to the starting position. */
    public Point robotPosition = new Point(0, 0);
    /** The line segment to the currently assigned checkpoint. */
    public LineSegment lineSegmentToNearestCheckPoint;
    /** The angle of direction compared to the starting position. The angle is measured from -180 to 180 degrees. */
    public double driveAngle;
    /** The array of checkpoints. The robot is done driving once the robot has driven all checkpoints in sequence. */
    public CheckPoint[] checkpointArray;

    /** The line perpendicular to the Beziercurve on an Checkpoint. */
    LineSegment checkpointLine;

    /** This method has the same goal as Execute(), but it accounts of missing points (Overshooting). */
    public void executeWithPointSkip() {
        // If the DriveMethod is follow, the robot will turn towards the direction of heading.
        if (driveMethod == DRIVE_METHOD.FOLLOW) {
            turnRobotAtStart();
        }
        /** Reset the odometry. */
        robot.odometry.reset();
        /** The Checkpoint List is converted to an array, because for some reason java won't do for each loops with lists. */
        checkpointArray = checkPoints.toArray();

        for (CheckPoint NextCheckpoint : checkpointArray) {
            lineSegmentToNearestCheckPoint = new LineSegment(robotPosition, NextCheckpoint.toPoint());

            // dydt, dxdt and Slope are calculated using the Beziercurve.
            double dydt = bezierCurve.getYSlope(NextCheckpoint.t);
            double dxdt = bezierCurve.getXSlope(NextCheckpoint.t);
            double slope = dydt / dxdt;

            /** The CheckpointLine and ExamplePointOnSkipSide are calculated using dydt, dxdt and Slope. */
            checkpointLine = new LineSegment(NextCheckpoint.toPoint(), -1 / slope);
            /** The point on the skip side of the checkpoint to calculate SkipPointvalue. */
            Point examplePointOnSkipSide = new Point(NextCheckpoint.x + dxdt, NextCheckpoint.y + dydt);

            /** SkipPointValue and RobotSkipValue are calculated using CheckpointLine and RobotSkipValue. */
            int skipPointValue = getSkipPointValue(checkpointLine, examplePointOnSkipSide);
            int robotSkipValue = getSkipPointValue(checkpointLine, robotPosition);

            /** The loop will also break if the SkipPointValue is equal to RobotSkipValue. */
            while (lineSegmentToNearestCheckPoint.length > 3 && !runningOpMode.isStopRequested() && robotSkipValue != skipPointValue) {
                /** RobotSkipValue is updated every cycle. */
                updateRobotPosition();
                robotSkipValue = getSkipPointValue(checkpointLine, robotPosition);
                lineSegmentToNearestCheckPoint = new LineSegment(robotPosition, NextCheckpoint.toPoint());
                driveAngle = lineSegmentToNearestCheckPoint.angle;
                powerRobot(driveAngle, speed);
                runningOpMode.telemetry.addData("X", robot.odometry.getX());
                runningOpMode.telemetry.addData("Y", robot.odometry.getY());
            }
        }

        /** After all checkpoints have been visited, stop moving. */
        robot.drivetrain.stop();
        /** Reset odometry. */
        robot.odometry.reset();
    }


    /** Turn the robot to face the direction of the Bezier Curve. */
    public void turnRobotAtStart() {
        double XslopeStart = bezierCurve.getXSlope(0);
        double YslopeStart = bezierCurve.getYSlope(0);
        double HypotSlope = Math.hypot(XslopeStart, YslopeStart);
        double StartAngle = Math.signum(XslopeStart) * Math.toDegrees(Math.acos(YslopeStart / HypotSlope));
        robot.drivetrain.turnRobotAO(StartAngle);
    }

    /** Distance driven since last check. */
    double DistanceDrivenLastCheck = 0;

    /** Updates the robot position to the RobotPosition (Point) Object. */
    private void updateRobotPosition() {
        if (driveMethod == DRIVE_METHOD.STRAFE) {
            robotPosition.x = robot.odometry.getX();
            robotPosition.y = robot.odometry.getY();
        } else if (driveMethod == DRIVE_METHOD.FOLLOW) {
            double ds = robot.odometry.getY() - DistanceDrivenLastCheck;
            DistanceDrivenLastCheck = robot.odometry.getY();

            double dy = ds * Math.cos(Math.toRadians(driveAngle));
            double dx = ds * Math.sin(Math.toRadians(driveAngle));
            robotPosition.x += dx;
            robotPosition.y += dy;
        }
    }

    /** Powers the robot to the given drive angle. */
    private void powerRobot(double driveAngle, double speed) {
        switch (driveMethod) {
            case STRAFE:
                robot.drivetrain.powerStrafeValues(driveAngle, speed, 0.0);
                break;
            case FOLLOW:
                robot.drivetrain.follow(driveAngle, speed);
                break;
            default:
                throw new RuntimeException("Unexpected drive method in bezier curve.");
        }
    }

    /** Gets the value of the side on which the point lies compared to a checkpointline. */
    private int getSkipPointValue(LineSegment line, Point Robotpoint) {
        return (int) (Math.signum((line.point2.x - line.point1.x) * (Robotpoint.y - line.point1.y) - (line.point2.y - line.point1.y) * (Robotpoint.x - line.point1.x)));
    }
}