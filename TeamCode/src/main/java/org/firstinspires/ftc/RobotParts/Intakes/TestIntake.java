package org.firstinspires.ftc.RobotParts.Intakes;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TestIntake {

    //---------------------------------------------------------------------------
    //Used variables
    // * IntakeMotor: The motor used to intake the motors
    //---------------------------------------------------------------------------
        public DcMotorEx IntakeMotorL;
        public DcMotorEx IntakeMotorR;
    //---------------------------------------------------------------------------
    //Used variables
    //---------------------------------------------------------------------------





    //---------------------------------------------------------------------------
    //Constructor
    //---------------------------------------------------------------------------
        public TestIntake(HardwareMap hardwareMap)
        {
            IntakeMotorL = hardwareMap.get(DcMotorEx.class, "IntakeL");
            IntakeMotorR = hardwareMap.get(DcMotorEx.class, "IntakeR");
             
            IntakeMotorL.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);  
            IntakeMotorL.setDirection(DcMotorEx.Direction.REVERSE); 
            IntakeMotorR.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);  
            IntakeMotorR.setDirection(DcMotorEx.Direction.REVERSE); 
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
            IntakeMotorL.setPower(-1.0);
            IntakeMotorR.setPower(-1.0);
        }
        public void Slurp(double power)
        {
            IntakeMotorL.setPower(-power);
            IntakeMotorR.setPower(-power);
        }
        
        public void Bleh(){
            IntakeMotorL.setPower(1.0);
            IntakeMotorR.setPower(1.0);
        }
        public void Bleh(double power)
        {
            IntakeMotorL.setPower(power);
            IntakeMotorR.setPower(power);
        }
        
        public void Stop()
        {
            IntakeMotorL.setPower(0.0);
            IntakeMotorR.setPower(0.0);
        }
    //---------------------------------------------------------------------------
    //Intake methods
    //---------------------------------------------------------------------------    
}
