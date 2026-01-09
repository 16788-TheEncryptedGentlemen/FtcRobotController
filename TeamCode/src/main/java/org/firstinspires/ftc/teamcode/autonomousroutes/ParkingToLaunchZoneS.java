package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;
import org.firstinspires.ftc.teamcode.robots.DrivetrainOnly;

@Autonomous
public class ParkingToLaunchZoneS extends LinearOpMode {

    DrivetrainOnly robot;

    final double POWER = 0.5;

    public void runOpMode() {
        // Initialisation.
        robot =  new DrivetrainOnly(this);
        waitForStart();

        robot.drivetrain.strafeStraight(67,POWER, 0);

    }
}