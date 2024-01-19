package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
//@Autonomous
public class RedStart1SDParkB extends LinearOpMode {
    public void runOpMode() {

        CompetitionRobot robot = new CompetitionRobot(this);

        BezierCurveRoute RedStart1SDParkB = new BezierCurveRoute(
                new double[] {-72.895, 38.24, 275.248333333333}, //The x-coefficients
                new double[] {468.44, -504.29, 171.283333333333}, //The y-coefficients
                robot,
                0.4,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );




        // Initialisation.
        robot.tiltMechanism.TiltMechanismStartPosition();
        waitForStart();

        RedStart1SDParkB.executeWithPointSkip();

        sleep(5000);
    }
}