package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class AutonoomTest extends LinearOpMode {

    @Override
    public void runOpMode() {

        //Variables
        CompetitionRobot robot = new CompetitionRobot(this);
//        robot.webcam.pipeline.cone = PipelineRedMarkerDetection.ConeColour.RED;
        int output = 1;
        int output2 = 1;

        BezierCurveRoute RedStart1Case0 = new BezierCurveRoute(
                new double[] {-111.135, 136.23, -53.3766666666667}, //The x-coefficients
                new double[] {-3.58499999999987, 59.7499999999999, 5.97500000000001}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        BezierCurveRoute RedStart1Case2 = new BezierCurveRoute(
                new double[] {100.38, -142.205, 67.7166666666666}, //The x-coefficients
                new double[] {-13.145, 74.0900000000001, 4.3816666666666}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );


        while (!isStarted() && !isStopRequested())
        {
            // debugging


            output = robot.webcam.pipeline.getBluePosition();

            output2 = robot.webcam.pipeline.getRedPosition();
            // result
//            result = robot.webcam.getResult();
            telemetry.addData("bluePosition", output);
            telemetry.addData("redPosition", output2);
            telemetry.update();

        }

        /*
         * The START command just came in: snapshot the current analysis now
         * for later use. We must do this because the analysis will continue
         * to change as the camera view changes once the robot starts moving!
         */


        /** Initialisation. */
        waitForStart();


        switch(output) {
            case 0: // LEFT
                RedStart1Case0.executeWithPointSkip();
                robot.drivetrain.driveStraight(-10, 0.4);
                break;
            case 2: // RIGHT
                RedStart1Case2.executeWithPointSkip();
                robot.drivetrain.driveStraight(-10, 0.4);
                break;
            default: // Default MIDDLE
                robot.drivetrain.driveStraight(80, 0.4);
                robot.drivetrain.driveStraight(-25, 0.4);
        }



    }
}