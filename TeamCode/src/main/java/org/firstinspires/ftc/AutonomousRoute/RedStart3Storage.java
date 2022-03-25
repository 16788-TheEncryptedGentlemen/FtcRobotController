package org.firstinspires.ftc.AutonomousRoute;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.Robots.WedstrijdRobot;

//Uncomment @Autonomous to show up on the DC controller app
@Autonomous
public class RedStart3Storage extends LinearOpMode
{
    public void runOpMode()
    {
        //Variables------------------------------------
        WedstrijdRobot Robot = new WedstrijdRobot(this);

        BezierCurveRoute RedStart3Storage = new BezierCurveRoute(
                new double[] {-326.235, 1041.44250000001, -1045.62500000005, -522.812499999917, 953.609999999949, 92.0149999999993, -263.497499999992}, //The x-coefficients
                new double[] {12.5475, -75.2849999999999, -1923.94999999999, 6461.96249999997, -8306.44499999995, 4880.97749999996, -1107.16749999999}, //The y-coefficients
                Robot,
                0.6,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );



        //Initialisation------------------------------------


        waitForStart();

        //Run program------------------------------------
        //Run program------------------------------------

        RedStart3Storage.ExecuteWithPointSkip();

    }

}
