package org.firstinspires.ftc.AutonomousRoute;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.Robots.WedstrijdRobot;

//Uncomment @Autonomous to show up on the DC controller app
@Autonomous
public class BlueStart1DuckWarehouse extends LinearOpMode
{
    public void runOpMode()
    {
        //Variables------------------------------------
        WedstrijdRobot Robot = new WedstrijdRobot(this);

        BezierCurveRoute BlueStart1Duck = new BezierCurveRoute(
                new double[] {23.9000000000002, 107.55, -113.524999999999, -5.97500000000082, 13.7425000000002}, //The x-coefficients
                new double[] {-149.375, 233.025000000001, -149.375000000001, -83.649999999999, 93.8074999999994}, //The y-coefficients
                Robot,
                0.2,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );



        //Initialisation------------------------------------

        waitForStart();

        //Run program------------------------------------

        BlueStart1Duck.ExecuteWithPointSkip();
        Robot.spinner.Turn();
        sleep(4000);
        Robot.spinner.Stop();
        Robot.Drivetrain.DriveStraight(120.0, 0.2);
        Robot.Drivetrain.powerStrafeValues(-90.0, 0.5);
        sleep(2000);
        Robot.Drivetrain.DriveStraight(90.0, 0.2);


    }

}
