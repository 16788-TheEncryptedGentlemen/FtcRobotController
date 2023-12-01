package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute.DRIVE_METHOD;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class BlueStart1ParkBTest extends LinearOpMode {
    public void runOpMode() {

        CompetitionRobot robot = new CompetitionRobot(this);


            BezierCurveRoute Test = new BezierCurveRoute(
                    new double[] {5.97499999999985, -83.6499999999998, -107.151666666667}, //The x-coefficients
                    new double[] {229.44, -229.44, 18.7216666666667}, //The y-coefficients
                    robot,
                    0.5,
                    DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                    this
            );


        // Initialisation.
        robot.tiltMechanism.TiltMechanismStartPosition();
        robot.grabber.Grab();
        waitForStart();

        // DRIVE TO BOARD
        robot.drivetrain.driveStraight(30, 0.4);
        sleep(800);
        robot.drivetrain.driveStraight(22, -0.4);
        robot.arm.AutoArmToBoardPosition();
        robot.tiltMechanism.TiltMechanismDown();

        sleep(800);
        robot.arm.motor.setTargetPosition(40);
        robot.grabber.Grab();

        sleep(800);
        robot.arm.AutoArmToBoardPosition();
        robot.tiltMechanism.TiltMechanismUp();

        robot.drivetrain.turnRobotAO(90);
        sleep(5000);
        // DRIVE FORWARD 10CM
        robot.grabber.Drop();
        // DRIVE BACK 10CM

        // DRIVE TO PARKING

//        Test.executeWithPointSkip();

        sleep(5000);
    }
}