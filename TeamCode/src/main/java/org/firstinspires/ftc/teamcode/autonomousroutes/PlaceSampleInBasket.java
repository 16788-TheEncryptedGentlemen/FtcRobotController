package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class PlaceSampleInBasket extends LinearOpMode {
    CompetitionRobot robot;

    private static final int ARMPOSITIONGROUND = 0;
    private static final int SLIDERPOSITIONMIDDLEBASKET = 300;
    private static final int SLIDERPOSITIONLOWERBASKET = 150;
    private static final int SLIDERPOSITIONGROUND = 0;
    private static final int ARMPOSITIONLOWERBASKET = 250;
    private static final int ARMPOSITIONMIDDLEBASKET = 500;

    final double POWER = 0.4;

    public void runOpMode() {

        // Initialisation.
        robot = new CompetitionRobot(this);
        waitForStart();

        driveToBasket();
        dropSampleInLowerBasket();
        driveToSecondSample();
        driveToSubmisibleZone();

    }

    /**
     * Drive to the basket from left start position.
     */
   /* public void driveToBasket() {
        robot.drivetrain.driveStraight(100, POWER);
        robot.drivetrain.turnRobotAO(-125, POWER);
        robot.drivetrain.driveStraight(130, POWER);
    }*/
    public void driveToBasket() {
        robot.drivetrain.driveStraight(25, POWER);
        robot.drivetrain.turnRobotAO(-90, POWER);
        robot.drivetrain.driveStraight(87, POWER);
        robot.drivetrain.turnRobotAO(-120, POWER);
    }
    /**
     * Place sample in the lower basket.
     */
    public void dropSampleInLowerBasket(){
        armToLowerBasket();
        sleep(1000);
        robot.grabber.drop();
        sleep (1000);
    }

    /**
     * Drives to pick up second sample
     */
    public void driveToSecondSample() {
        armToGroundPosition();
        sleep(1000);
        robot.drivetrain.turnRobotAO(0, POWER);
        sleep(1000);
        robot.grabber.grab();
        sleep(1000);
        robot.drivetrain.driveStraight(10,POWER);
        sleep(1000);
        robot.drivetrain.turnRobotAO(120, POWER);
        sleep(1000);
        robot.grabber.drop();
    }

    public void driveToSubmisibleZone(){
        robot.drivetrain.turnRobotAO(120,POWER);
        robot.drivetrain.driveStraight(60,POWER);
    }

    public void armToGroundPosition() {
        robot.arm.ToPosition(ARMPOSITIONGROUND);
        robot.sliderGrabber.in();
    }

    public void armToLowerBasket() {
        robot.arm.ToPosition(ARMPOSITIONLOWERBASKET);
        robot.sliderGrabber.out();
    }

    public void armToMiddleBasket() {
        robot.arm.ToPosition(ARMPOSITIONMIDDLEBASKET);
    }

}