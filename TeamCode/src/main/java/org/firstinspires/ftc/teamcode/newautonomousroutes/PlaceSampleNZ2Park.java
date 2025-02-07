package org.firstinspires.ftc.teamcode.newautonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class PlaceSampleNZ2Park extends LinearOpMode {
    CompetitionRobot robot;

    final double POWER = 0.5;

    public void runOpMode() {

        // Initialisation.
        robot = new CompetitionRobot(this);
        waitForStart();

        robot.drivetrain.strafeStraight(-2,POWER,0);
        robot.drivetrain.driveStraight(70, POWER);
        robot.grabber.grab();
        robot.drivetrain.turnRobotAO(-180, POWER);
        robot.drivetrain.driveStraight(60, POWER);
        robot.drivetrain.strafeStraight(-200, POWER, 0);
    }
}
