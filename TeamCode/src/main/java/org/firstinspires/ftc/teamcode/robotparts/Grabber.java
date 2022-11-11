package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;


    public class Grabber {

        public CRServo left;
        public CRServo right;

       /** The grabber has a right and left CRservo. */
        public Grabber(HardwareMap hardwareMap)
        {
            left = hardwareMap.get(CRServo.class, "LeftGrabber");
            right = hardwareMap.get(CRServo.class, "RightGrabber");
        }

        /** The robot grabber closes */
        public void grab()
        {
            left.setPower(1);
            right.setPower(-1);
        }
        /** The robot grabber open */
        public void drop()
        {
            left.setPower(-1);
            right.setPower(1);
        }
        /** The robot grabber stops. */
        public void stop()
        {
            left.setPower(0.0);
            right.setPower(0.0);
        }

    }

// TODO: Aditi: Add documentation to this class