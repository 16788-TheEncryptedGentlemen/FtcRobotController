package org.firstinspires.ftc.RobotParts.Intakes;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class CompliantIntake {

    //---------------------------------------------------------------------------
    //Used variables
    // * IntakeMotor: The motor used to intake the motors
    //---------------------------------------------------------------------------
        public DcMotorEx IntakeMotor;
    //---------------------------------------------------------------------------
    //Used variables
    //---------------------------------------------------------------------------





    //---------------------------------------------------------------------------
    //Constructor
    //---------------------------------------------------------------------------
        public CompliantIntake(HardwareMap hardwareMap)
        {
            IntakeMotor = hardwareMap.get(DcMotorEx.class, "Intake");
    
            IntakeMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);  
            IntakeMotor.setDirection(DcMotorEx.Direction.REVERSE); 
        }
    //---------------------------------------------------------------------------
    //Constructor
    //---------------------------------------------------------------------------    
    
    
    
    

    //---------------------------------------------------------------------------
    //Intake methods:
    // * Slurp(): Slurps the rings into the robot. This method has two isomorphic methods:
    // * - No arguments: The intake motor turns at maximum speed to take in rings
    // * - One double argument: the motor is powered at a specific speed to take in rings
    // * Bleh(): Spits the rings out of the robot
    // * - No arguments: The intake motor turns at maximum speed to spit out rings
    // * - One double argument: The motor is powered at a specific speed to spit out rings
    //---------------------------------------------------------------------------    
        public void Slurp(){
            IntakeMotor.setPower(1.0);
        }
        public void Slurp(double power){
            IntakeMotor.setPower(power);
        }
        
        
        
        public void Bleh(){
            IntakeMotor.setPower(-1.0);
        }
        public void Bleh(double power){
            IntakeMotor.setPower(power);
        }
        
        public void Stop(){
            IntakeMotor.setPower(0.0);
        }
        
        public void SlurpHalf(){
            IntakeMotor.setPower(0.5);
        }
    //---------------------------------------------------------------------------
    //Intake methods
    //---------------------------------------------------------------------------    
}
