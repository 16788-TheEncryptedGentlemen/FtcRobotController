package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.robots.DrivetrainTest;
@Autonomous
public class ShootingBallsRedLate extends ShootingBallsRed {
    @Override
    public void runOpMode() {
        // Initialisation.
        robot =  new DrivetrainTest(this);
        robot.drivetrain.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();

        sleep(15000);
        redRoute();
    }
}
