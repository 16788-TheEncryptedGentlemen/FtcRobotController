package org.firstinspires.ftc.AutonomousRoute;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.Robots.WedstrijdRobot;

//Uncomment @Autonomous to show up on the DC controller app
@Autonomous
public class BlueStart2DuckWarehouse extends LinearOpMode
{
    public void runOpMode()
    {
        //Variables------------------------------------
        WedstrijdRobot Robot = new WedstrijdRobot(this);

        BezierCurveRoute BlueStart2Duck = new BezierCurveRoute(
                new double[] {-3.58500000000015, 80.6625000000013, 107.549999999997, -250.949999999997, 93.2099999999976, -5.97499999999965}, //The x-coefficients
                new double[] {-770.775, 1478.8125, -1505.7, 905.212499999998, -462.464999999997, 167.897499999999}, //The y-coefficients
                Robot,
                0.6,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );






        //Initialisation------------------------------------

        waitForStart();

        //Run program------------------------------------

        BlueStart2Duck.ExecuteWithPointSkip();
        Robot.spinner.TurnRight();
        sleep(4000);
        Robot.spinner.Stop();
        Robot.Drivetrain.DriveStraight(120.0, 0.6);
        Robot.Drivetrain.powerStrafeValues(-90.0, 0.5);
        sleep(2000);
        Robot.Drivetrain.DriveStraight(110.0, 0.6);


    }

}
