package org.firstinspires.ftc.AutonomousRoute;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.Robots.WedstrijdRobot;

//Uncomment @Autonomous to show up on the DC controller app
@Autonomous
public class TestWebcam extends LinearOpMode
{
    public void runOpMode()
    {
        //Variables------------------------------------
        WedstrijdRobot Robot = new WedstrijdRobot(this);

        //Initialisation------------------------------------

        telemetry.clearAll();

        int DuckPosition;

        while(!isStarted() && !isStopRequested()) {
            DuckPosition = Robot.Webcam.getPositionOfDuck();
            telemetry.addData("output", DuckPosition);
            telemetry.update();
        }

        waitForStart();

        //Run program------------------------------------


//        Robot.Drivetrain.DriveStraight(60.0, 0.6);
//        Robot.Drivetrain.TurnRobotAO(0);
//        sleep(200);
//        Robot.Drivetrain.powerStrafeValues(-90.0, 0.5, 0);
        sleep(2000);


    }

}
