package org.firstinspires.ftc.Autonomousclasses;
import java.util.ArrayList;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.Robots.WedstrijdRobot;



public class BezierCurveRoute
{
    //-----------------------------------------------------------------
    //Used variables:
    // * DistanceBetweenCheckPoints: The distance between checkpoints on the curve
    // * Steplength: The steps in t when calculating checkpoints. The lower the value, the higher the precision of the checkpoints, but the longer it takes to calculate
    // * Beziercurve: The beziercurve the robot has to drive
    // * Checkpoints: The list of checkpoints along the curve
    // * Robot: The entire Robot. This class can take control of the robot. We mainly need: Drivetrain, Odometry and IMU
    // * runningOpMode: The currently running LinearOpMode. runningOpMode is used to stop the robot if stop is requested
    // * Speed: The speed of the robot from 0 (no movement) to 1 (max speed)
    // * DriveMethod: An enum that decides on the movement method (either strafing or following the curve)
    //-----------------------------------------------------------------
        private final double DistanceBetweenCheckPoints = 5;
        private final double Steplength = 0.01;
    
        public BezierCurve Beziercurve;
        public CheckPoints Checkpoints;
        private WedstrijdRobot Robot;
        public LinearOpMode runningOpMode;
        public double Speed;
    
        public enum DRIVE_METHOD
        {
            STRAFE,
            FOLLOW
        }
        private DRIVE_METHOD DriveMethod;
    //-----------------------------------------------------------------
    //Used variables
    //-----------------------------------------------------------------




    
    //Constructor of the BezierCurveRoute class. The Checkpoint list is also generated here.
    public BezierCurveRoute(double[] XCoefficients, double[] YCoefficients, WedstrijdRobot _Robot, double _Speed, DRIVE_METHOD _DriveMethod, LinearOpMode _runningOpMode)
    {
        Beziercurve = new BezierCurve(XCoefficients, YCoefficients);
        Checkpoints = new CheckPoints(Beziercurve, DistanceBetweenCheckPoints, Steplength);
        Robot = _Robot;
        Speed = _Speed; 
        DriveMethod = _DriveMethod;
        runningOpMode = _runningOpMode;
    }


    


    //---------------------------------------------------------------------------------------------------------------------------------------
    //Execute Method:
    // - Variables:
    // - * RobotPosition: The position of the robot on the field compared to the starting position
    // - * LineSegmentToNearestCheckPoint: The line segment to the currently assigned checkpoint
    // - * DriveAngle: The angle of direction compared to the starting position. The angle is measured from -180 to 180 degrees 
    // - * CheckpointArray: The array of checkpoints. The robot is done driving once the robot has driven all checkpoints in sequence
    //
    // - How does the method work:
    // - * First, the robot checks if the DriveMethod is Follow. If it is, it will turn towards the direction of heading.
    // - * Next, we reset the odometry.
    // - * The Checkpoint List is converted to an array, because for some reason java won't do for each loops with lists.
    // - * For each Checkpoint in the list, we do the following:
    // - * - We calculate a line segment towards the currently assigned checkpoint
    // - * - While the robot is not within 3 cm of the assigned checkpoint and the robot is not stopped, do the following:
    // - * - * Update the robot position.
    // - * - * Recalculate the line segments towards the assigned checkpoint.
    // - * - * Calculate the angle of movement for the robot.
    // - * - * Power the robot accordingly.
    // - * After all checkpoints have been visited, stop moving and reset odometry.
    //---------------------------------------------------------------------------------------------------------------------------------------
        public Point RobotPosition = new Point(0,0);
        public LineSegment LineSegmentToNearestCheckPoint;
        public double DriveAngle;
        public CheckPoint[] CheckpointArray;
    
    
        public void Execute()
        {
            TurnRobotAtStart();
            Robot.Odometry.Reset();
            CheckpointArray = Checkpoints.toArray();
            
            for(CheckPoint NextCheckpoint : CheckpointArray)
            {
                LineSegmentToNearestCheckPoint = new LineSegment(RobotPosition, NextCheckpoint.toPoint());
                
                while(LineSegmentToNearestCheckPoint.Length > 3 && !runningOpMode.isStopRequested())
                {
                    updateRobotPosition();
                    LineSegmentToNearestCheckPoint = new LineSegment(RobotPosition, NextCheckpoint.toPoint());
                    DriveAngle = LineSegmentToNearestCheckPoint.Angle;
                    PowerRobot();
                }   
            }
            
            Robot.Drivetrain.Stop();
            Robot.Odometry.Reset();
        }
    //---------------------------------------------------------------------------------------------------------------------------------------
    //Execute method
    //---------------------------------------------------------------------------------------------------------------------------------------
        
        
        
        
    
