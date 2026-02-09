package org.firstinspires.ftc.teamcode.drivercontrolled;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robots.DrivetrainOnly;

@TeleOp
public class MecanumDriveTest extends TeleOpMode<DrivetrainOnly> {
    /**
     * Runs once when init is pressed.
     */
    public void initCode() {
        robot = new DrivetrainOnly(this);
    }

    /**
     * Repeats until program is stopped.
     */
    public void controlLoop() {
        controlDrivetrain();
        telemetry.addData("X", robot.odometry.getX());
        telemetry.addData("Y", robot.odometry.getY());
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
        } else {
            robot.drivetrain.frontRight.setPower(0.0);
        }
        // als je op rechter bumper drukt gaat rechtsachter vooruit
        if (gamepad1.right_bumper) {
            telemetry.addLine("Rechtsachter");
            robot.drivetrain.backRight.setPower(0.2);
        } else {
            robot.drivetrain.backRight.setPower(0.0);
        }
        // als je op linker trigger drukt gaat linksvoor vooruit
        if (gamepad1.left_trigger > 0) {
            telemetry.addLine("Linksvoor");
            robot.drivetrain.frontLeft.setPower(0.2);
        } else {
            robot.drivetrain.frontLeft.setPower(0.0);
        }

        if (gamepad1.left_bumper) {
            telemetry.addLine("Linksachter");
            robot.drivetrain.backLeft.setPower(0.2);
        } else {
            robot.drivetrain.backLeft.setPower(0.0);
        }
    }
}
