package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robots.DrivetrainTest;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class ShootRight extends LinearOpMode {
    DrivetrainTest robot;

    final double POWER = 0.5;

    public void runOpMode() {
        // Initialisation.
        robot = new DrivetrainTest(this);
        waitForStart();
        robot.shooterV.shootV(0);
        sleep(1000);
        robot.shooter.DeliverBall();
        sleep(3000);
        robot.shooter.NewBall();
        robot.shooterV.stopMotor();
        robot.drivetrain.strafeStraight(30, POWER,0 );

    }
}