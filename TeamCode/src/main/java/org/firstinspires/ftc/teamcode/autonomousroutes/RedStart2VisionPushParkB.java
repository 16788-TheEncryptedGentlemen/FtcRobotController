package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class RedStart2VisionPushParkB extends LinearOpMode {
    private final boolean BLUE_SIDE = false;
    private final boolean SKIP_VISION = false;
    private BezierCurveRoute case0;
    private BezierCurveRoute case2;
    private BezierCurveRoute case0ParkB;
    private BezierCurveRoute case1ParkB;
    private BezierCurveRoute case2ParkB;
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

        case2 = new BezierCurveRoute(
                new double[] {121.093333333333, -167.3, 108.346666666667, -33.0616666666667}, //The x-coefficients
                new double[] {1.59333333333325, -14.3399999999995, 17.5266666666658, 59.3516666666669}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case0ParkB= new BezierCurveRoute(
                new double[] {360.093333333333, 100.380000000001, -3122.93333333334, 8811.13333333334, -15391.6, 18692.9866666667, -13511.4666666667, 4207.99333333333}, //The x-coefficients
                new double[] {-12.7466666666668, 89.2266666666674, 89.2266666666628, -585.54999999999, -3457.53333333335, 10283.3733333333, -9209.46666666667, 2746.90666666666}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case1ParkB = new BezierCurveRoute(
                new double[] {332.608333333333, -760.816666666667, 1011.76666666667, -979.900000000001, 515.443333333334}, //The x-coefficients
                new double[] {-37.8416666666669, 533.766666666667, -1915.98333333333, 2103.2, -734.128333333334}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case2ParkB = new BezierCurveRoute(
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
                leftPixelPlacement();
                return;
            case 2: // RIGHT
                rightPixelPlacement();
                return;
            default: // Default MIDDLE
                middlePixelPlacement();
                return;
        }
    }

    private void middlePixelPlacement() {
        //Push pixel naar de middelste streep.
        robot.drivetrain.driveStraight(80, 0.4);
        //Rij een stuk naar achter zodat de pixel niet meer onder de robot ligt.
        robot.drivetrain.driveStraight(-25, 0.4);
        //Rij naar de backstage en parkeer.
        case1ParkB.executeWithPointSkip();
    }

    private void rightPixelPlacement() {
        //Push pixel naar de rechter streep.
        case2.executeWithPointSkip();
        //Rij een stuk naar achter zodat de pixel niet meer onder de robot ligt.
        robot.drivetrain.driveStraight(-10, 0.4);
        //Rij naar de backstage en parkeer.
        case2ParkB.executeWithPointSkip();
    }

    private void leftPixelPlacement() {
        //Push pixel naar de linker streep.
        case0.executeWithPointSkip();
        //Rij een stuk naar achter zodat de pixel niet meer onder de robot ligt.
        robot.drivetrain.driveStraight(-10, 0.4);
        //Rij naar de backstage en parkeer.
        case0ParkB.executeWithPointSkip();
    }
}