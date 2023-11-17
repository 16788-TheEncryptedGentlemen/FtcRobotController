package org.firstinspires.ftc.teamcode.autonomousroutes;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

@Autonomous
public class WielenTest extends LinearOpMode {

    public void runOpMode() {
        CompetitionRobot robot = new CompetitionRobot(this);
        waitForStart();

        while (!isStopRequested()) {
            telemetry.addData("", "frontRight");
            robot.drivetrain.frontRight.setPower(1.0);
            sleep(1000);
            robot.drivetrain.frontRight.setPower(0.0);
            sleep(1000);

            telemetry.addData("", "frontLeft");
            robot.drivetrain.frontLeft.setPower(1.0);
            sleep(1000);
            robot.drivetrain.frontLeft.setPower(0.0);
            sleep(1000);

            telemetry.addData("", "backRight");
            robot.drivetrain.backRight.setPower(1.0);
            sleep(1000);
            robot.drivetrain.backRight.setPower(0.0);
            sleep(1000);

            telemetry.addData("", "backLeft");
            robot.drivetrain.backLeft.setPower(1.0);
            sleep(1000);
            robot.drivetrain.backLeft.setPower(0.0);
            sleep(1000);
        }
    }





}

