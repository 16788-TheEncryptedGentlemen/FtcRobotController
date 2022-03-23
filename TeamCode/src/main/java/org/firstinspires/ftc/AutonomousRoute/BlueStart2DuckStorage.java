package org.firstinspires.ftc.AutonomousRoute;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.Robots.WedstrijdRobot;

//Uncomment @Autonomous to show up on the DC controller app
@Autonomous
public class BlueStart2DuckStorage extends LinearOpMode
{
    public void runOpMode()
    {
        //Variables------------------------------------
        WedstrijdRobot Robot = new WedstrijdRobot(this);

        BezierCurveRoute BlueStart2Duck = new BezierCurveRoute(
                new double[] {-2.98750000000007, 53.7750000000005, 53.7749999999987, -83.6499999999994, 15.5349999999997}, //The x-coefficients
                new double[] {-642.3125, 985.875, -752.849999999999, 301.737499999998, -77.0774999999994}, //The y-coefficients
                Robot,
                0.6,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );


        BezierCurveRoute BlueDuckStorage = new BezierCurveRoute(
                new double[] {0, -12.5474999999997, 41.8249999999989, 1673, -4128.1275, 3471.47500000001, -997.227500000002}, //The x-coefficients
                new double[] {117.11, -338.7825, 543.725000000001, -585.549999999997, 464.257499999997, -242.584999999999, 51.9825000000005}, //The y-coefficients
                Robot,
                0.6,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );


        //Initialisation------------------------------------

        waitForStart();

        //Run program------------------------------------

        BlueStart2Duck.ExecuteWithPointSkip();
        Robot.Drivetrain.powerStrafeValues(-90.0, 0.5);
        sleep(200);
        Robot.spinner.TurnRight();
        sleep(4000);
        Robot.spinner.Stop();
        BlueDuckStorage.ExecuteWithPointSkip();

    }

}
