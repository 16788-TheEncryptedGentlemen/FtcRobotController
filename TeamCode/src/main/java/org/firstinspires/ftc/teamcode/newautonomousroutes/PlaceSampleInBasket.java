package org.firstinspires.ftc.teamcode.newautonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class PlaceSampleInBasket extends LinearOpMode {
    CompetitionRobot robot;

    final double POWER = 0.5;

    public void runOpMode() {

        // Initialisation.
        robot = new CompetitionRobot(this);
        waitForStart();

        robot.drivetrain.driveStraight(100, POWER);
        robot.drivetrain.turnRobotAO(-90, POWER);
        robot.drivetrain.driveStraight(70, POWER);
        robot.autolift.liftMidPole();
        robot.drivetrain.driveStraight(5, POWER);
        robot.grabber.drop();
        robot.autolift.liftGroundLevel();
        robot.drivetrain.driveBackwards(180, POWER);
    }
}
