package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robots.DrivetrainTest;

@Autonomous
public class StartToLaunchZoneLLinks extends LinearOpMode {

    DrivetrainTest robot;

    final double POWER = 0.5;

    public void runOpMode() {
        // Initialisation.
        robot =  new DrivetrainTest(this);
        waitForStart();

        robot.shooterV.shootForAutonomous(0);
        robot.drivetrain.driveStraight(40,POWER);
        sleep(1000);
        robot.shooter.DeliverBall();
        sleep(500);
        robot.intake.IntakeStart(-1.0);
        sleep(5000);
        robot.shooter.stopMotor();
        sleep(500);
        robot.shooter.NewBall();
        sleep(500);
        robot.drivetrain.strafeStraight(-180,POWER,0);
        sleep(500);
        robot.drivetrain.turnRobotAO(180,-0.3);
        sleep(500);
        robot.drivetrain.driveStraight(100,POWER);
        robot.drivetrain.stop();
        robot.intake.IntakeStop();





    }
}