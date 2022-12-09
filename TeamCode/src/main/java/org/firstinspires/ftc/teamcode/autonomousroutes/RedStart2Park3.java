package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class RedStart2Park3 extends LinearOpMode {
    public void runOpMode() {

        CompetitionRobot robot = new CompetitionRobot(this);

        BezierCurveRoute RedStart2Park3 = new BezierCurveRoute(
                /** The x-coefficients. */
                new double[] {209.125, -225.855, 79.6666666666661},
                /** The Y-coefficients. */
                new double[] {13.1449999999976, -76.479999999996, 131.051666666665},
                robot,
                0.6,
                /** STRAFE or FOLLOW. */
                DRIVE_METHOD.STRAFE,
                this
        );

        /** Initialisation. */
        waitForStart();

        // Close robot grabber around cone before driving.
        robot.grabber.grab();

        RedStart2Park3.executeWithPointSkip();
    }
}