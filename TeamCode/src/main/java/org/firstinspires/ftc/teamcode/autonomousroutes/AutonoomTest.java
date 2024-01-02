package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class AutonoomTest extends LinearOpMode {
    private CompetitionRobot robot;

    private void initAutonomous() {
        robot = new CompetitionRobot(this);

    }

    @Override
    public void runOpMode() {
        initAutonomous();

        // Initialisation.
        waitForStart();

        robot.pusher.preloadRight();
        sleep(1000);

        }
    }