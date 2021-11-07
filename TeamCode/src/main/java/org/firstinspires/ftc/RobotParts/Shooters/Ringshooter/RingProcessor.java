package org.firstinspires.ftc.RobotParts.Shooters.Ringshooter;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import android.graphics.Color;

import org.firstinspires.ftc.OtherObjects.Timer;
import org.firstinspires.ftc.OtherObjects.Targets.Target;
import org.firstinspires.ftc.OtherObjects.Targets.TARGET_ENUM_CLASS.TARGET;




public class RingProcessor
{
    //-----------------------------------------------------------------
    //Used variables:
    // * target: The target enum that the robot needs to shoot at
    // * LiftServoRight: The right servo for the lift
    // * LiftServoLeft: The left servo for the lift
    // * LoaderServo: The loaderservo to load rings in the shooter
    // * PushButtonTop: The top push button
    // * PushButtonBottom: The bottom push button
    // * PushServoTimer: The timer used to push the rings in the shooter
    // * PushedPosition: The pushed position of the LoaderServo
    // * DefaultPosition: The default position of the LoaderServo
    // * State: The processing state of the LoaderServo
    //-----------------------------------------------------------------
        public Target target;
        
        public CRServo LiftServoRight;
        public CRServo LiftServoLeft;
        public Servo LoaderServo;
        public ColorSensor TopColorSensor;
            public float[] TophsvValues = {0F, 0F, 0F};   
            public final float[] Topvalues = TophsvValues;
            public final double SCALE_FACTOR = 255;
        
        public DigitalChannel PushButtonBottom;
        
        public Timer PushServoTimer = new Timer();
        public final double PushedPosition = 0;
        public final double DefaultPosition = 0.8;
        
        public enum PROCESSING_STATE
        {
            IDLE,
            MOVING,
            FINISHED
        }
        public PROCESSING_STATE State = PROCESSING_STATE.IDLE;    
    //-----------------------------------------------------------------
    //Used variables:    
    //-----------------------------------------------------------------
    
    
    
    
    
    //-----------------------------------------------------------------
    //Constructor   
    //-----------------------------------------------------------------
        public RingProcessor(HardwareMap hardwaremap, Target _target)
        {
            target = _target;
            LiftServoRight = hardwaremap.get(CRServo.class, "RightLiftCRServo");
            LiftServoLeft = hardwaremap.get(CRServo.class, "LeftLiftCRServo");
            LoaderServo = hardwaremap.get(Servo.class, "Loader");
            
            TopColorSensor = hardwaremap.get(ColorSensor.class, "TopColorSensor"); 
            
            PushButtonBottom = hardwaremap.get(DigitalChannel.class, "PushSensorBottom");
            PushButtonBottom.setMode(DigitalChannel.Mode.INPUT);
        }
    //-----------------------------------------------------------------
    //Constructor   
    //-----------------------------------------------------------------    
    
    
    
    
    
    //------------------------------------------------------
    //Lift system: 
    // * PowerLift(): Powers lift to go up or down with a certain power value
    // * MoveLiftUp(): Moves lift up until the push button at the top is pressed
    // * MoveLiftDown(): Moves lift down until the push button at the down is pressed
    // * StopLift(): Stops the lift from moving
    // * isLiftAtTop: Returns true if the lift is at the top
    //------------------------------------------------------
        public void updateColorSens()
        {
            Color.RGBToHSV((int) (TopColorSensor.red() * SCALE_FACTOR),
                            (int) (TopColorSensor.green() * SCALE_FACTOR),
                            (int) (TopColorSensor.blue() * SCALE_FACTOR),
                            TophsvValues);
        }
        
        public void PowerLift(double Power)
        {
            LiftServoRight.setPower(-Power);
            LiftServoLeft.setPower(Power);            
        }
        
        public void StopLift()
        {
            PowerLift(0.0);
        }        
        
        public void MoveLiftUp()
        {
            updateColorSens();
            
            if(!isLiftAtTop())
                PowerLift(1.0);
            else
                StopLift();
        }
        
        public void MoveLiftDown()
        {
            if(PushButtonBottom.getState())
                PowerLift(-1.0);
            else
                StopLift();
        }
        
        public boolean isLiftAtTop()
        {
            return TophsvValues[0] < 50;
        }
    //------------------------------------------------------
    //Lift system
    //------------------------------------------------------   
    
    
    
    
    
    //------------------------------------------------------
    //PushRingsInShooter():
    // * This method is called as soon as we are ready to shoot. This method pushes
    // * each ring into the shooter in a timed sequence
    //------------------------------------------------------ 
        public void PushRingsInShooter()
        {
            if(State == PROCESSING_STATE.IDLE)
            {
                PushServoTimer.Reset();
                State = PROCESSING_STATE.MOVING;
            }
                
            if(PushServoTimer.isBetween(0, 1.0))
                LoaderServo.setPosition(PushedPosition);           //Push first ring
            else if(PushServoTimer.isBetween(1.0, 2.0))  
                LoaderServo.setPosition(DefaultPosition);           //Set servo back 
            else if(PushServoTimer.isBetween(2.0, 3.0))  
                LoaderServo.setPosition(PushedPosition);           //Push second ring
            else if(PushServoTimer.isBetween(3.0, 4.0))  
                LoaderServo.setPosition(DefaultPosition);           //Set servo back 
            else if(PushServoTimer.isBetween(4.0, 5.0))  
                LoaderServo.setPosition(PushedPosition);           //Push third ring 
            else if(PushServoTimer.isBetween(5.0, 6.0))  
                LoaderServo.setPosition(DefaultPosition);           //Set servo back 
            else if(PushServoTimer.getTime() > 6.0)
                State = PROCESSING_STATE.FINISHED;
        }
    //------------------------------------------------------
    //Pushing rings in the shooter
    //------------------------------------------------------
}
