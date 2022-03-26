package org.firstinspires.ftc.AutonomousRoute;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.Robots.WedstrijdRobot;

//Uncomment @Autonomous to show up on the DC controller app
@Autonomous
public class RedStart4Storage extends LinearOpMode
{
    public void runOpMode()
    {
        //Variables------------------------------------
        WedstrijdRobot Robot = new WedstrijdRobot(this);

        BezierCurveRoute RedStart4Storage = new BezierCurveRoute(
                new double[] {-75.2850000000053, 967.950000000032, -8431.92000000007, 25371.045, -33577.1099999998, 18369.5399999997, -817.37999999977, -2269.30500000005, 391.959999999998}, //The x-coefficients
                new double[] {-1462.68, 5979.78, -7679.07, -12422.025, 51043.2300000001, -68509.3500000001, 48311.4600000002, -18573.8850000001, 3138.66750000003}, //The y-coefficients
                Robot,
                0.6,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );



        //Initialisation------------------------------------


        waitForStart();

        //Run program------------------------------------
        //Run program------------------------------------

        RedStart4Storage.ExecuteWithPointSkip();

    }

}
