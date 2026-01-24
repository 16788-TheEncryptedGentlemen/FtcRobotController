package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.robots.DrivetrainTest;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class ShootBackwards extends LinearOpMode {
    DrivetrainTest robot;

    final double POWER = 0.5;

    public void runOpMode() {
        // Initialisation.
        robot = new DrivetrainTest(this);
        waitForStart();
        robot.shooterV.shootV(1750);
        sleep(500);
        robot.intake.IntakeStart(1.0);
        sleep(3000);
        robot.intake.IntakeStop();
        robot.shooter.stopMotor();
        robot.drivetrain.driveStraight(-30, POWER);

        // todo: checken of in ieder geval éen deel van éen wiel erin staat.
    }
}