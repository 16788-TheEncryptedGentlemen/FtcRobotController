package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;


@Autonomous
public class RedStart2Park3 extends LinearOpMode {
    public void runOpMode() {

        CompetitionRobot Robot = new CompetitionRobot(this);

        BezierCurveRoute RedStart2Park3 = new BezierCurveRoute(
                new double[] {209.125, -225.855, 79.6666666666661}, //The x-coefficients
                new double[] {13.1449999999976, -76.479999999996, 131.051666666665}, //The y-coefficients
                Robot,
                0.6,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );


        waitForStart();

        RedStart2Park3.executeWithPointSkip();
    }
}