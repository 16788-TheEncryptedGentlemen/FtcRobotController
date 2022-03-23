package org.firstinspires.ftc.RobotParts;

    import com.qualcomm.robotcore.hardware.DcMotor;
    import com.qualcomm.robotcore.hardware.HardwareMap;

public class Spinner {

        //---------------------------------------------------------------------------
    //Used variables
    // * DuckWheelMotor: The motor used to intake the motors
    //---------------------------------------------------------------------------
        public DcMotor DuckWheelMotor;
    //---------------------------------------------------------------------------
    //Used variables
    //---------------------------------------------------------------------------



    //---------------------------------------------------------------------------
    //Constructor
    //---------------------------------------------------------------------------
        public Spinner (HardwareMap hardwareMap)
        {
            DuckWheelMotor = hardwareMap.get(DcMotor.class, "DuckWheel");
    
           DuckWheelMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);  
            DuckWheelMotor.setDirection(DcMotor.Direction.REVERSE); 
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
        public void Turn(){
            DuckWheelMotor.setPower(29.0);
        }
        public void Stop()
        {
            DuckWheelMotor.setPower(0.0);
        }
    //---------------------------------------------------------------------------
    //Intake methods
    //---------------------------------------------------------------------------    
}