package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;


    public class Grabber {

        public CRServo left;
        public CRServo right;

        public Grabber(HardwareMap hardwareMap)
        {
            left = hardwareMap.get(CRServo.class, "LeftGrabber");
            right = hardwareMap.get(CRServo.class, "RightGrabber");
        }

        public void grab()
        {
            left.setPower(1);
            right.setPower(-1);
        }
        public void drop()
        {
            left.setPower(-1);
            right.setPower(1);
        }
        public void stop()
        {
            left.setPower(0.0);
            right.setPower(0.0);
        }

    }

// TODO: Aditi: Add documentation to this class