package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class ATEST extends LinearOpMode {
    private final boolean BLUE_SIDE = true;
    private final boolean SKIP_VISION = false;
    private CompetitionRobot robot;

    private void initAutonomous() {
        robot = new CompetitionRobot(this);


    }

    @Override
    public void runOpMode() {
        int markerPosition = 1;

        initAutonomous();

        // TODO: Hier 1 functie van maken.
        robot.grabber.grab();


        while (!isStarted() && !isStopRequested()) {
            markerPosition = robot.webcam.getMarkerPosition(BLUE_SIDE);
            // result
            telemetry.addData("Position", markerPosition);
            telemetry.update();
        }

        // Initialisation.
        waitForStart();

        // Do my thing

        // This is meant to be a script to drive through the course made on in the bak by Cornel
        // Though I made this script mostly through just thinking and hoping it all went well
        // seeiing as i couldn't really do much testing until now, so we'll just have to wait and see
        larsVerplaatsing(150);
        larsRotatie(90);
        larsVerplaatsing(50);
        larsRotatie(180);
        larsVerplaatsing(90);
        larsRotatie(90);
        larsVerplaatsing(100);
        larsRotatie(0);
        larsVerplaatsing(180);
        larsRotatie(65);
        larsVerplaatsing(30);
        larsRotatie(270);
        larsVerplaatsing(300);
        larsRotatie(180);
        larsVerplaatsing(300);

//        larsVerplaatsing(100);
//        sleep(100);
//        larsRotatie(180);
//        sleep(100);
//        larsVerplaatsing(100);
//        sleep(100);
//        larsRotatie(0);
//        sleep(300);
//        larsVerplaatsing(10);
//        sleep(500);
//        larsRotatie(350);
//        larsSlowStart();
//        sleep(1000);
//        larsSlowAngle();
    }
    private void larsVerplaatsing(int distance) {
        int uitrolAfstand = 20; // [cm]
        // normaal rijden, laatste aantal centimeters op lagere power.
        if (uitrolAfstand > distance) {
            robot.drivetrain.driveStraightDirect(distance, 0.3);
            robot.drivetrain.stop();
        }
        robot.drivetrain.driveStraightDirect(distance - uitrolAfstand, 0.4);
        robot.drivetrain.driveStraightEnd(uitrolAfstand, 0.4);
        robot.drivetrain.stop();
    }
    private void larsSlowStart() {
        double startDistance = 20;
        double currentDistance = 0;

        while (currentDistance < startDistance){
            // Ask jeroen about the weird decimal situation
            currentDistance += 2;
            double motorI = currentDistance /10;
            robot.drivetrain.driveStraightDirect(10, motorI);
            sleep(30);

        }
        robot.drivetrain.stop();
    }
    private void larsSlowAngle() {
        double wantedSpeed = 0.3;
        double wantedAngle = 30;
        double currentSpeed = 0;
        double currentAngle = 0;

        while (currentAngle < wantedAngle && currentSpeed < wantedSpeed) {
            currentAngle += 2;
            currentSpeed += 0.1;
            robot.drivetrain.turnRobotAO(currentAngle, currentSpeed);
        }
    }
    private void larsRotatie(int rotation) {
        int uitrolRotatie = 15;
        if (uitrolRotatie > rotation) {
            robot.drivetrain.turnRobotAO(rotation, 0.3);
            robot.drivetrain.stop();
        }
        else {
            robot.drivetrain.turnRobotAO(rotation - uitrolRotatie, 0.3);
            robot.drivetrain.turnRobotAO(rotation, 0.1);
            robot.drivetrain.stop();
        }
    }

    private void middlePixelPlacement() {
        robot.drivetrain.driveStraight(100, 0.3);

//        sleep(100);
//        robot.drivetrain.turnRobotAO(180);
//        sleep(100);
//        robot.drivetrain.driveStraight(100, 0.3);
    }

    private void rightPixelPlacement() {
        robot.drivetrain.driveStraight(100, 0.3);
        sleep(100);
        robot.drivetrain.turnRobotAO(90);
        sleep(200);
        robot.drivetrain.driveStraight(15, 0.3);
        sleep(500);
        robot.drivetrain.driveStraight(-20, 0.3);
    }

    private void leftPixelPlacement() {
        robot.drivetrain.driveStraight(135, 0.3);
        sleep(100);
        robot.drivetrain.strafeStraight(-25, 0.3, 0);
        sleep(500);
        robot.drivetrain.driveStraight(-20, 0.3);


    }
}