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
                new double[] {-279.63, 743.8875, -597.5, -224.062499999999, 272.459999999998, 13.1450000000009}, //The x-coefficients
                new double[] {10.755, -53.7750000000001, -1099.4, 2769.4125, -2373.27, 697.2825}, //The y-coefficients
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
