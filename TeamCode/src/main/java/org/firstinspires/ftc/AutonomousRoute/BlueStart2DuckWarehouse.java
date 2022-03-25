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
                new double[] {-2.98750000000007, -23.8999999999996, 406.299999999999, -603.475, 247.9625}, //The x-coefficients
                new double[] {-705.05, 1081.475, -346.550000000001, -418.25, 215.1}, //The y-coefficients
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
        Robot.Drivetrain.TurnRobotAO(0);
        sleep(500);
        Robot.Drivetrain.powerStrafeValues(-90.0, 0.5, 0);
        sleep(1000);
        Robot.Drivetrain.DriveStraight(135.0, 0.6);


    }

}
