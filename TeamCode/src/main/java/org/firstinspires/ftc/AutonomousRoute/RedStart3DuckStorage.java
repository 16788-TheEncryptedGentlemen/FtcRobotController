package org.firstinspires.ftc.AutonomousRoute;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.Robots.WedstrijdRobot;

//Uncomment @Autonomous to show up on the DC controller app
@Autonomous
public class RedStart3DuckStorage extends LinearOpMode
{
    public void runOpMode()
    {
        //Variables------------------------------------
        WedstrijdRobot Robot = new WedstrijdRobot(this);

        //Initialisation------------------------------------
        BezierCurveRoute RedStart3SideTurn = new BezierCurveRoute(
                new double[] {-32.8625}, //The x-coefficients
                new double[] {0}, //The y-coefficients
                Robot,
                0.6,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );


        waitForStart();

        RedStart3SideTurn.ExecuteWithPointSkip();
        Robot.Drivetrain.TurnRobotAO(0);
        sleep(500);
        Robot.Drivetrain.TurnRobotAO(180);


    }

}
