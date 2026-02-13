package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robots.DrivetrainTest;
@Autonomous
public class WaitAndDrive extends LinearOpMode {
    DrivetrainTest robot;

    final double POWER = 0.6;
        @Override
        public void runOpMode() {
            // Initialisation.
            robot = new DrivetrainTest(this);
            robot.drivetrain.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            waitForStart();

            sleep(25000);
            robot.drivetrain.driveStraight(50, POWER);
        }

}
