package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class RedStart1VisionPushParkB extends LinearOpMode {
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
                new double[]{-111.135, 136.23, -53.3766666666667}, //The x-coefficients
                new double[]{-3.58499999999987, 59.7499999999999, 5.97500000000001}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case2 = new BezierCurveRoute(
                new double[]{100.38, -142.205, 67.7166666666666}, //The x-coefficients
                new double[]{-13.145, 74.0900000000001, 4.3816666666666}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case0ParkB= new BezierCurveRoute(
                new double[] {-272.46, 215.100000000001, 187.216666666665, 98.5875000000008, -709.83, 415.063333333333}, //The x-coefficients
                new double[] {46.6049999999964, -492.937499999991, 1400.14166666666, -1315.99375000001, 826.940000000008, -217.490000000003}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case1ParkB = new BezierCurveRoute(
                new double[]{100.38, -142.205, 67.7166666666666}, //The x-coefficients
                new double[]{-13.145, 74.0900000000001, 4.3816666666666}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case2ParkB = new BezierCurveRoute(
                new double[] {-243.780000000001, 290.385000000003, -114.720000000002}, //The x-coefficients
                new double[] {-22.7049999999988, 44.2149999999988, 43.02}, //The y-coefficients
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