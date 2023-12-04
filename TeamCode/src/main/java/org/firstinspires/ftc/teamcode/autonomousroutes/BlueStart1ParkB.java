package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute.DRIVE_METHOD;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class BlueStart1ParkB extends LinearOpMode {
    public void runOpMode() {

        CompetitionRobot robot = new CompetitionRobot(this);


        BezierCurveRoute BlueStart1ParkB = new BezierCurveRoute(
                new double[] {-235.415}, //The x-coefficients
                new double[] {2.7883333333333}, //The y-coefficients
                robot,
                0.4,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );



        // Initialisation.
        robot.tiltMechanism.TiltMechanismStartPosition();
        waitForStart();

        robot.drivetrain.driveStraight(1.0, 0.4);
        sleep(500);
        BlueStart1ParkB.executeWithPointSkip();

        sleep(5000);
    }
}