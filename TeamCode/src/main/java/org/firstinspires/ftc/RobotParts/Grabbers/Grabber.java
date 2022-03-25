package org.firstinspires.ftc.RobotParts.Grabbers;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Grabber {

    public CRServo Left;
    public CRServo Right;

    public Grabber(HardwareMap hardwareMap)
    {
        Left = hardwareMap.get(CRServo.class, "LeftGrabber");
        Right = hardwareMap.get(CRServo.class, "RightGrabber");
    }

    public void Grab()
    {
        Left.setPower(1);
        Right.setPower(-1);
    }
    public void Drop()
    {
        Left.setPower(-1);
        Right.setPower(1);
    }
    public void StopGrabber()
    {
        Left.setPower(0.0);
        Right.setPower(0.0);
    }

}
