package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;


@Autonomous
public class BlueStart1Park2 extends LinearOpMode {
    public void runOpMode() {

        CompetitionRobot Robot = new CompetitionRobot(this);

        BezierCurveRoute BlueStart1Park2 = new BezierCurveRoute(
                new double[] {370.45, -856.416666666667, 884.3, -254.933333333333, -143.001666666667}, //The x-coefficients
                new double[] {-3.9833333333338, -51.7833333333265, 1422.04999999998, -2039.46666666665, 799.853333333328}, //The y-coefficients
                Robot,
                0.6,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        waitForStart();

        BlueStart1Park2.executeWithPointSkip();
    }
}