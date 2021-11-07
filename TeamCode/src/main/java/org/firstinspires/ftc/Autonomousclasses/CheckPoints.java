package org.firstinspires.ftc.Autonomousclasses;
import java.util.ArrayList;

public class CheckPoints {
    
    //----------------------------------------------------------------
    //Used Variables:
    // * CheckPoints: A List of checkpoints.
    // * CurrentPointIndex: The index of the point that the robot is driving to.
    // * BezierCurve: The bezier curve to calculate the checkpoints.
    // * DistanceBetweenCheckpoints: The distance between the checkpoints on the curve.
    // * Steplength: The steps in T to calculate the new Checkpoint.
    // * 
    //----------------------------------------------------------------
        private ArrayList<CheckPoint> CheckPoints = new ArrayList<CheckPoint>();
        public int CurrentPointIndex;
        public final BezierCurve Beziercurve;
        double DistanceBetweenCheckpoints;
        double Steplength;
    //----------------------------------------------------------------
    //Used Variables
    //----------------------------------------------------------------
    
    
    
    //Constructor of CheckPoints class
    public CheckPoints(BezierCurve _Beziercurve, double _DistanceBetweenCheckpoints, double _Steplength) 
    {
        Beziercurve = _Beziercurve;
        Steplength = _Steplength;
        DistanceBetweenCheckpoints = _DistanceBetweenCheckpoints;
        
        CalculateCheckpoints();
    }
    
    
    
    
    
    //----------------------------------------------------------------
    //Methods:
    // * CalculateCheckpoints(): Calculates all checkpoints and adds them to the CheckPoints list
    // * toArray(): Converts CheckPoints list to an Array and returns said array. 
    //----------------------------------------------------------------
        private void CalculateCheckpoints() 
        {
            //Sets initialized values
            Point point1 = new Point(0,0);
            Point point2;
            
            double CurrentDistanceToCheckpoint = 0;
            double CurrentTValue = 0;
            
            //While the T value is less than one:
            while(CurrentTValue < 1) 
            {
                //While the current distance between checkpoints is less than the wanted distance between checkpoints:
                while(DistanceBetweenCheckpoints > CurrentDistanceToCheckpoint && CurrentTValue < 1) 
                {
                    point2 = Beziercurve.getPosition(CurrentTValue);
                    
                    //Add the small distance to the current distance to checkpoint
                    CurrentDistanceToCheckpoint += Math.hypot(point2.X - point1.X, point2.Y - point1.Y);
                    
                    point1 = point2;
                    
                    if(DistanceBetweenCheckpoints > CurrentDistanceToCheckpoint)
                        CurrentTValue += Steplength;
                }
                if(CurrentTValue < 1) {
                    CheckPoints.add(point1.ConvertToCheckPoint(CurrentTValue));
                    CurrentDistanceToCheckpoint = 0;
                }
                else {
                    point1 = Beziercurve.getPosition(1);
                    CheckPoints.add(point1.ConvertToCheckPoint(CurrentTValue));
                }
            }
        }
        
        
        
        public CheckPoint[] toArray()
        {
            int AmountOfCheckPoints = CheckPoints.size();
            CheckPoint[] Checkpoints = new CheckPoint[AmountOfCheckPoints];
            
            for(int i = 0; i < AmountOfCheckPoints; i++){
                Checkpoints[i] = CheckPoints.get(i);
            }
            
            return Checkpoints;
        }
    //----------------------------------------------------------------
    //Methods
    //----------------------------------------------------------------    
    
}