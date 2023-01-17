package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class Test extends LinearOpMode {
    public void runOpMode() {

        CompetitionRobot robot = new CompetitionRobot(this);

        robot.imu.resetAngularOrientation();

        /** Initialisation. */
        waitForStart();

        telemetry.addData("enc", robot.autolift.currentEnc());
        telemetry.update();
        sleep(2000);


// laten staan voor wedstrijd calibration
        robot.grabber.grab();
//        sleep(1000);
//        robot.autolift.liftGroundLevel();
        sleep(3000);
        robot.autolift.liftGroundJunction();
        sleep(3000);
        robot.autolift.liftLowPole();
        sleep(3000);
        robot.autolift.liftMidPole();
        sleep(3000);
        robot.autolift.liftHighPole();
        sleep(3000);
        robot.autolift.liftGroundLevel();
        sleep(3000);
        robot.grabber.drop();
        sleep(1000);
        robot.autolift.liftHighPole();
        robot.autolift.liftGroundLevel();
        sleep(1000);
        telemetry.addData("enc", robot.autolift.currentEnc());
        telemetry.update();
        sleep(5000);


    }
}