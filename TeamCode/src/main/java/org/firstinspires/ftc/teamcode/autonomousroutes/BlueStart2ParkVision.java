package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;
import org.firstinspires.ftc.teamcode.webcamgarbage.PipelineColourDetection;

/** Uncomment @Autonomous to show up on the DC controller app */
@Autonomous
public class BlueStart2ParkVision extends LinearOpMode
{

    int[] output;
    int result;

    @Override
    public void runOpMode()
    {

        //Variables------------------------------------
        CompetitionRobot robot = new CompetitionRobot(this);
        robot.webcam.pipeline.cone = PipelineColourDetection.ConeColour.BLUE;


        while (!isStarted() && !isStopRequested())
        {
            // debugging
            output = robot.webcam.getAnalysis();
            telemetry.addData("R", output[0]);
            telemetry.addData("Y", output[1]);
            telemetry.addData("B", output[2]);

            // result
            result = robot.webcam.getResult();
            telemetry.addData("Result", result);
            telemetry.update();

        }

        /*
         * The START command just came in: snapshot the current analysis now
         * for later use. We must do this because the analysis will continue
         * to change as the camera view changes once the robot starts moving!
         */

        /*
         * Show that snapshot on the telemetry
         */
        telemetry.addData("Final result", result);
        telemetry.update();



        // autonomous routes:

        BezierCurveRoute BlueStart2Park1 = new BezierCurveRoute(
                /** The x-coefficients. */
                new double[] {-195.98, 212.71, -74.8866666666666},
                /** The Y-coefficients. */
                new double[] {9.55999999999847, -50.1899999999981, 101.575},
                robot,
                0.6,
                /** STRAFE or FOLLOW. */
                BezierCurveRoute.DRIVE_METHOD.STRAFE,
                this
        );

        BezierCurveRoute BlueStart2Park2 = new BezierCurveRoute(
                new double[] {-412.275, 1051.6, -1127.28333333333, 209.124999999998, 281.223333333334}, //The x-coefficients
                new double[] {-3.98333333333403, -87.6333333333348, 880.316666666671, -764.800000000003, 101.176666666667}, //The y-coefficients
                robot,
                0.6,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );


        BezierCurveRoute BlueStart2Park3 = new BezierCurveRoute(
                /** The x-coefficients. */
                new double[] {209.125, -225.855, 79.6666666666661},
                /** The Y-coefficients. */
                new double[] {13.1449999999976, -76.479999999996, 131.051666666665},
                robot,
                0.6,
                /** STRAFE or FOLLOW. */
                BezierCurveRoute.DRIVE_METHOD.STRAFE,
                this
        );



        waitForStart();


        // Close robot grabber around cone before driving.
        robot.grabber.grab();
        sleep(3000);

        //webcam.close();

        switch (result) {
            case 1: BlueStart2Park1.executeWithPointSkip();
                //route voor rood
                break;

            case 2: BlueStart2Park2.executeWithPointSkip();
                //route voor geel
                robot.drivetrain.driveStraight(10,-0.3);
                break;

            case 3: BlueStart2Park3.executeWithPointSkip();
                // route voor blauw
                break;
        }

        // routes die algemeen zijn

    }

}