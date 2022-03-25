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
                new double[] {23.8999999999986, 107.550000000008, -113.52500000002, -5.97499999998126, 13.7424999999947}, //The x-coefficients
                new double[] {-149.374999999998, 233.025, -149.375000000003, -83.6499999999967, 93.8074999999985}, //The y-coefficients
                Robot,
                0.6,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );



        BezierCurveRoute BlueDuckStorage = new BezierCurveRoute(
                new double[] {0, -16.73, 66.9199999999964, 3346.00000000001, -11008.34, 13885.9, -7977.82000000001, 1752.4675}, //The x-coefficients
                new double[] {133.84, -451.71, 869.959999999997, -1171.09999999999, 1238.01999999999, -970.339999999998, 415.860000000004, -66.3225000000004}, //The y-coefficients
                Robot,
                0.6 ,
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
