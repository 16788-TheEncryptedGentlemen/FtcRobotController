package org.firstinspires.ftc.RobotParts.Grabbers;

        import com.qualcomm.robotcore.hardware.Servo;
        import com.qualcomm.robotcore.hardware.HardwareMap;

public class GrabberIntake {

    //---------------------------------------------------------------------------
    //Used variables
    // * IntakeMotor: The motor used to intake the motors
    //---------------------------------------------------------------------------
    public Servo IntakeServo;
    //---------------------------------------------------------------------------
    //Used variables
    //---------------------------------------------------------------------------





    //---------------------------------------------------------------------------
    //Constructor
    //---------------------------------------------------------------------------
    public GrabberIntake(HardwareMap hardwareMap)
    {
        IntakeServo = hardwareMap.get(Servo.class, "Intake");

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
    public void In(){
        IntakeServo.setPosition(-0.4);
    }
    public void In(double power)
    {
        IntakeServo.setPosition(-power);
    }

    public void Out(){
        IntakeServo.setPosition(0.4);
    }
    public void Out(double power)
    {
        IntakeServo.setPosition(power);
    }

    public void Stop()
    {
        IntakeServo.setPosition(0.0);
    }
    //---------------------------------------------------------------------------
    //Intake methods
    //---------------------------------------------------------------------------
}
