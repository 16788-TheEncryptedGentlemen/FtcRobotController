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
import org.firstinspires.ftc.teamcode.robotparts.TiltMechanism;
import org.firstinspires.ftc.teamcode.robotparts.DroneLauncher;
import org.firstinspires.ftc.teamcode.robotparts.Webcam;

/** Contains all the robot parts. */
public class CompetitionRobot {

    /** The webcam of the robot. */
    public Webcam webcam;
    public Display display;
    public Imu  imu;
    public Odometry odometry;
   public MecanumDrivetrain drivetrain;
    public Arm  arm;
    public TiltMechanism tiltMechanism;
    public DroneLauncher droneLauncher;
    public Grabber grabber;

    /** The constructor for a LinearOpMode program. */
    public CompetitionRobot(LinearOpMode runningLinearOpMode) {
        HardwareMap hardwaremap = runningLinearOpMode.hardwareMap;
        display = new Display(runningLinearOpMode.telemetry);

        display.showLoading("webcam");
        webcam = new Webcam(hardwaremap);
        display.showLoading("imu");
        imu = new Imu(hardwaremap);
        display.showLoading("drivetrain");
        odometry = new Odometry(hardwaremap);
        drivetrain = new MecanumDrivetrain(runningLinearOpMode, odometry, imu);
//        display.showLoading("odometry");
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
      //display.showLoading("grabberRight");
       grabber = new Grabber(hardwaremap);
        arm = new Arm(hardwaremap);
//        display.showLoading("arm");
       tiltMechanism = new TiltMechanism(hardwaremap);
        //display.showLoading("tiltMechanism")
       droneLauncher = new DroneLauncher(hardwaremap);
        //display.showLoading("droneLauncher")
      // display.showLoading("Done initializing! Press start to play");
    }
}