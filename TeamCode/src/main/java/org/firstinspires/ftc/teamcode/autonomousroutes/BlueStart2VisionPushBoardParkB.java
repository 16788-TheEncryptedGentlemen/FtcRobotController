package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class BlueStart2VisionPushBoardParkB extends LinearOpMode {
    private final boolean BLUE_SIDE = true;
    private final boolean SKIP_VISION = false;
    private BezierCurveRoute case0;
    private BezierCurveRoute case2;
    private BezierCurveRoute case0Board0;
    private BezierCurveRoute case1Board1;
    private BezierCurveRoute case2Board2;
    private BezierCurveRoute case0ParkB;
    private BezierCurveRoute case1ParkB;
    private BezierCurveRoute case2ParkB;
    private CompetitionRobot robot;

    private void initAutonomous() {
        robot = new CompetitionRobot(this);

        case0 = new BezierCurveRoute(
                new double[] {-109.94, 136.23, -54.1733333333334}, //The x-coefficients
                new double[] {-5.97500000000014, 3.58500000000015, 65.3266666666666}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case2 = new BezierCurveRoute(
                new double[] {0, 35.8500000000002, -130.653333333334, 125.873333333333}, //The x-coefficients
                new double[] {240.593333333333, -313.09, 154.553333333333, -17.1283333333333}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case0Board0 = new BezierCurveRoute(
                new double[] {-3.18666666666675, 4.78000000000014, -1.59333333333333, -60.9450000000001}, //The x-coefficients
                new double[] {-66.92, 100.38, -55.7666666666667, 8.36500000000008}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case1Board1 = new BezierCurveRoute(
                new double[] {-88.8283333333333}, //The x-coefficients
                new double[] {-0.398333333333355}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case2Board2 = new BezierCurveRoute(
                new double[] {-468.041666666667, 717, -246.966666666667, -201.158333333333, 80.4633333333335}, //The x-coefficients
                new double[] {-5.97500000000008, -23.8999999999998, 306.716666666666, -440.158333333332, 181.241666666666}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case0ParkB = new BezierCurveRoute(
                new double[] {4.77999999999997, 7.17000000000007, -42.6216666666667}, //The x-coefficients
                new double[] {-108.745, 112.33, -39.0366666666666}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case1ParkB = new BezierCurveRoute(
                new double[] {-1.19499999999999, 32.265, -62.5383333333333}, //The x-coefficients
                new double[] {-175.665, 168.495, -50.9866666666667}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case2ParkB = new BezierCurveRoute(
                new double[] {-15.9333333333333, 100.38, -149.773333333334, 33.0616666666668}, //The x-coefficients
                new double[] {-296.36, 415.86, -245.373333333333, 49.3933333333334}, //The y-coefficients
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
        sleep(1000);
        robot.arm.AutoArmToBoardPosition();
        sleep(1000);
        robot.tiltMechanism.TiltMechanismStartPosition();

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
        // Laat pixel in pusher los.
        robot.pusher.releasePreLoadRight();
        //Rij een stuk naar achter zodat de pixel niet meer onder de robot ligt.
        robot.drivetrain.driveStraight(-25, 0.4);
        // Rij naar board.
        case1Board1.executeWithPointSkip();
        // Draai
        robot.drivetrain.turnRobotAO(-90);
        // Beweeg arm naar juiste positie.
        robot.arm.AutoArmToBoardPosition();
        // Rij stukje naar voren.
        robot.drivetrain.driveStraight(10, 0.4);
        // laat pikel los.
        robot.grabber.drop();
        // rijd achteruit.
        robot.drivetrain.driveStraight(-10,0.4);
        // Beweeg arm terug.
        robot.arm.MoveArmDown();
        // Draai robot.
        robot.drivetrain.turnRobotAO(0);
        // Parkeer in backstage.
        case1ParkB.executeWithPointSkip();

    }

    private void rightPixelPlacement() {
        // Push pixel naar de rechter streep.
        case2.executeWithPointSkip();
        // Laat pixel in pusher los.
        robot.pusher.releasePreLoadRight();
        // Rij een stuk naar achter zodat de pixel niet meer onder de robot ligt.
        robot.drivetrain.driveStraight(-10, 0.4);
        // Rij naar board.
        case2Board2.executeWithPointSkip();
        // Draai
        robot.drivetrain.turnRobotAO(-90);
        // Beweeg arm naar juiste positie.
        robot.arm.AutoArmToBoardPosition();
        // Rij stukje naar voren.
        robot.drivetrain.driveStraight(10, 0.4);
        // laat pikel los.
        robot.grabber.drop();
        // rijd achteruit.
        robot.drivetrain.driveStraight(-10,0.4);
        // Beweeg arm terug.
        robot.arm.MoveArmDown();
        // Draai robot.
        robot.drivetrain.turnRobotAO(0);
        // Parkeer in backstage.
        case2ParkB.executeWithPointSkip();
    }

    private void leftPixelPlacement() {
        // Push pixel naar de rechter streep.
        case0.executeWithPointSkip();
        // Laat pixel in pusher los.
        robot.pusher.releasePreLoadRight();
        // Rij een stuk naar achter zodat de pixel niet meer onder de robot ligt.
        robot.drivetrain.driveStraight(-10, 0.4);
        // Rij naar board.
        case0Board0.executeWithPointSkip();
        // Draai
        robot.drivetrain.turnRobotAO(-90);
        // Beweeg arm naar juiste positie.
        robot.arm.AutoArmToBoardPosition();
        // Rij stukje naar voren.
        robot.drivetrain.driveStraight(10, 0.4);
        // laat pikel los.
        robot.grabber.drop();
        // rijd achteruit.
        robot.drivetrain.driveStraight(-10,0.4);
        // Beweeg arm terug.
        robot.arm.MoveArmDown();
        // Draai robot.
        robot.drivetrain.turnRobotAO(0);
        // Parkeer in backstage.
        case0ParkB.executeWithPointSkip();
    }
}