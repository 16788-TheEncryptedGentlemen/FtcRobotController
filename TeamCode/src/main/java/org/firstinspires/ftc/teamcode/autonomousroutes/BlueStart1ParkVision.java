package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;
import org.firstinspires.ftc.teamcode.webcamgarbage.PipelineColourDetection;

/** Uncomment @Autonomous to show up on the DC controller app */
@Autonomous
public class BlueStart1ParkVision extends LinearOpMode
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
            telemetry.addData("Cone red", robot.webcam.pipeline.cone == PipelineColourDetection.ConeColour.RED);
            telemetry.addData("Cone blue", robot.webcam.pipeline.cone == PipelineColourDetection.ConeColour.BLUE);

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

        BezierCurveRoute BlueStart1Park1 = new BezierCurveRoute(
                /** The x-coefficients. */
                new double[] {-243.780000000001, 290.385000000003, -114.720000000002},
                /** The Y-coefficients. */
                new double[] {-22.7049999999988, 44.2149999999988, 43.02},
                robot,
                0.6,
                /** STRAFE or FOLLOW. */
                BezierCurveRoute.DRIVE_METHOD.STRAFE,
                this
        );

        BezierCurveRoute BlueStart1Park2 = new BezierCurveRoute(
                /** The x-coefficients. */
                new double[] {370.45, -856.416666666667, 884.3, -254.933333333333, -143.001666666667},
                /** The Y-coefficients. */
                new double[] {-3.9833333333338, -51.7833333333265, 1422.04999999998, -2039.46666666665, 799.853333333328},
                robot,
                0.6,
                /** STRAFE or FOLLOW. */
                BezierCurveRoute.DRIVE_METHOD.STRAFE,
                this
        );

        BezierCurveRoute BlueStart1Park3 = new BezierCurveRoute(
                /** The x-coefficients. */
                new double[] {152.96, -139.815, 43.8166666666667},
                /** The Y-coefficients. */
                new double[] {8.36500000000046, -65.7250000000004, 121.093333333333},
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
        robot.lift.liftMidPole();
        sleep(2000);

        //webcam.close();

        switch (result) {
            case 1: BlueStart1Park1.executeWithPointSkip();
                //route voor rood
                break;

            case 2: BlueStart1Park2.executeWithPointSkip();
                //route voor geel
                break;

            case 3: BlueStart1Park3.executeWithPointSkip();
                // route voor blauw
                break;
        }

        // routes die algemeen zijn

    }

}