package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
//@Autonomous
public class BlueStart2ParkB extends LinearOpMode {
    public void runOpMode() {

        CompetitionRobot robot = new CompetitionRobot(this);

        BezierCurveRoute BlueStart2ParkB = new BezierCurveRoute(
                new double[] {-115.915}, //The x-coefficients
                new double[] {2.39000000000027}, //The y-coefficients
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
        BlueStart2ParkB.executeWithPointSkip();

        sleep(5000);
    }
}