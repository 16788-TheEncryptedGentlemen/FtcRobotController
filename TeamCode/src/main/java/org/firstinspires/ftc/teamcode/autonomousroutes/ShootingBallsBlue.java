package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robots.DrivetrainTest;

@Autonomous
public class ShootingBallsBlue extends LinearOpMode {
    DrivetrainTest robot;

    final double POWER = 0.6;

    public void runOpMode() {
        // Initialisation.
        robot =  new DrivetrainTest(this);
        robot.drivetrain.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();

        blueRoute();
    }

    public void blueRoute() {
        robot.shooterV.shootForAutonomous(0);
        robot.drivetrain.driveStraight(40,POWER);
        sleep(1000);
        robot.shooter.DeliverBall();
        robot.intake.IntakeStart(1);
        sleep(3000);
        robot.shooter.stopMotor();
        robot.intake.IntakeStop();
        sleep(500);
        robot.shooter.NewBall();
        robot.drivetrain.strafeStraight(118,POWER,0);
        sleep(100);
        robot.drivetrain.turnRobotAO(-176);
        sleep(100);
        robot.intake.IntakeStart(1);
        robot.shooter.DeliverBall();
        robot.drivetrain.driveStraight(85,0.3);
        sleep(400);
        robot.drivetrain.stop();
        robot.shooter.NewBall();
        robot.intake.IntakeStop();
    }
}
