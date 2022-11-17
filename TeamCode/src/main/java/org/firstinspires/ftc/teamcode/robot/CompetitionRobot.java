package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Display;
import org.firstinspires.ftc.teamcode.robotparts.Grabber;
import org.firstinspires.ftc.teamcode.robotparts.Imu;
import org.firstinspires.ftc.teamcode.robotparts.Lift;
import org.firstinspires.ftc.teamcode.robotparts.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.robotparts.Odometry;
import org.firstinspires.ftc.teamcode.robotparts.Webcam;

/** Contains all the robot parts. */
public class CompetitionRobot {
    /** The drivetrain of the robot. */
    public MecanumDrivetrain drivetrain;
    /** The odometry of the robot. */
    public Odometry odometry;
    /** The imu of the robot. */
    public Imu imu;
    /** The display of the robot. */
    public Display display;
    /** The webcam of the robot. */
    public Webcam webcam;
    /** The grabber of the robot. */
    public Grabber grabber;
    /** The lift of the robot */
    public Lift lift;

    /** The constructor for a LinearOpMode program. */
    public CompetitionRobot(LinearOpMode runningLinearOpMode) {
        HardwareMap hardwaremap = runningLinearOpMode.hardwareMap;
        display = new Display(runningLinearOpMode.telemetry);

        display.showLoading("odometry");
        odometry = new Odometry(hardwaremap);
        display.showLoading("imu");
        imu = new Imu(hardwaremap);
        display.showLoading("drivetrain");
        drivetrain = new MecanumDrivetrain(hardwaremap, odometry, imu);
        display.showLoading("webcam");
        webcam = new Webcam(hardwaremap);
        display.showLoading("Done initializing! Press start to play");
    }

    /** The constructor for an OpMode program. */
    public CompetitionRobot(OpMode opMode) {
        HardwareMap hardwaremap = opMode.hardwareMap;
        display = new Display(opMode.telemetry);

        display.showLoading("odometry");
        odometry = new Odometry(hardwaremap);
        display.showLoading("imu");
        imu = new Imu(hardwaremap);
        display.showLoading("drivetrain");
        drivetrain = new MecanumDrivetrain(hardwaremap, odometry, imu);
        display.showLoading("grabber");
        grabber = new Grabber(hardwaremap);
        display.showLoading("lift");
        lift = new Lift(hardwaremap);
        display.showLoading("Done initializing! Press start to play");
    }

}