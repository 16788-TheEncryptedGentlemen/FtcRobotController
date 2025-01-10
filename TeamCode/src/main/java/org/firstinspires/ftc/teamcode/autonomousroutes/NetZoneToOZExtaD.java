package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class NetZoneToOZExtaD extends LinearOpMode {
    CompetitionRobot robot;

    final double POWER = 0.5;

    public void runOpMode() {
        // Initialisation.
        robot = new CompetitionRobot(this);
        waitForStart();
        sleep (7000);
        robot.drivetrain.strafeStraight(-100,POWER,0);
        robot.drivetrain.strafeStraight(220,POWER,0);

        // todo: checken of in ieder geval éen deel van éen wiel erin staat.
    }
}