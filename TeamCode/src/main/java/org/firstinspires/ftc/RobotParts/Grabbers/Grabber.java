package org.firstinspires.ftc.RobotParts.Grabbers;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Grabber {

    public CRServo Left;
    public CRServo Right;

    public Grabber(HardwareMap hardwareMap)
    {
        Left = hardwareMap.get(CRServo.class, "Left");
        Right = hardwareMap.get(CRServo.class, "Right");
    }

    public void Grab()
    {
        Left.setPower(0.5);
        Right.setPower(-0.5);
    }
    public void Drop()
    {
        Left.setPower(-0.5);
        Right.setPower(0.5);
    }
    public void StopGrabber()
    {
        Left.setPower(0.0);
        Right.setPower(0.0);
    }

}
