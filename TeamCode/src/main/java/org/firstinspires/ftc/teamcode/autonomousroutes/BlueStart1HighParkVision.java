package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;
import org.firstinspires.ftc.teamcode.webcamgarbage.PipelineColourDetection;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class BlueStart1HighParkVision extends LinearOpMode {

    int[] output;
    int result;

    @Override
    public void runOpMode() {

        //Variables
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

        robot.imu.resetAngularOrientation();

        BezierCurveRoute BlueStart1High = new BezierCurveRoute(
                new double[] {299.546666666667, -540.14, 414.266666666667, -113.525}, //The x-coefficients
                new double[] {46.2066666666676, 16.7299999999984, 97.1933333333329, -37.044999999999}, //The y-coefficients
                robot,
                0.5,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        BezierCurveRoute BlueStart1HighPark1 = new BezierCurveRoute(
                new double[] {-175.266666666667, -265.29, 599.093333333333, -275.646666666666}, //The x-coefficients
                new double[] {38.2400000000001, -174.47, 310.700000000001, -207.93}, //The y-coefficients
                robot,
                0.6,
                BezierCurveRoute.DRIVE_METHOD.STRAFE,
                this
        );


        BezierCurveRoute BlueStart1HighPark2 = new BezierCurveRoute(
                new double[] {-167.3, 81.2599999999993, 101.973333333334, -77.2766666666669}, //The x-coefficients
                new double[] {0, 26.2900000000006, -50.9866666666676, 7.96666666666712}, //The y-coefficients
                robot,
                0.6,
                BezierCurveRoute.DRIVE_METHOD.STRAFE,
                this
        );

        /** Initialisation. */
        waitForStart();

        telemetry.addData("enc", robot.autolift.currentEnc());
        telemetry.update();
        sleep(200);

        robot.grabber.grab();
        sleep(1000);
        robot.autolift.liftGroundJunction();
        sleep(500);
        robot.drivetrain.driveStraight(1,0.3);
        BlueStart1High.executeWithPointSkip();
        robot.drivetrain.turnRobotAO(-45);
        robot.autolift.liftHighPole();
        robot.drivetrain.driveStraight(16,0.3);
        sleep(500);
        robot.autolift.liftMidPole();
        sleep(200);
        robot.grabber.drop();
        sleep(200);
        robot.drivetrain.driveStraight(16,-0.3);
        robot.autolift.liftGroundJunction();
        robot.drivetrain.turnRobotAO(0);
        switch (result) {
            case 1: BlueStart1HighPark1.executeWithPointSkip(); //TODO: verander beziercurve
                //route voor rood
                break;

            case 2: BlueStart1HighPark2.executeWithPointSkip();
                //route voor geel
                break;

            case 3: robot.drivetrain.driveStraight(10,-0.3);
                // route voor blauw
                break;
        }


    }
}