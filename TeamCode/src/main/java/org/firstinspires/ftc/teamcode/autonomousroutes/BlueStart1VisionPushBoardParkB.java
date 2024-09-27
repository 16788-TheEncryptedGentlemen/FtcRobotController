package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class BlueStart1VisionPushBoardParkB extends LinearOpMode {
    private final boolean BLUE_SIDE = false;
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

        case0Board0 = new BezierCurveRoute(
                new double[] {-14.3399999999999, -83.6499999999998, 549.699999999999, 734.925000000003, -2251.38, 1151.98}, //The x-coefficients
                new double[] {-179.250000000004, 495.925000000015, -231.033333333363, -1021.72499999997, 1438.77999999998, -486.364999999997}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case1Board1 = new BezierCurveRoute(
                new double[] {356.11, -770.775, 677.166666666666, -221.074999999998, 21.509999999999, 19.1200000000002}, //The x-coefficients
                new double[] {-45.4100000000017, 35.8500000000047, 438.16666666666, -794.674999999996, 418.249999999999, -41.0283333333338}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case2Board2 = new BezierCurveRoute(
                new double[] {39.0366666666667}, //The x-coefficients
                new double[] {1.19499999999998}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case0ParkB = new BezierCurveRoute(
                new double[] {4.78000000000014, -17.9250000000003, 48.1983333333335}, //The x-coefficients
                new double[] {-231.83, 253.34, -90.4216666666666}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case1ParkB = new BezierCurveRoute(
                new double[] {20.7133333333343, -66.9200000000016, 41.4266666666678, 40.2316666666664}, //The x-coefficients
                new double[] {-184.826666666666, 236.61, -184.826666666666, 70.1066666666666}, //The y-coefficients  y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case2ParkB = new BezierCurveRoute(
                new double[] {-7.17000000000007, 3.58500000000015, 35.0533333333333}, //The x-coefficients
                new double[] {-90.8199999999999, 71.7, -12.7466666666667}, //The y-coefficients
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

        robot.grabber.grab();

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
        robot.arm.AutoArmToBoardPosition();
        sleep(1000);
        robot.tiltMechanism.TiltMechanismStartPosition();
        sleep(200);
        robot.pusher.grab();
        sleep(200);
        robot.drivetrain.driveStraight(70, 0.3);
        sleep(200);
        robot.pusher.release();
        sleep(200);
        robot.drivetrain.driveStraight(-20, 0.3);
        sleep(100);
        robot.drivetrain.turnRobotAO(-90);
        sleep(200);
        robot.drivetrain.driveStraight(-30,0.3);
        sleep(200);
        robot.drivetrain.strafeStraight(70, 0.3,-90);
        sleep(200);
        robot.arm.ArmToStageDoorPosition();
        robot.drivetrain.turnRobotAO(-95);
        robot.drivetrain.driveStraight(220, 0.3);
        sleep(200);
        robot.drivetrain.strafeStraight(-60, 0.3, -90);
        sleep(200);
        robot.arm.AutoArmToBoardPosition();
        sleep(150);
        robot.drivetrain.driveStraight(23, 0.3);
        robot.grabber.drop();
        sleep(100);
        robot.drivetrain.driveStraight(-10,0.3);
    }

    private void rightPixelPlacement() {
        robot.arm.AutoArmToBoardPosition();
        sleep(1000);
        robot.tiltMechanism.TiltMechanismStartPosition();
        sleep(200);
        robot.pusher.grab();
        sleep(200);
        robot.drivetrain.driveStraight(10, 0.3);
        sleep(200);
        robot.drivetrain.strafeStraight(25, 0.3, 0);
        sleep(200);
        robot.drivetrain.driveStraight(50, 0.3);
        sleep(200);
        robot.pusher.release();
        sleep(200);
        robot.drivetrain.driveStraight(-20,0.3);
        sleep(200);
        robot.drivetrain.strafeStraight(-30,0.3,0);
        sleep(200);
        robot.arm.ArmToStageDoorPosition();
        robot.drivetrain.driveStraight(75,0.3);
        robot.drivetrain.turnRobotAO(-95);
        sleep(200);
        robot.drivetrain.driveStraight(180,0.3);
        sleep(200);
        robot.arm.AutoArmToBoardPosition();
        sleep(200);
        robot.drivetrain.strafeStraight(-35,0.3,-90);
        sleep(200);
        robot.drivetrain.driveStraight(25,0.3);
        sleep(200);
        robot.grabber.drop();
        sleep(100);
        robot.drivetrain.driveStraight(-10,0.3);
    }

    private void leftPixelPlacement() {
        robot.arm.AutoArmToBoardPosition();
        sleep(1000);
        robot.tiltMechanism.TiltMechanismStartPosition();
        sleep(200);
        robot.pusher.grab();
        sleep(200);
        robot.drivetrain.driveStraight(62, 0.3);
        sleep(200);
        robot.drivetrain.turnRobotAO(-90);
        sleep(200);
        robot.drivetrain.driveStraight(5,0.3);
        sleep(200);
        robot.pusher.release();
        sleep(200);
        robot.drivetrain.driveStraight(-20,0.3);
        sleep(200);
        robot.arm.ArmToStageDoorPosition();
        robot.drivetrain.strafeStraight(50,0.3,-90);
        sleep(200);
        robot.drivetrain.turnRobotAO(-95);
        sleep(200);
        robot.drivetrain.driveStraight(200, 0.3);
        sleep(200);
        robot.arm.AutoArmToBoardPosition();
        robot.drivetrain.strafeStraight(-70,0.3,-90);
        sleep(200);
        robot.drivetrain.driveStraight(20,0.3);
        sleep(200);
        robot.grabber.drop();
        sleep(200);
        robot.drivetrain.driveStraight(-10, 0.3);
    }
}