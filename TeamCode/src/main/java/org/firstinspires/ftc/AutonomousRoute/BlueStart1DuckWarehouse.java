package org.firstinspires.ftc.AutonomousRoute;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.Robots.WedstrijdRobot;

//Uncomment @Autonomous to show up on the DC controller app
@Autonomous
public class  BlueStart1DuckWarehouse extends LinearOpMode
{
    public void runOpMode()
    {
        //Variables------------------------------------
        WedstrijdRobot Robot = new WedstrijdRobot(this);

        BezierCurveRoute BlueStart1Duck = new BezierCurveRoute(
                new double[] {23.8999999999986, 107.550000000008, -113.52500000002, -5.97499999998126, 13.7424999999947}, //The x-coefficients
                new double[] {-149.374999999998, 233.025, -149.375000000003, -83.6499999999967, 93.8074999999985}, //The y-coefficients
                Robot,
                0.6   ,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );




        //Initialisation------------------------------------

        waitForStart();

        //Run program------------------------------------

        BlueStart1Duck.ExecuteWithPointSkip();
        Robot.spinner.TurnRight();
        sleep(4000);
        Robot.spinner.Stop();
        Robot.Drivetrain.DriveStraight(120.0, 0.2);
        Robot.Drivetrain.powerStrafeValues(-90.0, 0.5);
        sleep(2000);
        Robot.Drivetrain.DriveStraight(90.0, 0.2);


    }

}
