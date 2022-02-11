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

        waitForStart();

        //Run program------------------------------------


        while(Math.abs(Robot.imu.getAngle() - 90) > 0 && !isStopRequested()) {
            Robot.Drivetrain.TurnRobotNoLoop(90);
            telemetry.addData("Angle", Robot.imu.getAngle());
        }
        Robot.Drivetrain.Stop();

        //Robot.Drivetrain.TurnRobotAO(90);
        //sleep(3000);
    }

}
