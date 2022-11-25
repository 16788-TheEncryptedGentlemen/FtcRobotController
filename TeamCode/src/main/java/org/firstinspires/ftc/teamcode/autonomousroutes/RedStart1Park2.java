package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class RedStart1Park2 extends LinearOpMode {
    public void runOpMode() {

        CompetitionRobot robot = new CompetitionRobot(this);

        BezierCurveRoute RedStart1Park2 = new BezierCurveRoute(
                /** The x-coefficients. */
                new double[] {370.45, -856.416666666667, 884.3, -254.933333333333, -143.001666666667},
                /** The Y-coefficients. */
                new double[] {-3.9833333333338, -51.7833333333265, 1422.04999999998, -2039.46666666665, 799.853333333328},
                robot,
                0.6,
                /** STRAFE or FOLLOW. */
                DRIVE_METHOD.STRAFE,
                this
        );

        /** Initialisation. */
        waitForStart();

        RedStart1Park2.executeWithPointSkip();
    }
}