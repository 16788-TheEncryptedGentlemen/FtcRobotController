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
                new double[] {20.9124999999981, -476.804999999994, 4998.08749999999, -13593.125, 16161.18, -8921.27249999999, 1881.52749999999}, //The x-coefficients
                new double[] {-953.609999999998, 2760.44999999998, -3617.86249999995, 3722.42499999994, -6349.03499999997, 6666.905, -2405.535}, //The y-coefficients
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
