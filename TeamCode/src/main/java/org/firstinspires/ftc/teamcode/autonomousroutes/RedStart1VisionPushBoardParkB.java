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
    private BezierCurveRoute case2;
    private BezierCurveRoute case0ParkB;
    private BezierCurveRoute case1ParkB;
    private BezierCurveRoute case2ParkB;
    private CompetitionRobot robot;

    private void initAutonomous() {
        robot = new CompetitionRobot(this);

        case0 = new BezierCurveRoute(
                new double[] {-134.835833333334, 221.373750000001, -150.769166666667, 36.0989583333336}, //The x-coefficients
                new double[] {-5.57666666666569, 65.7249999999985, 155.350000000001, -151.1675}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case2 = new BezierCurveRoute(
                new double[] {6.37333333333339, -11.9500000000003, -27.0866666666663, 52.9783333333332}, //The x-coefficients
                new double[] {195.98, -169.69, 7.96666666666653, 29.4766666666668}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case0ParkB= new BezierCurveRoute(
                new double[] {-12.1989583333324, -1351.993125, 7146.846875, -15288.7802083333, 15483.615, -7288.35479166666, 1574.16354166666}, //The x-coefficients
                new double[] {-408.490833333334, 2329.6525, -6601.37916666666, 14366.8875, -18804.52, 12077.6658333333, -2888.51416666666}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case1ParkB = new BezierCurveRoute(
                new double[] {-525.252291666667, 2972.711875, -8830.303125, 15647.778125, -17231.9, 10713.1252083333, -2509.54979166667}, //The x-coefficients
                new double[] {73.8908333333343, -798.857500000003, 4133.70416666667, -7758.53750000001, 6842.57000000001, -2920.77916666668, 490.945833333336}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case2ParkB = new BezierCurveRoute(
                new double[] {-525.252291666667, 2972.711875, -8830.303125, 15647.778125, -17231.9, 10713.1252083333, -2509.54979166667}, //The x-coefficients
                new double[] {73.8908333333343, -798.857500000003, 4133.70416666667, -7758.53750000001, 6842.57000000001, -2920.77916666668, 490.945833333336}, //The y-coefficients
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

        // Jeroen denkt dat dit niet nodig is.
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
        robot.drivetrain.turnRobotAO(90);
        sleep(100);
        robot.drivetrain.driveStraight(-30,0.3);
        sleep(200);
        robot.drivetrain.strafeStraight(-70, 0.3,90);
        sleep(200);
        robot.arm.ArmToStageDoorPosition();
        robot.drivetrain.turnRobotAO(85);
        robot.drivetrain.driveStraight(220, 0.3);
        sleep(200);
        robot.drivetrain.strafeStraight(60, 0.3, 90);
        sleep(200);
        robot.arm.AutoArmToBoardPosition();
        sleep(200);
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
        robot.drivetrain.driveStraight(62, 0.3);
        sleep(200);
        robot.drivetrain.turnRobotAO(90);
        sleep(200);
        robot.drivetrain.driveStraight(5,0.3);
        sleep(200);
        robot.pusher.release();
        sleep(200);
        robot.drivetrain.driveStraight(-20,0.3);
        sleep(200);
        robot.arm.ArmToStageDoorPosition();
        robot.drivetrain.strafeStraight(-50,0.3,90);
        sleep(200);
        robot.drivetrain.turnRobotAO(85);
        robot.drivetrain.driveStraight(180, 0.3);
        sleep(200);
        robot.arm.AutoArmToBoardPosition();
        robot.drivetrain.strafeStraight(80,0.3,90);
        sleep(200);
        robot.drivetrain.driveStraight(35,0.3);
        sleep(200);
        robot.grabber.drop();
        sleep(200);
        robot.drivetrain.driveStraight(-10, 0.3);
    }

    private void leftPixelPlacement() {
        robot.arm.AutoArmToBoardPosition();
        sleep(1000);
        robot.tiltMechanism.TiltMechanismStartPosition();
        sleep(200);
        robot.pusher.grab();
        sleep(200);
        robot.drivetrain.driveStraight(10, 0.3);
        sleep(200);
        robot.drivetrain.strafeStraight(-25, 0.3, 0);
        sleep(200);
        robot.drivetrain.driveStraight(50, 0.3);
        sleep(200);
        robot.pusher.release();
        sleep(200);
        robot.drivetrain.driveStraight(-20,0.3);
        sleep(200);
        robot.drivetrain.strafeStraight(30,0.3,0);
        sleep(200);
        robot.arm.ArmToStageDoorPosition();
        robot.drivetrain.driveStraight(75,0.3);
        robot.drivetrain.turnRobotAO(85);
        sleep(200);
        robot.drivetrain.driveStraight(180,0.3);
        sleep(200);
        robot.arm.AutoArmToBoardPosition();
        sleep(200);
        robot.drivetrain.strafeStraight(35,0.3,90);
        sleep(200);
        robot.drivetrain.driveStraight(23,0.3);
        sleep(200);
        robot.grabber.drop();
        sleep(100);
        robot.drivetrain.driveStraight(-10,0.3);
    }
}