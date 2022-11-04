package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;

//Uncomment @Autonomous to show up on the DC controller app
@Autonomous
public class Test extends LinearOpMode
{
    public void runOpMode()
    {
        //Variables------------------------------------
        CompetitionRobot robot = new CompetitionRobot(this);

        BezierCurveRoute RedStart1Park1 = new BezierCurveRoute(
                new double[] {-243.780000000001, 290.385000000003, -114.720000000002}, //The x-coefficients
                new double[] {-22.7049999999988, 44.2149999999988, 43.02}, //The y-coefficients
                robot,
                0.6,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );






        //Initialisation------------------------------------


        waitForStart();

        //Run program------------------------------------
        //Run program------------------------------------

        RedStart1Park1.ExecuteWithPointSkip();

    }

}