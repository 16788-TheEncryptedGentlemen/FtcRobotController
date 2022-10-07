package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Display;
import org.firstinspires.ftc.teamcode.robotparts.IMU;
import org.firstinspires.ftc.teamcode.robotparts.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.robotparts.Odometry;
import org.firstinspires.ftc.teamcode.robotparts.Webcam;

/** Contains all the robot parts. */
public class CompetitionRobot {
    public MecanumDrivetrain drivetrain;
    public Odometry odometry;
    public IMU imu;
    public Display display;
    public Webcam webcam;
    //---------------------------------------------------------------------
    //Constructors:
    // * LinearOpMode: The constructor for a LinearOpMode program
    // * OpMode: The constructor for an OpMode program
    //---------------------------------------------------------------------

    public CompetitionRobot(LinearOpMode runningLinearOpMode) {
        HardwareMap hardwaremap = runningLinearOpMode.hardwareMap;
        display = new Display(runningLinearOpMode.telemetry);

        display.showLoading("Odometry");
        odometry = new Odometry(hardwaremap);
        display.showLoading("imu");
        imu = new IMU(hardwaremap);
        display.showLoading("Drivetrain");
        drivetrain = new MecanumDrivetrain(hardwaremap, odometry, imu);
        display.showLoading("webcam");
        webcam = new Webcam(runningLinearOpMode);
        display.showLoading("Done initializing! Press start to play");
    }


    public CompetitionRobot(OpMode opMode) {
        HardwareMap hardwaremap = opMode.hardwareMap;
        display = new Display(opMode.telemetry);

        display.showLoading("Odometry");
        odometry = new Odometry(hardwaremap);
        display.showLoading("imu");
        imu = new IMU(hardwaremap);
        display.showLoading("Drivetrain");
        drivetrain = new MecanumDrivetrain(hardwaremap, odometry, imu);
        display.showLoading("Done initializing! Press start to play");
    }
    //---------------------------------------------------------------------
    //Constructors
    //---------------------------------------------------------------------


    //---------------------------------------------------------------------
    //Other methods
    //---------------------------------------------------------------------


    //---------------------------------------------------------------------
    //Other methods
    //---------------------------------------------------------------------
}