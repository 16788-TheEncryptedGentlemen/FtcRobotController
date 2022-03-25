package org.firstinspires.ftc.AutonomousRoute;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.Robots.WedstrijdRobot;

//Uncomment @Autonomous to show up on the DC controller app
@Autonomous
public class RedStart3DuckStorage extends LinearOpMode
{
    public void runOpMode()
    {
        //Variables------------------------------------
        WedstrijdRobot Robot = new WedstrijdRobot(this);

        //Initialisation------------------------------------
        BezierCurveRoute Test = new BezierCurveRoute(
                new double[] {258.12, -953.609999999999, 3613.68, 7779.44999999999, -39917.78, 54221.93, -31433.28, 6501.39749999999}, //The x-coefficients
                new double[] {-564.04, 819.77, -635.739999999999, -2425.85000000001, 9502.64000000002, -6240.29000000003, -3867.01999999999, 3424.2725}, //The y-coefficients
                Robot,
                0.5,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        waitForStart();

        Test.ExecuteWithPointSkip();

    }

}
