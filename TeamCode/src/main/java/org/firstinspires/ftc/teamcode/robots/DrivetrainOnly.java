package org.firstinspires.ftc.teamcode.robots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.otherclasses.Display;
import org.firstinspires.ftc.teamcode.robotparts.Imu;
import org.firstinspires.ftc.teamcode.robotparts.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.robotparts.Odometry;

/**
 * Contains all the robot parts.
 */
public class DrivetrainOnly {
    public Display display;
    public Imu imu;
    public Odometry odometry;
    public MecanumDrivetrain drivetrain;

    /**
     * The constructor initialises all robot parts.
     */
    public DrivetrainOnly(LinearOpMode opMode) {
        display = new Display(opMode.telemetry);

        display.showLoading("imu");
        imu = new Imu(opMode.hardwareMap);
        display.showLoading("odometry");
        odometry = new Odometry(opMode.hardwareMap);
        display.showLoading("drivetrain");
        drivetrain = new MecanumDrivetrain(opMode, odometry, imu);
        display.showLoading("Done initializing! Press start to play");
    }
}
