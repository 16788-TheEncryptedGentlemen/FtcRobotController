package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Display;
import org.firstinspires.ftc.teamcode.robotparts.Grabber;
import org.firstinspires.ftc.teamcode.robotparts.Imu;
import org.firstinspires.ftc.teamcode.robotparts.Odometry;
import org.firstinspires.ftc.teamcode.robotparts.SingleMotorLift;
import org.firstinspires.ftc.teamcode.robotparts.MecanumDrivetrain;

/** Contains all the robot parts. */
public class TestRobot {
    /** The drivetrain of the robot. */
    public MecanumDrivetrain drivetrain;
    /** The odometry of the robot. */
    public Odometry odometry;
    /** The imu of the robot. */
    public Imu imu;
    /** The display of the robot. */
    public Display display;
    /** The grabber of the robot. */
    public Grabber grabber;
    /** The lift of the robot */
    public SingleMotorLift lift;


   /** The constructor for an OpMode program. */
    public TestRobot(OpMode opMode) {
        HardwareMap hardwaremap = opMode.hardwareMap;
        display = new Display(opMode.telemetry);

        display.showLoading("imu");
        imu = new Imu(hardwaremap);
        display.showLoading("drivetrain");
        drivetrain = new MecanumDrivetrain(hardwaremap, odometry, imu);
        display.showLoading("grabber");
        grabber = new Grabber(hardwaremap);
        display.showLoading("lift");
        lift = new SingleMotorLift(hardwaremap);
        display.showLoading("Done initializing! Press start to play");
    }

}