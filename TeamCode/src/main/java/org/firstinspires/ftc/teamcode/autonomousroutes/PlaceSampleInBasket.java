package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
//@Autonomous
public class PlaceSampleInBasket extends LinearOpMode {
    CompetitionRobot robot;

    private static final int ARMPOSITIONGROUND = 0;
    private static final int SLIDERPOSITIONMIDDLEBASKET = 300;
    private static final int SLIDERPOSITIONLOWERBASKET = 150;
    private static final int SLIDERPOSITIONGROUND = 0;
    private static final int ARMPOSITIONLOWERBASKET = 250;
    private static final int ARMPOSITIONMIDDLEBASKET = 500;

    final double POWER = 0.5;

    public void runOpMode() {

        // Initialisation.
        robot = new CompetitionRobot(this);
        waitForStart();

        driveToBasket();
        dropSampleInLowerBasket();
        drivePlaceSample();
       // driveToSubmisibleZone();

    }

    /**
     * Drive to the basket from left start position.
     */
 /*   public void driveToBasket() {
        robot.drivetrain.driveStraight(100, POWER);
        robot.drivetrain.turnRobotAO(-125, POWER);
        robot.drivetrain.driveStraight(130, POWER);
    } */

    /**
     * Place sample in the lower basket.
     */
    public void dropSampleInLowerBasket(){
        robot.arm.MoveArmUp();
        robot.sliderGrabber2.SliderToOutPosition();
        armToGroundPosition();
        robot.grabber.grab();
        armToLowerBasket();
    }
    public void driveToBasket() {
        robot.drivetrain.driveStraight(25, POWER);
        robot.drivetrain.turnRobotAO(-90, POWER);
        robot.drivetrain.driveStraight(50, POWER);
        robot.drivetrain.turnRobotAO(-120, POWER);
        robot.drivetrain.driveStraight(40, POWER);
        telemetry.addLine("Rechtsvoor");
        telemetry.addLine("Linksvoor");
        telemetry.addLine("Rechtachter");
        telemetry.addLine("Rechtsvoor");
    }
    /**
     * Drives to pick up second sample
     */
  public void drivePlaceSample() {
      robot.sliderGrabber2.MoveSliderOut();
      robot.grabber.drop();
        sleep(1000);
        robot.drivetrain.turnRobotAO(0, POWER);
        sleep(1000);
      robot.drivetrain.strafeStraight(210,POWER,0);
    }

  /*  public void driveToSubmisibleZone(){
        robot.drivetrain.turnRobotAO(120,POWER);
        robot.drivetrain.driveStraight(60,POWER);
    }*/

    public void armToGroundPosition() {
        robot.arm.ToPosition(ARMPOSITIONGROUND);
        robot.sliderGrabber2.SliderToInPosition();
    }

    public void armToLowerBasket() {
        robot.arm.ToPosition(ARMPOSITIONLOWERBASKET);
        robot.sliderGrabber2.SliderToOutPosition();
    }

    public void armToMiddleBasket() {
        robot.arm.ToPosition(ARMPOSITIONMIDDLEBASKET);
    }

}