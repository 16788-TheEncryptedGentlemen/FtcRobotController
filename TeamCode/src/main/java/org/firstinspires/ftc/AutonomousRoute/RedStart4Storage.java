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
                new double[] {-66.9199999999998, 752.849999999998, -5621.27999999999, 14095.025, -14923.16, 6123.17999999997, -262.899999999992, -164.312500000001}, //The x-coefficients
                new double[] {-1300.16, 4650.94, -5119.38, -6901.12500000001, 22685.88, -22836.45, 10750.22, -2093.0425}, //The y-coefficients
                Robot,
                0.2,
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
