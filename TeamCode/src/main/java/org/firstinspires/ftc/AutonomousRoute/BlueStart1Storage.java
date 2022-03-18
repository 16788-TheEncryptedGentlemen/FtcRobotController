package org.firstinspires.ftc.AutonomousRoute;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.Robots.WedstrijdRobot;

//Uncomment @Autonomous to show up on the DC controller app
@Autonomous
public class BlueStart1Storage extends LinearOpMode
{
    public void runOpMode()
    {
        //Variables------------------------------------
        WedstrijdRobot Robot = new WedstrijdRobot(this);

        BezierCurveRoute BlueStart1Storage = new BezierCurveRoute(
                new double[] {161.325, -335.197500000001, 248.560000000001}, //The x-coefficients
                new double[] {17.9250000000009, -172.08, 112.927499999999}, //The y-coefficients
                Robot,
                0.6,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );




        //Initialisation------------------------------------


        waitForStart();

        //Run program------------------------------------
        //Run program------------------------------------

        BlueStart1Storage.ExecuteWithPointSkip();

    }

}
