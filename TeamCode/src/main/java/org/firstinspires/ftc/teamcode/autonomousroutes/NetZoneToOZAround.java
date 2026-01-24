package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
//@Autonomous
public class NetZoneToOZAround extends LinearOpMode {
    CompetitionRobot robot;

    final double POWER = 0.5;

    public void runOpMode() {
        // Initialisation.
        robot = new CompetitionRobot(this);
        waitForStart();

        robot.drivetrain.strafeStraight(-10,POWER,0);
        robot.sliderGrabber2.SliderToInPosition();
        robot.drivetrain.driveStraight(37,POWER);
        robot.drivetrain.strafeStraight(255,POWER,0);
        robot.drivetrain.driveBackwards(40,POWER);

        // todo: checken of in ieder geval éen deel van éen wiel erin staat.
    }
}