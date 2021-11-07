package org.firstinspires.ftc.RobotParts.Shooters.Ringshooter;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.RobotParts.Drivetrains.MecanumDrivetrain;
import org.firstinspires.ftc.RobotParts.Shooters.Ringshooter.Shooter;
import org.firstinspires.ftc.RobotParts.Shooters.Ringshooter.RingProcessor;
import org.firstinspires.ftc.OtherObjects.Timer;
import org.firstinspires.ftc.OtherObjects.Targets.Target;
import org.firstinspires.ftc.OtherObjects.Targets.TARGET_ENUM_CLASS.TARGET;
import org.firstinspires.ftc.RobotParts.Shooters.Ringshooter.RingProcessor.PROCESSING_STATE;
import org.firstinspires.ftc.RobotParts.Shooters.Ringshooter.Shooter.SHOOTER_STATE;



public class RingShooter
{
    //------------------------------------------------------------------------
    //Used variables:
    // * Drivetrain: The drivetrain of the robot for autonomous inputs
    // * shooter: The actual shooter of the entire system
    // * Ringprocessor: The ringprocessor to process the rings in the robot
    // * target: The desired target we want the robot to shoot at
    // * CommitTimer: A backup timer to commit the Ringprocessor to start processing the rings
    // * isShooting: A boolean that is true if the robot is shooting rings
    //------------------------------------------------------------------------
        private MecanumDrivetrain Drivetrain;
        public Shooter shooter;
        public RingProcessor Ringprocessor;
        public Target target = new Target(TARGET.NONE);
        public Timer CommitTimer = new Timer();  
        public boolean isShooting = false; 
    //------------------------------------------------------------------------
    //Used variables
    //------------------------------------------------------------------------
    
    
    
    
      
    //------------------------------------------------------------------------
    //Constructor 
    //------------------------------------------------------------------------
        public RingShooter(HardwareMap hardwareMap, MecanumDrivetrain _Drivetrain)
        {
            Drivetrain = _Drivetrain;
            shooter = new Shooter(hardwareMap, target);
            Ringprocessor = new RingProcessor(hardwareMap, target);
        }
    //------------------------------------------------------------------------
    //Constructor
    //------------------------------------------------------------------------





    //------------------------------------------------------------------
    //Preparing, stop and get velocity of the ShootMotor:
    // * Prepare(): prepares robot to shoot by moving the lift up and warming up the shooter
    // * StopPreparing(): Stops lift from moving and shooter from warming up
    // * StartShooting(): Starts shooting rings (should be called when y is pressed)
    // * Stop(): Cancels shooting and processing. Control is returned to the driver player
    // * getVelocity(): gets velocity (double) of the shooter motor
    //------------------------------------------------------------------ 
        public void Prepare()
        {
            //Ringprocessor.MoveLiftUp();
            shooter.PrepareForShooting();
        }
        
        public void StopPreparing()
        {
            //Ringprocessor.StopLift();
            shooter.Stop();
        }
        
        public void StartShooting()
        {
            isShooting = true;
            CommitTimer.Reset();
            this.Prepare();          
        }
        
        public void Stop()
        {
            isShooting = false;
            Ringprocessor.State = PROCESSING_STATE.IDLE; 
            Ringprocessor.LoaderServo.setPosition(Ringprocessor.DefaultPosition);
            shooter.Stop();
        }     
        
        public double getVelocity()
        {
            return shooter.getVelocity();
        }
    //------------------------------------------------------------------
    //Preparing to shoot, stop and get velocity of the ShootMotor
    //------------------------------------------------------------------ 





    //------------------------------------------------------------------
    //Shoot rings methods:
    // * ShootRings(): The method that is called to shoot rings (either high goal or power shot. This is determined by which target type is set)
    // * ShootHighGoal(): Shoots rings in the high goal 
    // * ShootPowerShots(): Shoots the rings to the power shots and moves the robot accordingly
    //------------------------------------------------------------------    
        public void ShootRings()
        {
            if(target.equals(TARGET.HIGH_GOAL))
                ShootHighGoal();
            if(target.equals(TARGET.POWER_SHOT))
                ShootPowerShots();
        }
        
        
        
        public void ShootHighGoal()
        {
            Prepare();
    
            if((shooter.State == SHOOTER_STATE.READY && Ringprocessor.isLiftAtTop()) || CommitTimer.getTime() > shooter.WarmupTimeHighGoal)
                Ringprocessor.PushRingsInShooter();
                
            if(Ringprocessor.State == PROCESSING_STATE.FINISHED) //If the sequence is finished, stop the shooter
                this.Stop();
        }
        
        
        
        public void ShootPowerShots()
        {
            Prepare();
            
            if(Ringprocessor.State == PROCESSING_STATE.IDLE)
                Drivetrain.TurnRobotNoLoop(-5);
            
            if((shooter.State == SHOOTER_STATE.READY && Ringprocessor.isLiftAtTop()) || CommitTimer.getTime() > shooter.WarmupTimePowerShot)
            {
                Ringprocessor.PushRingsInShooter();        
                
                if(Ringprocessor.PushServoTimer.isBetween(0.3, 1.1))
                    Drivetrain.TurnRobotNoLoop(0);
                else if(Ringprocessor.PushServoTimer.isBetween(1.1, 1.9))
                    Drivetrain.TurnRobotNoLoop(5);
                else
                    Drivetrain.setPower(new double[] {0,0,0,0});
            }
            
            if(Ringprocessor.State == PROCESSING_STATE.FINISHED) //If the sequence is done, stop the shooter
                this.Stop();
        }    
    //------------------------------------------------------------------
    //Shoot rings methods
    //------------------------------------------------------------------    

    
}
