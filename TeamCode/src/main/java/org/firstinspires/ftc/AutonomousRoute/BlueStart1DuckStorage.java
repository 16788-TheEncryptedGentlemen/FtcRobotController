package org.firstinspires.ftc.AutonomousRoute;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.Robots.WedstrijdRobot;

//Uncomment @Autonomous to show up on the DC controller app
@Autonomous
public class BlueStart1DuckStorage extends LinearOpMode
{
    public void runOpMode()
    {
        //Variables------------------------------------
        WedstrijdRobot Robot = new WedstrijdRobot(this);

        BezierCurveRoute BlueStart1Duck = new BezierCurveRoute(
                new double[] {23.8999999999986, 107.550000000008, -113.52500000002, -5.97499999998126, 10.7549999999944}, //The x-coefficients
                new double[] {-149.374999999998, 233.025, -149.375000000003, -83.6499999999967, 93.8074999999988}, //The y-coefficients
                Robot,
                0.6,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );





        BezierCurveRoute BlueDuckStorage = new BezierCurveRoute(
                new double[] {-11.9500000000002, 71.7000000000005, 328.624999999999, -600.4875, 258.7175}, //The x-coefficients
                new double[] {140.4125, -304.725000000001, 286.800000000002, -113.525000000002, -6.57249999999959}, //The y-coefficients
                Robot,
                0.6,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );


        //Initialisation------------------------------------


        waitForStart();

        //Run program------------------------------------
        //Run program------------------------------------

        BlueStart1Duck.ExecuteWithPointSkip();
        Robot.spinner.TurnRight();
        sleep(4000);
        Robot.spinner.Stop();
        Robot.Drivetrain.DriveStraight(5.0, 0.2);
        BlueDuckStorage.ExecuteWithPointSkip();
        Robot.Drivetrain.DriveStraight(-20.0, 0.2);
    }

}
