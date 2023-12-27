package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class AutonoomTest2 extends LinearOpMode {
    private final boolean BLUE_SIDE = false;
    private final boolean SKIP_VISION = false;
    private BezierCurveRoute case0;
    private BezierCurveRoute case2;
    private CompetitionRobot robot;
    private BezierCurveRoute redStart1Case2Board;

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
                new double[] {100.38, -142.204999999999, 67.7166666666665}, //The x-coefficients
                new double[] {-13.1450000000003, 74.0900000000012, 4.38166666666595}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        redStart1Case2Board = new BezierCurveRoute(
                new double[] {-268.875, 914.175, -1864.2, 9410.62499999999, -47730.69, 122840.025, -148347.3, 74827.9125000001, -3901.67500000004, -5712.69749999999}, //The x-coefficients
                new double[] {23.8999999999999, -564.637499999999, 4086.89999999999, 11418.2250000001, -90944.2800000001, 197874.075, -215745.3, 133630.875, -48463.225, 8715.73250000001}, //The y-coefficients
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
        robot.drivetrain.driveStraight(80, 0.4);
        robot.drivetrain.driveStraight(-25, 0.4);
    }

    private void rightPixelPlacement() {
        case2.executeWithPointSkip();
        robot.drivetrain.driveStraight(-10, 0.4);
        redStart1Case2Board.executeWithPointSkip();
        robot.drivetrain.turnRobotAO(90);
    }

    private void leftPixelPlacement() {
        case0.executeWithPointSkip();
        robot.drivetrain.driveStraight(-10, 0.4);
    }
}