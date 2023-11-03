package org.firstinspires.ftc.teamcode.robots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.otherclasses.Display;
import org.firstinspires.ftc.teamcode.robotparts.Arm;
import org.firstinspires.ftc.teamcode.robotparts.GrabberLeft;
import org.firstinspires.ftc.teamcode.robotparts.GrabberRight;
import org.firstinspires.ftc.teamcode.robotparts.Odometry;
import org.firstinspires.ftc.teamcode.robotparts.Webcam;
import org.firstinspires.ftc.teamcode.robotparts.Imu;
import org.firstinspires.ftc.teamcode.robotparts.MecanumDrivetrain;

/** Contains all the robot parts. */
public class CompetitionRobot {

    /** The webcam of the robot. */
    public Webcam webcam;
    public Display display;
    public Imu  imu;
    public Odometry odometry;
    public MecanumDrivetrain drivetrain;
    public Arm  arm;
    public GrabberLeft grabberLeft;
    public GrabberRight grabberRight;



    /** The constructor for a LinearOpMode program. */
    public CompetitionRobot(LinearOpMode runningLinearOpMode) {
        HardwareMap hardwaremap = runningLinearOpMode.hardwareMap;
        display = new Display(runningLinearOpMode.telemetry);

        display.showLoading("webcam");
        webcam = new Webcam(hardwaremap);
        display.showLoading("imu");
        imu = new Imu(hardwaremap);
        display.showLoading("drivetrain");
        drivetrain = new MecanumDrivetrain(runningLinearOpMode, odometry, imu);
        display.showLoading("odometry");
        odometry = new Odometry(hardwaremap);
        display.showLoading("Done initializing! Press start to play");
    }
    public CompetitionRobot(OpMode opMode) {
        HardwareMap hardwaremap = opMode.hardwareMap;
        display = new Display(opMode.telemetry);

       // display.showLoading("imu");
        imu = new Imu(hardwaremap);
       // display.showLoading("drivetrain");
        drivetrain = new MecanumDrivetrain(hardwaremap, odometry, imu);
       // display.showLoading("grabberLeft");
        grabberLeft = new GrabberLeft(hardwaremap);
        //display.showLoading("grabberRight");
        grabberRight = new GrabberRight(hardwaremap);
       // display.showLoading("arm");
        arm = new Arm(hardwaremap);
       // display.showLoading("Done initializing! Press start to play");
    }
}