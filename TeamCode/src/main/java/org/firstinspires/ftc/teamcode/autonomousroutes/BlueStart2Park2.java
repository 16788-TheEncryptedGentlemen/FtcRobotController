package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class BlueStart2Park2 extends LinearOpMode {
    public void runOpMode() {

        CompetitionRobot robot = new CompetitionRobot(this);

        BezierCurveRoute BlueStart2Park2 = new BezierCurveRoute(
                /** The x-coefficients. */
                new double[] {-412.275, 1051.6, -1107.36666666666, 207.133333333333, 262.9},
                /** The Y-coefficients. */
                new double[] {-3.98333333333403, -87.6333333333348, 1326.45, -1718.80833333334, 604.670000000001},
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

        BlueStart2Park2.executeWithPointSkip();
    }
}