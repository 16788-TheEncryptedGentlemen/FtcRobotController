package org.firstinspires.ftc.RobotParts.Shooters.Ringshooter;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.robotcore.external.State;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.OtherObjects.Targets.Target;
import org.firstinspires.ftc.OtherObjects.Targets.TARGET_ENUM_CLASS.TARGET;



public class Shooter
{
    //------------------------------------------------------------------------
    //Used variables:    
    // * ShootMotor: The shooting motor
    // * target: The target of the robot
    // * PIDFCoeff: The PIDF coefficients for the ShootMotor
    // * VelocityHighGoal: The velocity of the motor to shoot the high goal
    // * VelocityPowerShot: The velocity of the motor to shoot the power shot
    // * WarmupTimeHighGoal: The amount of time it takes on average for the ShootMotor to warm up to VelocityHighGoal
    // * WarmupTimePowerShot: The amount of time it takes on average for the ShootMotor to warm up to VelocityPowerShot
    // * ShooterReadyStateMargin: The margin the motor velocity has before it is concidered on speed
    // * State: The shooter state which is either: IDLE, PREPARING or READY
    //------------------------------------------------------------------------
        public DcMotorEx ShootMotor;
        public Target target;
        private PIDFCoefficients PIDFCoeff = new PIDFCoefficients(1000, 300, 0, 200); 
        public double VelocityHighGoal = 1700;  
        public double VelocityPowerShot = 1300;     
        public final double WarmupTimeHighGoal = 1.5;
        public final double WarmupTimePowerShot = 1.0;
        private int ShooterReadyStateMargin = 20;
        
        public enum SHOOTER_STATE
        {
            IDLE,
            PREPARING,
            READY;
        }
        public SHOOTER_STATE State = SHOOTER_STATE.IDLE;    
    //------------------------------------------------------------------------
    //Used variables   
    //------------------------------------------------------------------------
    
    
    
    
    
    //------------------------------------------------------------------------
    //Constructor
    //------------------------------------------------------------------------
    public Shooter(HardwareMap hardwaremap, Target _target)
    {
        target = _target;
        
        ShootMotor = hardwaremap.get(DcMotorEx.class, "Shooter");
        //ShootMotor.setPIDFCoefficients(DcMotorEx.RunMode.RUN_USING_ENCODERS, PIDFCoeff);
        ShootMotor.setDirection(DcMotorEx.Direction.REVERSE);
    }
    //------------------------------------------------------------------------
    //Constructor
    //------------------------------------------------------------------------    
    
    
    
    
    
    //-------------------------------------------------------------
    //Prepare shooting and set/get velocity:
    // * PrepareForShooting(): Sets velocity with the associated target to the ShootMotor. It also updates the State of the shooter 
    // * updateState(): Updates the State of the motor (to IDLE, PREPARING or READY)
    // * setVelocity(): Sets velocity of the motor
    // * getVelocity(): Gets velocity of the motor
    // * Stop(): Stops the motor
    //-------------------------------------------------------------
        public void PrepareForShooting()
        {
            updateState();
            
            if(target.equals(TARGET.HIGH_GOAL))
                setVelocity(VelocityHighGoal);
            else if(target.equals(TARGET.POWER_SHOT))
                setVelocity(VelocityPowerShot);
        }
        
        
        
        public void updateState()
        {
            if(!ShootMotor.isMotorEnabled())
                State = SHOOTER_STATE.IDLE;
            
            if(target.equals(TARGET.HIGH_GOAL))
            {
                if(getVelocity() >= VelocityHighGoal - ShooterReadyStateMargin)
                    State = SHOOTER_STATE.READY;  
                else
                    State = SHOOTER_STATE.PREPARING;                
            }
            else if(target.equals(TARGET.POWER_SHOT))
            {
                if(getVelocity() >= VelocityPowerShot - ShooterReadyStateMargin)
                    State = SHOOTER_STATE.READY;
                else
                    State = SHOOTER_STATE.PREPARING;            
            }            
        }    
    
    
    
        public void setVelocity(double Velocity)
        {
            ShootMotor.setVelocity(Velocity);
        }
    
        public double getVelocity()
        {
            return ShootMotor.getVelocity();
        }
        
        public void Stop()
        {
            ShootMotor.setVelocity(0);
            State = SHOOTER_STATE.IDLE;
        }
    //-------------------------------------------------------------
    //Prepare shooting and set/get velocity
    //-------------------------------------------------------------
 
}
