package org.firstinspires.ftc.teamcode.robots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.otherclasses.Display;
import org.firstinspires.ftc.teamcode.robotparts.Arm2Motor;
import org.firstinspires.ftc.teamcode.robotparts.BlockGrabber;
import org.firstinspires.ftc.teamcode.robotparts.Imu;
import org.firstinspires.ftc.teamcode.robotparts.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.robotparts.Odometry;
import org.firstinspires.ftc.teamcode.robotparts.Webcam;
import org.firstinspires.ftc.teamcode.robotparts.SliderGrabber;

/** Contains all the robot parts. */
public class CompetitionRobot {

    public Display display;
    public Webcam webcam;
    public Imu imu;
    public Odometry odometry;
    public MecanumDrivetrain drivetrain;
    public BlockGrabber grabber;
    public SliderGrabber sliderGrabber;
    public Arm2Motor arm;


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
        grabber = new BlockGrabber(hardwaremap);
        display.showLoading("arm");
        arm = new Arm2Motor(hardwaremap);
        display.showLoading("slidergrabber");
        sliderGrabber = new SliderGrabber(hardwaremap);
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
        grabber = new BlockGrabber(hardwaremap);
        display.showLoading("arm");
        arm = new Arm2Motor(hardwaremap);
        display.showLoading("slidergrabber");
        sliderGrabber = new SliderGrabber(hardwaremap);
        display.showLoading("Done initializing! Press start to play");
    }
}
