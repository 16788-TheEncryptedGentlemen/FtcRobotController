package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class BlueStart1Park1 extends LinearOpMode {
    public void runOpMode() {

        CompetitionRobot robot = new CompetitionRobot(this);

        BezierCurveRoute BlueStart1Park1 = new BezierCurveRoute(
                /** The x-coefficients. */
                new double[] {-243.780000000001, 290.385000000003, -114.720000000002},
                /** The Y-coefficients. */
                new double[] {-22.7049999999988, 44.2149999999988, 43.02},
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
        sleep(3000);
        robot.lift.liftLow();
        sleep(2000);

        BlueStart1Park1.executeWithPointSkip();
    }
}