package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

    /** The object Grabber. */
    public class Grabber {

        /** The Left CRservo of the Grabber. */
        public Servo left;
        /** The right CRservo of the Grabber. */
        public Servo right;


       /** The grabber has a right and left Servo. */
        public Grabber(HardwareMap hardwareMap)
        {
            left = hardwareMap.get(Servo.class, "LeftGrabber");
            right = hardwareMap.get(Servo.class, "RightGrabber");

        }

        /** The robot grabber closes. */
        public void grab()
        {
            left.setPosition(0);
            right.setPosition(1);
        }
        /** The robot grabber opens. */
        public void drop()
        {
            left.setPosition(0.6);
            right.setPosition(0.2);
        }
        /** The robot grabber stops. */
        public void stop()
        {
            left.setPosition(0.0);
            right.setPosition(0.0);
        }

    }
