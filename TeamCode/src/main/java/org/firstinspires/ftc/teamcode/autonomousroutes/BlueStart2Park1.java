package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class BlueStart2Park1 extends LinearOpMode {
    public void runOpMode() {

        CompetitionRobot robot = new CompetitionRobot(this);

        BezierCurveRoute BlueStart2Park1 = new BezierCurveRoute(
                /** The x-coefficients. */
                new double[] {-195.98, 212.71, -74.8866666666666},
                /** The Y-coefficients. */
                new double[] {9.55999999999847, -50.1899999999981, 101.575},
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

        BlueStart2Park1.executeWithPointSkip();
    }
}