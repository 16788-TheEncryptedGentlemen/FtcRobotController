package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robots.DrivetrainOnly;

@Autonomous
public class ParkingToLaunchZoneSRechts extends LinearOpMode {

    DrivetrainOnly robot;

    final double POWER = 0.5;

    public void runOpMode() {
        // Initialisation.
        robot =  new DrivetrainOnly(this);
        waitForStart();

        robot.drivetrain.driveStraight(17,POWER);
        robot.drivetrain.turnRobotAO(-40,POWER);
        robot.drivetrain.strafeStraight(-10,POWER,-40);
        robot.drivetrain.stop();


    }
}
