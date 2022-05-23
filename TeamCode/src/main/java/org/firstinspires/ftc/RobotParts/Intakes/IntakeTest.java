package org.firstinspires.ftc.RobotParts.Intakes;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeTest {

    //---------------------------------------------------------------------------
    //Used variables
    // * IntakeMotor: The motor used to intake the motors
    //---------------------------------------------------------------------------
    public DcMotorEx IntakeTestMotor;
    //---------------------------------------------------------------------------
    //Used variables
    //---------------------------------------------------------------------------





    //---------------------------------------------------------------------------
    //Constructor
    //---------------------------------------------------------------------------
    public IntakeTest (HardwareMap hardwareMap)
    {
        IntakeTestMotor = hardwareMap.get(DcMotorEx.class, "TestIntake");

        IntakeTestMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        IntakeTestMotor.setDirection(DcMotorEx.Direction.REVERSE);
    }
    //---------------------------------------------------------------------------
    //Constructor
    //---------------------------------------------------------------------------





    //---------------------------------------------------------------------------
    //Intake methods:
    // * In(): Takes the rings into the robot. This method has two isomorphic methods:
    // * - No arguments: The intake motor turns at maximum speed to take in rings
    // * - One double argument: the motor is powered at a specific speed to take in rings
    // * Out(): Spits the rings out of the robot
    // * - No arguments: The intake motor turns at maximum speed to spit out rings
    // * - One double argument: The motor is powered at a specific speed to spit out rings
    //---------------------------------------------------------------------------
    public void In(){
        IntakeTestMotor.setPower(-1.0);
    }
    public void In(double power)
    {
        IntakeTestMotor.setPower(-power);
    }

    public void Out(){
        IntakeTestMotor.setPower(1.0);
    }
    public void Out(double power)
    {
        IntakeTestMotor.setPower(power);
    }

    public void Stop()
    {
        IntakeTestMotor.setPower(0.0);
    }
    //---------------------------------------------------------------------------
    //Intake methods
    //---------------------------------------------------------------------------
}
