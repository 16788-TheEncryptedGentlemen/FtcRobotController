package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
//@Autonomous
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
                new double[] {370.848333333333, -794.674999999998, 892.266666666661, -1435.99166666666, 2793.90999999999, -2835.735, 1128.87666666667}, //The x-coefficients
                new double[] {11.1533333333331, 58.5550000000017, -362.483333333336, -780.733333333328, 2660.07, -2278.06833333333, 637.731666666667}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case1ParkB = new BezierCurveRoute(
                new double[] {370.848333333333, -794.674999999998, 892.266666666661, -1435.99166666666, 2793.90999999999, -2835.735, 1128.87666666667}, //The x-coefficients
                new double[] {11.1533333333331, 58.5550000000017, -362.483333333336, -780.733333333328, 2660.07, -2278.06833333333, 637.731666666667}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case2ParkB = new BezierCurveRoute(
                new double[] {309.505, -619.009999999999, 237.008333333326, 1017.74166666668, -1890.49000000001, 1251.96166666667, -217.091666666666}, //The x-coefficients
                new double[] {-2.78833333333387, 66.9200000000014, -153.358333333333, -1617.23333333334, 3923.185, -2986.305, 715.805000000001}, //The y-coefficients
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
        sleep(1000);



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
        //Push pixel naar de middelste streep.
        robot.drivetrain.driveStraight(65, 0.4);
        sleep(200);
        robot.pusher.release();
        sleep(200);
        //Rij een stuk naar achter zodat de pixel niet meer onder de robot ligt.
        robot.drivetrain.driveStraight(-25, 0.4);
        //Rij naar de backstage en parkeer.
        case1ParkB.executeWithPointSkip();
    }

    private void rightPixelPlacement() {
        robot.arm.AutoArmToBoardPosition();
        sleep(1000);
        robot.tiltMechanism.TiltMechanismStartPosition();
        sleep(200);
        //Push pixel naar de rechter streep.
        case2.executeWithPointSkip();
        sleep(200);
        robot.pusher.release();
        sleep(200);
        //Rij een stuk naar achter zodat de pixel niet meer onder de robot ligt.
        robot.drivetrain.driveStraight(-15, 0.4);
        //Rij naar de backstage en parkeer.
        case2ParkB.executeWithPointSkip();
    }

    private void leftPixelPlacement() {
        robot.arm.AutoArmToBoardPosition();
        sleep(1000);
        robot.tiltMechanism.TiltMechanismStartPosition();
        sleep(200);
        robot.drivetrain.driveStraight(50, 0.4);
        robot.drivetrain.turnRobotAO(-45);
        robot.drivetrain.driveStraight(20, 0.4);
        sleep(200);
        robot.pusher.release();
        sleep(200);
        robot.drivetrain.driveStraight(-20, 0.4);
        robot.drivetrain.turnRobotAO(0);
        //Rij naar de backstage en parkeer.
        case0ParkB.executeWithPointSkip();
    }
}