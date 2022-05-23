package org.firstinspires.ftc.RobotParts.Odometry;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;



public class OdometryOmniWheels 
{
    //-----------------------------------------------------------
    //Used Variables:
    // * CountsPerRotation: Counts per rotation of the encoder
    // * DiameterOdometryWheel: Diameter of the odometry wheels in cm
    // * CmPerCount: Amount of cm that the robot drives in one count
    // * VerticalCorrection: A correction value for the vertical odometry wheel
    // * HorizontalCorrection: A correction value for the horizontal odometry wheel
    // * Ywheel: The odometry wheel in the vertical direction
    // * Xwheel: The odometry wheel in the horizontal direction
    //-----------------------------------------------------------    
        public final int CountsPerRotation = 8192;         
        public final double DiameterOdometryWheel = 5.0;  
        public final double CmPerCount = (DiameterOdometryWheel*Math.PI)/CountsPerRotation;             
    
        public final double VerticalCorrection = 1.055;     
        public final double HorizontalCorrection = 1.02967;   
    
        private DcMotorEx Ywheel;
        private DcMotorEx Xwheel;
    //-----------------------------------------------------------
    //Used Variables:    
    //-----------------------------------------------------------
    
    
    
    
    
    //-----------------------------------------------------------
    //Constructor
    //-----------------------------------------------------------
        public OdometryOmniWheels(HardwareMap hardwareMap)
        {
            Xwheel = hardwareMap.get(DcMotorEx.class, "EncoderX");
            Ywheel = hardwareMap.get(DcMotorEx.class, "EncoderY");
            
            //resets encoders
            Ywheel.setMode(DcMotorEx.RunMode.RESET_ENCODERS);
            Xwheel.setMode(DcMotorEx.RunMode.RESET_ENCODERS);
            
            //Runs encoders on both wheels
            Ywheel.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);  
            Xwheel.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            
            //Reverses Ywheel, because the encoder is mounted in reverse
            Ywheel.setDirection(DcMotorEx.Direction.REVERSE); 
        }
    //-----------------------------------------------------------
    //Constructor
    //-----------------------------------------------------------    
    
    
    
    
    
    //-----------------------------------------------------------
    //Position methods:
    // * getY(): Returns the y position of the odometry
    // * getX(): Returns the x position of the odometry
    // * getDistanceFromStart(): Gets distance from the original starting position
    //-----------------------------------------------------------
        public double getY()
        {
            return CmPerCount * Ywheel.getCurrentPosition() * VerticalCorrection; 
        }
        public double getX()
        {
            return -CmPerCount * Xwheel.getCurrentPosition() * HorizontalCorrection; 
        }
        
        public double getDistanceFromStart()
        {
            return Math.hypot(this.getY(), this.getX());
        }
    //-----------------------------------------------------------
    //Position methods
    //-----------------------------------------------------------
    
    
    
    
    
    //-----------------------------------------------------------  
    //Other methods:
    // * Reset(): Resets the encoders
    //-----------------------------------------------------------
        public void Reset()
        {
            Ywheel.setMode(DcMotorEx.RunMode.RESET_ENCODERS);
            Xwheel.setMode(DcMotorEx.RunMode.RESET_ENCODERS);
            
            Ywheel.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);   
            Xwheel.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        }
    //-----------------------------------------------------------  
    //Other methods
    //-----------------------------------------------------------  
    
    
}
