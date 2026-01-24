package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
//@Autonomous
public class NetZoneToOZAroundAdjust extends LinearOpMode {
    CompetitionRobot robot;

    final double POWER = 0.5;

    public void runOpMode() {
        // Initialisation.
        robot = new CompetitionRobot(this);
        waitForStart();

        robot.drivetrain.strafeStraight(-10,POWER,0);
        sleep(15000);
        robot.drivetrain.strafeStraight(10,POWER,0);
        robot.drivetrain.driveStraight(45,POWER);
        robot.drivetrain.strafeStraight(255,POWER,0);
        robot.drivetrain.driveBackwards(-40,POWER);

        // todo: checken of in ieder geval éen deel van éen wiel erin staat.
    }
}