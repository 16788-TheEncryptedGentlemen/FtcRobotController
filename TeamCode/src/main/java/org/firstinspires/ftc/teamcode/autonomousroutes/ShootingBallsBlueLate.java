package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robots.DrivetrainTest;
@Autonomous
public class ShootingBallsBlueLate extends ShootingBallsBlue{
    public void runOpMode() {
        // Initialisation.
        robot =  new DrivetrainTest(this);
        robot.drivetrain.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();

        sleep(1500);
        blueRoute();
    }
}
