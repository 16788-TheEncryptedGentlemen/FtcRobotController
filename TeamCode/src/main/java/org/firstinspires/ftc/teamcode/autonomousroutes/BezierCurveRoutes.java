package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

public class BezierCurveRoutes {
    public BezierCurveRoute BlueStart1ParkB;

    public BezierCurveRoutes(CompetitionRobot robot, LinearOpMode opMode) {
        BlueStart1ParkB = new BezierCurveRoute(
                new double[]{11.9499999999987, -77.674999999998, -176.860000000001}, //The x-coefficients
                new double[]{45.4100000000003, -83.6499999999998, 41.4266666666665}, //The y-coefficients
                robot,
                0.6,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                opMode
        );
    }
}

