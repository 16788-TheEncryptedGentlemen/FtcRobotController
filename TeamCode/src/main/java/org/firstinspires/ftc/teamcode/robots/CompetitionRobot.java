package org.firstinspires.ftc.teamcode.robots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.otherclasses.Display;
import org.firstinspires.ftc.teamcode.robotparts.Arm;
import org.firstinspires.ftc.teamcode.robotparts.Grabber;
import org.firstinspires.ftc.teamcode.robotparts.Imu;
import org.firstinspires.ftc.teamcode.robotparts.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.robotparts.Odometry;
import org.firstinspires.ftc.teamcode.robotparts.Pusher;
import org.firstinspires.ftc.teamcode.robotparts.TiltMechanism;
import org.firstinspires.ftc.teamcode.robotparts.Webcam;
import org.firstinspires.ftc.teamcode.robotparts.DroneLauncher;
import org.firstinspires.ftc.teamcode.robotparts.PixelWheel;
import org.firstinspires.ftc.teamcode.robotparts.HangMechanism;

/** Contains all the robot parts. */
public class CompetitionRobot {

    public Display display;
    public Webcam webcam;
    public Imu imu;
    public Odometry odometry;
    public MecanumDrivetrain drivetrain;
    public Grabber grabber;
    public Pusher pusher;
    public TiltMechanism tiltMechanism;
    public Arm arm;
    public DroneLauncher droneLauncher;
    public PixelWheel pixelWheel;
    public HangMechanism hangMechanism;

    /** The constructor for a LinearOpMode program. */
    public CompetitionRobot(LinearOpMode runningLinearOpMode) {
        display = new Display(runningLinearOpMode.telemetry);
        HardwareMap hardwaremap = runningLinearOpMode.hardwareMap;

        display.showLoading("webcam");
        webcam = new Webcam(hardwaremap);
        display.showLoading("imu");
        imu = new Imu(hardwaremap);
        display.showLoading("odometry");
        odometry = new Odometry(hardwaremap);
        display.showLoading("drivetrain");
        drivetrain = new MecanumDrivetrain(runningLinearOpMode, odometry, imu);
        display.showLoading("grabber");
        grabber = new Grabber(hardwaremap);
        display.showLoading("pusher");
        pusher = new Pusher(hardwaremap);
        display.showLoading("tiltMechanism");
        tiltMechanism = new TiltMechanism(hardwaremap);
        display.showLoading("arm");
        arm = new Arm(hardwaremap);
        display.showLoading("Done initializing! Press start to play");
    }

    public CompetitionRobot(OpMode opMode) {
        display = new Display(opMode.telemetry);
        HardwareMap hardwaremap = opMode.hardwareMap;

        display.showLoading("webcam");
        webcam = new Webcam(hardwaremap);
        display.showLoading("imu");
        imu = new Imu(hardwaremap);
        display.showLoading("odometry");
        odometry = new Odometry(hardwaremap);
        display.showLoading("drivetrain");
        drivetrain = new MecanumDrivetrain(hardwaremap, odometry, imu);
        display.showLoading("grabber");
        grabber = new Grabber(hardwaremap);
        display.showLoading("pusher");
        pusher = new Pusher(hardwaremap);
        display.showLoading("tiltMechanism");
        tiltMechanism = new TiltMechanism(hardwaremap);
        display.showLoading("arm");
        arm = new Arm(hardwaremap);
        display.showLoading("pixelGrabber");
        droneLauncher = new DroneLauncher(hardwaremap);
        display.showLoading("droneLauncher");
        pixelWheel = new PixelWheel(hardwaremap);
        display.showLoading("pixelWheel");
        hangMechanism = new HangMechanism(hardwaremap);
        display.showLoading("hangMechanism");
        display.showLoading("Done initializing! Press start to play");
    }
}
