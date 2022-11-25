package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;


@Autonomous
public class RedStart2Park1 extends LinearOpMode {
    public void runOpMode() {

        CompetitionRobot Robot = new CompetitionRobot(this);

        BezierCurveRoute RedStart2Park1 = new BezierCurveRoute(
                new double[] {-195.98, 212.71, -74.8866666666666}, //The x-coefficients
                new double[] {9.55999999999847, -50.1899999999981, 101.575}, //The y-coefficients
                Robot,
                0.6,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        waitForStart();

        RedStart2Park1.executeWithPointSkip();
    }
}