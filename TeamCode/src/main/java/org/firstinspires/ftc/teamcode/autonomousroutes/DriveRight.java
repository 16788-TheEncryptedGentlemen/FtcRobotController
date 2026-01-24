package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robots.DrivetrainTest;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class DriveRight extends LinearOpMode {
    DrivetrainTest robot;

    final double POWER = 0.5;

    public void runOpMode() {
        // Initialisation.
        robot = new DrivetrainTest(this);
        waitForStart();
        robot.drivetrain.strafeStraight(30, POWER,0 );
    }
}