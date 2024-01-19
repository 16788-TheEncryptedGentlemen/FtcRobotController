package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class BlueStart2VisionPushParkB extends LinearOpMode {
    private final boolean BLUE_SIDE = true;
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

        case0ParkB= new BezierCurveRoute(
                new double[] {-9.55999999999995, 71.6999999999998, -144.993333333333, -10.3566666666668}, //The x-coefficients
                new double[] {-165.706666666667, 229.44, -186.419999999999, 73.6916666666663}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case1ParkB = new BezierCurveRoute(
                new double[] {-4.78000000000006, 38.2400000000003, -219.880000000001, 66.5216666666669}, //The x-coefficients
                new double[] {-148.18, -11.9500000000003, 237.406666666667, -137.026666666667}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case2ParkB = new BezierCurveRoute(
                new double[] {-4.78000000000006, 38.2400000000003, -219.880000000001, 66.5216666666669}, //The x-coefficients
                new double[] {-148.18, -11.9500000000003, 237.406666666667, -137.026666666667}, //The y-coefficients
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
        //Push pixel naar de middelste streep.
        robot.drivetrain.driveStraight(70, 0.4);
        //Rij een stuk naar achter zodat de pixel niet meer onder de robot ligt.
        robot.drivetrain.driveStraight(-25, 0.4);
        //Rij naar de backstage en parkeer.
        case1ParkB.executeWithPointSkip();
    }

    private void rightPixelPlacement() {
        robot.drivetrain.driveStraight(70, 0.4);
        robot.drivetrain.turnRobotAO(45);
        robot.drivetrain.driveStraight(20, 0.4);
        robot.drivetrain.driveStraight(-20, 0.4);
        robot.drivetrain.turnRobotAO(0);
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