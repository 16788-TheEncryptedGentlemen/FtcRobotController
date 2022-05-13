package org.firstinspires.ftc.RobotParts;

    import com.qualcomm.robotcore.hardware.DcMotor;
    import com.qualcomm.robotcore.hardware.HardwareMap;

public class DuckWheel {

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
    public DuckWheel (HardwareMap hardwareMap)
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
    // * TurnRight(): The DuckWheel turns to the right.
    // * TurnLeft(): The DuckWheel turns to the left.
    // * Stop(): The DuckWheel stops.
    //---------------------------------------------------------------------------
    public void TurnRight(){
        DuckWheelMotor.setPower(1.0);
    }
    public void TurnLeft(){
        DuckWheelMotor.setPower(-1.0);
    }
    public void Stop()
    {
        DuckWheelMotor.setPower(0.0);
    }
    //---------------------------------------------------------------------------
    //Intake methods
    //---------------------------------------------------------------------------
}