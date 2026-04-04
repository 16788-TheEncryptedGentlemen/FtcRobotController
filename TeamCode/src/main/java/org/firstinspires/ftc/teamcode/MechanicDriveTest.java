package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

//@TeleOp
public class MechanicDriveTest extends OpMode {

    /**
     * The robot
     */
    CompetitionRobot robot;

    @Override
    /** Initialisation */
    public void init() {
        robot = new CompetitionRobot(this);
    }

    @Override
    /** Repeats program until program is stopped */
    public void loop() {
        controlDrivetrain();
        telemetry.update();
    }

    private void controlDrivetrain() {
        // If start is pressed then the current heading of the imu is set to 0.
        if (gamepad1.start) {
            robot.imu.reset();
        }
            // als je op rechter trigger drukt gaat rechtsvoor vooruit
            if (gamepad1.right_trigger > 0) {
                telemetry.addLine("Rechtsvoor");
                robot.drivetrain.frontRight.setPower(0.2);
            }
            else {
                robot.drivetrain.frontRight.setPower(0.0);
            }
            // als je op rechter bumper drukt gaat rechtsachter vooruit
            if (gamepad1.right_bumper) {
                telemetry.addLine("Rechtsachter");
                robot.drivetrain.backRight.setPower(0.2);
            }
            else {
                robot.drivetrain.backRight.setPower(0.0);
            }
            // als je op linker trigger drukt gaat linksvoor vooruit
            if (gamepad1.left_trigger > 0) {
                telemetry.addLine("Linksvoor");
                robot.drivetrain.frontLeft.setPower(0.2);
            }
            else {
                robot.drivetrain.frontLeft.setPower(0.0);
            }

            if (gamepad1.left_bumper) {
                telemetry.addLine("Linksachter");
                robot.drivetrain.backLeft.setPower(0.2);
            }
            else {
                robot.drivetrain.backLeft.setPower(0.0);
            }
        }
    }
