package org.firstinspires.ftc.teamcode.robots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.otherclasses.Display;
import org.firstinspires.ftc.teamcode.robotparts.*;
import org.firstinspires.ftc.teamcode.robotparts.template.BaseOdometry;

/**
 * Contains all the robot parts.
 */
public class DrivetrainOnly {

    public Display display;
    public Imu imu;
    public BaseOdometry odometry;
    public MecanumDrivetrain drivetrain;
    /**
     * The constructor for a LinearOpMode program.
     */
    public DrivetrainOnly(LinearOpMode runningLinearOpMode) {
        display = new Display(runningLinearOpMode.telemetry);
        HardwareMap hardwaremap = runningLinearOpMode.hardwareMap;
        Initialize(hardwaremap);
        display.showLoading("drivetrain");
        drivetrain = new MecanumDrivetrain(runningLinearOpMode, odometry, imu);
        display.showLoading("Done initializing! Press start to play");
    }

    public DrivetrainOnly(OpMode opMode) {
        display = new Display(opMode.telemetry);
        HardwareMap hardwaremap = opMode.hardwareMap;
        Initialize(hardwaremap);
        display.showLoading("drivetrain");
        drivetrain = new MecanumDrivetrain(hardwaremap, odometry, imu);
        display.showLoading("Done initializing! Press start to play");
    }

    /**
     * Initializes all base robot parts.
     * @param hardwaremap robot hardwaremap
     */
    public void Initialize(HardwareMap hardwaremap) {
        display.showLoading("imu");
        imu = new Imu(hardwaremap);
        display.showLoading("odometry");
        odometry = new PinPointOdometry(hardwaremap); // INFO: changed odometry type. change type to select old Odometry
    }
}
