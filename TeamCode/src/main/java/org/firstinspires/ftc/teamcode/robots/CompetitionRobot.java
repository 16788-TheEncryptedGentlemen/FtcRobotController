package org.firstinspires.ftc.teamcode.robots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robotparts.Webcam;

/** Contains all the robot parts. */
public class CompetitionRobot {

    /** The webcam of the robot. */
    public Webcam webcam;

    /** The constructor for a LinearOpMode program. */
    public CompetitionRobot(LinearOpMode runningLinearOpMode) {
        HardwareMap hardwaremap = runningLinearOpMode.hardwareMap;

        display.showLoading("webcam");
        webcam = new Webcam(hardwaremap);
        display.showLoading("Done initializing! Press start to play");
    }

}