package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;
import org.firstinspires.ftc.teamcode.webcamgarbage.PipelineColourDetection;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class RedStart1HighParkVision extends LinearOpMode {

    int[] output;
    int result;

    @Override
    public void runOpMode() {

        //Variables
        CompetitionRobot robot = new CompetitionRobot(this);
        robot.webcam.pipeline.cone = PipelineColourDetection.ConeColour.RED;


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

       // Autonomous routes:
         BezierCurveRoute RedStart1High = new BezierCurveRoute(
        	new double[] {264.493333333333, -434.98, 309.106666666667, -78.4716666666669}, //The x-coefficients
        	new double[] {11.1533333333339, -35.8500000000006, 307.513333333333, -159.731666666666}, //The y-coefficients
        	robot,
        	0.6,
        	/** STRAFE or FOLLOW. */
          BezierCurveRoute.DRIVE_METHOD.STRAFE,
        	this
        );

        BezierCurveRoute RedStart1HighPark1 = new BezierCurveRoute(
                new double[] {-167.3, -329.820000000001, 675.573333333334, -302.733333333334}, //The x-coefficients
                new double[] {0, 43.02, -49.3933333333334, -21.51}, //The y-coefficients
                robot,
                0.6,
                BezierCurveRoute.DRIVE_METHOD.STRAFE,
                this
        );

        BezierCurveRoute RedStart1HighPark2 = new BezierCurveRoute(
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
        RedStart1High.executeWithPointSkip();
        robot.drivetrain.turnRobotAO(-45);
        robot.autolift.liftHighPole();
        robot.drivetrain.driveStraight(18,0.3);
        sleep(500);
        robot.autolift.liftMidPole();
        sleep(200);
        robot.grabber.drop();
        sleep(200);
        robot.drivetrain.driveStraight(18,-0.3);
        robot.autolift.liftGroundJunction();
        robot.drivetrain.turnRobotAO(0);
        switch (result) {
            case 1: RedStart1HighPark1.executeWithPointSkip(); //TODO: verander beziercurve
                //route voor rood
                break;

            case 2: RedStart1HighPark2.executeWithPointSkip();
                //route voor geel
                break;

            case 3: robot.drivetrain.driveStraight(10,-0.3);
                // route voor blauw
                break;
        }


    }
}