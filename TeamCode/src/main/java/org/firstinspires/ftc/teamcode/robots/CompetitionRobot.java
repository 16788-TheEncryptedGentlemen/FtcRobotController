package org.firstinspires.ftc.teamcode.robots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.otherclasses.Display;
import org.firstinspires.ftc.teamcode.robotparts.Imu;
import org.firstinspires.ftc.teamcode.robotparts.Intake;
import org.firstinspires.ftc.teamcode.robotparts.MecanumDrivetrain;
import org.firstinspires.ftc.teamcode.robotparts.Odometry;
import org.firstinspires.ftc.teamcode.robotparts.ShooterV;
import org.firstinspires.ftc.teamcode.robotparts.Transport;

public class CompetitionRobot {

    public Display display;
    public Imu imu;
    public Odometry odometry;
    public MecanumDrivetrain drivetrain;
    public Transport transport;
    public Intake intake;
    public ShooterV shooter;

    /**
     * The constructor for a LinearOpMode program.
     */
    public CompetitionRobot(LinearOpMode opMode) {
        display = new Display(opMode.telemetry);
        HardwareMap hardwaremap = opMode.hardwareMap;

        display.showLoading("imu");
        imu = new Imu(hardwaremap);
        display.showLoading("odometry");
        odometry = new Odometry(hardwaremap);
        display.showLoading("drivetrain");
        drivetrain = new MecanumDrivetrain(opMode, odometry, imu);
        display.showLoading("transport");
        transport = new Transport(hardwaremap);
        display.showLoading("shooter");
        shooter = new ShooterV(hardwaremap);
        display.showLoading("intake");
        intake = new Intake(hardwaremap);
        display.showLoading("Done initializing! Press start to play");
    }
}
