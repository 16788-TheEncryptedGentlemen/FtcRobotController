package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class RedStart1VisionPushBoardParkB extends LinearOpMode {
    private final boolean BLUE_SIDE = false;
    private final boolean SKIP_VISION = false;
    private BezierCurveRoute case0;
    private BezierCurveRoute doNothing;
    private BezierCurveRoute case2;
    private BezierCurveRoute backToMiddle;
    private BezierCurveRoute case0Board;
    private BezierCurveRoute case1Board;
    private BezierCurveRoute case2Board;
    private BezierCurveRoute case0BoardParkB;
    private BezierCurveRoute case1BoardParkB;
    private BezierCurveRoute case2BoardParkB;

    private CompetitionRobot robot;

    private void initAutonomous() {
        robot = new CompetitionRobot(this);

        case0 = new BezierCurveRoute(
                new double[] {-3.18666666666675, 54.97, -129.06, 49.7916666666665}, //The x-coefficients
                new double[] {337.786666666667, -619.01, 497.12, -148.18}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        doNothing = new BezierCurveRoute(
                new double[] {}, //The x-coefficients
                new double[] {}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case2 = new BezierCurveRoute(
                new double[] {121.093333333333, -167.3, 108.346666666667, -33.0616666666667}, //The x-coefficients
                new double[] {1.59333333333325, -14.3399999999995, 17.5266666666658, 59.3516666666669}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        backToMiddle = new BezierCurveRoute(
                new double[] {0, 63.7333333333331, -625.383333333333, 850.441666666666, -330.218333333333}, //The x-coefficients
                new double[] {-81.6583333333335, 219.083333333334, -302.733333333335, 274.850000000001, -145.391666666667}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case0BoardParkB= new BezierCurveRoute(
                new double[] {360.093333333333, 100.380000000001, -3122.93333333334, 8811.13333333334, -15391.6, 18692.9866666667, -13511.4666666667, 4207.99333333333}, //The x-coefficients
                new double[] {-12.7466666666668, 89.2266666666674, 89.2266666666628, -585.54999999999, -3457.53333333335, 10283.3733333333, -9209.46666666667, 2746.90666666666}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case1BoardParkB = new BezierCurveRoute(
                new double[] {332.608333333333, -760.816666666667, 1011.76666666667, -979.900000000001, 515.443333333334}, //The x-coefficients
                new double[] {-37.8416666666669, 533.766666666667, -1915.98333333333, 2103.2, -734.128333333334}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case2BoardParkB = new BezierCurveRoute(
                new double[] {384.79, -1430.415, 3081.10833333334, -4461.33333333334, 4592.385, -3175.91166666667, 1101.79}, //The x-coefficients
                new double[] {-30.6716666666667, 267.68, -822.558333333332, -627.375, 3471.475, -3385.03666666666, 1070.32166666666}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );
    }

    @Override
    public void runOpMode() {
        int markerPosition = 1;

        initAutonomous();

        // TODO: Hier 1 functie van maken.
        robot.grabber.grab();
        robot.tiltMechanism.TiltMechanismUp();

        while (!isStarted() && !isStopRequested()) {
            markerPosition = robot.webcam.getMarkerPosition(BLUE_SIDE);
            // result
            telemetry.addData("Position", markerPosition);
            telemetry.update();
        }

        // Initialisation.
        waitForStart();

        // Choose default option if skip.
        if (SKIP_VISION) {
            markerPosition = 1;
        }

        switch (markerPosition) {
            case 0: // LEFT
                robot.pusher.releasePreLoadRight();
                robot.drivetrain.driveStraight(-20, 0.4);
                anySidePixelPlacement(case0, doNothing, case0Board, case0BoardParkB);
                return;
            case 2: // RIGHT
                anySidePixelPlacement(case2, backToMiddle, case2Board, case2BoardParkB);
                return;
            default: // Default MIDDLE
                // Rechtdoor rijden is nauwkeuriger dan met een bezier route rijden,
                // dus we rijden eerst rechtvooruit en rijden dan een lege beziercurve route.
                robot.drivetrain.driveStraight(80, 0.4);
                robot.pusher.releasePreLoadRight();
                robot.drivetrain.driveStraight(-20, 0.4);
                anySidePixelPlacement(doNothing, doNothing, case1Board, case1BoardParkB);
                return;
        }
    }

    private void anySidePixelPlacement(BezierCurveRoute pushPixel, BezierCurveRoute backUp, BezierCurveRoute toBoard, BezierCurveRoute parkB) {
        //Push pixel naar de linker streep.
        pushPixel.executeWithPointSkip();
        robot.pusher.releasePreLoadRight();
        backUp.executeWithPointSkip();
        //Rij een stuk naar achter zodat de pixel niet meer onder de robot ligt.
        toBoard.executeWithPointSkip();
        //Rij naar de backstage en parkeer.
        parkB.executeWithPointSkip();
    }
}
