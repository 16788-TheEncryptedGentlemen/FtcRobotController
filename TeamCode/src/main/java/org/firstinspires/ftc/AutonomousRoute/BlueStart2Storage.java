package org.firstinspires.ftc.AutonomousRoute;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.Robots.WedstrijdRobot;

//Uncomment @Autonomous to show up on the DC controller app
@Autonomous
public class BlueStart2Storage extends LinearOpMode
{
    public void runOpMode()
    {
        //Variables------------------------------------
        WedstrijdRobot Robot = new WedstrijdRobot(this);

        BezierCurveRoute BlueStart2Storage = new BezierCurveRoute(
                new double[] {20.9124999999999, -476.804999999999, 4998.0875, -13593.125, 16161.18, -8912.9075, 1878.54}, //The x-coefficients
                new double[] {-953.61, 2760.45, -3617.8625, 3722.425, -6349.035, 6717.095, -2448.555}, //The y-coefficients
                Robot,
                0.6,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );







        //Initialisation------------------------------------

        waitForStart();

        //Run program------------------------------------

        BlueStart2Storage.ExecuteWithPointSkip();


    }

}