    //---------------------------------------------------------------------------------------------------------------------------------------
    //ExecuteWithPointSkip Method. This method has the same goal as Execute(), but it accounts of missing points (Overshooting). 
    //All of the already mentioned variabled and code will not be explained again:
    // - Added Variables:
    // - * SkipPointValue: The integer value that is associated with skipping the associated checkpoint
    // - * RobotSkipValue: The integer value of the robot that is associated with skipping.
    // - * CheckpointLine: The line perpendicular to the Beziercurve on an Checkpoint.
    // - * dydt: The slope of the y-curve at an assigned checkpoint
    // - * dxdt: The slope of the x-curve at an assigned checkpoint
    // - * Slope: The slope of the bezier curve at a checkpoint 
    // - * ExamplePointOnSkipSide: The point on the skip side of the checkpoint to calculate SkipPointvalue.
    // - These are the code additions to the method:
    // - * In the foreach loop:
    // - * - dydt, dxdt and Slope are calculated using the Beziercurve
    // - * - The CheckpointLine and ExamplePointOnSkipSide are calculated using dydt, dxdt and Slope
    // - * - SkipPointValue and RobotSkipValue are calculated using CheckpointLine and RobotSkipValue
    // - * In the while loop:
    // - * - One more condition is added: The loop will also break if the SkipPointValue is equal to RobotSkipValue.
    // - * - RobotSkipValue is updated every cycle.
    //---------------------------------------------------------------------------------------------------------------------------------------      
        public int SkipPointValue;
        public int RobotSkipValue;
        LineSegment CheckpointLine;
        private double dydt;
        private double dxdt;
        private double Slope;
        private Point ExamplePointOnSkipSide;
    
    
        public void ExecuteWithPointSkip()
        {
            TurnRobotAtStart();
            Robot.Odometry.Reset();
            CheckpointArray = Checkpoints.toArray();
            
            for(CheckPoint NextCheckpoint : CheckpointArray)
            {
                LineSegmentToNearestCheckPoint = new LineSegment(RobotPosition, NextCheckpoint.toPoint());

                dydt = Beziercurve.getYSlope(NextCheckpoint.T);
                dxdt = Beziercurve.getXSlope(NextCheckpoint.T);
                Slope = dydt/dxdt;
                
                CheckpointLine = new LineSegment(NextCheckpoint.toPoint(), -1/Slope); 
                ExamplePointOnSkipSide = new Point(NextCheckpoint.X + dxdt, NextCheckpoint.Y + dydt);
                
                SkipPointValue = getSkipPointValue(CheckpointLine, ExamplePointOnSkipSide);   
                RobotSkipValue = getSkipPointValue(CheckpointLine, RobotPosition);
                
                while(LineSegmentToNearestCheckPoint.Length > 3 && !runningOpMode.isStopRequested() && RobotSkipValue != SkipPointValue)
                {
                    updateRobotPosition();
                    RobotSkipValue = getSkipPointValue(CheckpointLine, RobotPosition);
                    LineSegmentToNearestCheckPoint = new LineSegment(RobotPosition, NextCheckpoint.toPoint());
                    DriveAngle = LineSegmentToNearestCheckPoint.Angle;
                    PowerRobot();
                }   
                
            }
            
            Robot.Drivetrain.Stop();
            Robot.Odometry.Reset();        
        }
    //---------------------------------------------------------------------------------------------------------------------------------------
    //ExecuteWithPointSkip Method
    //---------------------------------------------------------------------------------------------------------------------------------------
    
    
    
    
    
    //---------------------------------------------------------------------------------------------------------------------------------------
    //Other methods:
    // * TurnRobotAtStart(): Turn the robot to face the direction of the Bezier Curve.
    // * updateRobotPosition(): Updates the robot position to the RobotPosition (Point) Object.
    // * - Vars associated with updateRobotPosition():
    // * - ds: Small distance that the y-encoder has traveled in one loop
    // * - DistanceDrivenLastCheck: Distance driven since last check
    // * - dx: Small distance in the x direction that the robot has traveled
    // * - dy: Small distance in the y direction that the robot has traveled
    // * PowerRobot(): Powers the robot accordingly.
    // * getSkipPointValue(): Gets the value of the side on which the point lies compared to a checkpointline.
    //---------------------------------------------------------------------------------------------------------------------------------------
        public void TurnRobotAtStart()
        {
            if(DriveMethod == DRIVE_METHOD.FOLLOW)
            {
                double XslopeStart = Beziercurve.getXSlope(0);
                double YslopeStart = Beziercurve.getYSlope(0); 
                double HypotSlope = Math.hypot(XslopeStart,YslopeStart);
                double StartAngle = Math.signum(XslopeStart)*Math.toDegrees(Math.acos(YslopeStart/HypotSlope));
                Robot.Drivetrain.TurnRobotAO(StartAngle);
            }        
        }
        
        
        
        double ds; 
        double DistanceDrivenLastCheck = 0; 
        double dx; 
        double dy; 
        private void updateRobotPosition()
        {
            if(DriveMethod == DRIVE_METHOD.STRAFE)
            {
                RobotPosition.X = Robot.Odometry.getX();
                RobotPosition.Y = Robot.Odometry.getY();        
            }
            else if(DriveMethod == DRIVE_METHOD.FOLLOW)
            {
                ds = Robot.Odometry.getY() - DistanceDrivenLastCheck;
                DistanceDrivenLastCheck = Robot.Odometry.getY();
                
                dy = ds*Math.cos(Math.toRadians(DriveAngle));
                dx = ds*Math.sin(Math.toRadians(DriveAngle));
                RobotPosition.X += dx;
                RobotPosition.Y += dy;  
            }
        }
        
        
        
        private void PowerRobot()
        {
            if(DriveMethod == DRIVE_METHOD.STRAFE)
                Robot.Drivetrain.powerStrafeValues(DriveAngle, Speed, 0);
            else if(DriveMethod == DRIVE_METHOD.FOLLOW)
                Robot.Drivetrain.Follow(DriveAngle, Speed);
        }
        
        
        
        private int getSkipPointValue(LineSegment line, Point Robotpoint)
        {
             return (int)(Math.signum((line.point2.X - line.point1.X)*(Robotpoint.Y - line.point1.Y) - (line.point2.Y - line.point1.Y)*(Robotpoint.X - line.point1.X)));
        } 
    //---------------------------------------------------------------------------------------------------------------------------------------
    //Other methods
    //---------------------------------------------------------------------------------------------------------------------------------------    
}