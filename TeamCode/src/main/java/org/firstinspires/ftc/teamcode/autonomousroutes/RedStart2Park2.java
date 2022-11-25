package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;


@Autonomous
public class RedStart2Park2 extends LinearOpMode {
    public void runOpMode() {

        CompetitionRobot Robot = new CompetitionRobot(this);

        BezierCurveRoute RedStart2Park2 = new BezierCurveRoute(
                new double[] {-412.275, 1051.6, -1107.36666666666, 207.133333333333, 262.9}, //The x-coefficients 
                new double[] {-3.98333333333403, -87.6333333333348, 1326.45, -1718.80833333334, 604.670000000001}, //The y-coefficients 
                Robot,
                0.6,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW 
                this
        );


        waitForStart();

        RedStart2Park2.executeWithPointSkip();
    }
}