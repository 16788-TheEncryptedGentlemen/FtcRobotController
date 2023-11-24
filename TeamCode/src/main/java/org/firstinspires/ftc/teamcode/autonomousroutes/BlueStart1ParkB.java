package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class BlueStart1ParkB extends LinearOpMode {
    public void runOpMode() {

        CompetitionRobot robot = new CompetitionRobot(this);
        BezierCurveRoutes routes = new BezierCurveRoutes(robot, this);
        // Initialisation.

        waitForStart();

       robot.drivetrain.driveStraight(3,0.4);
       routes.BlueStart1ParkB.executeWithPointSkip();

    }
}