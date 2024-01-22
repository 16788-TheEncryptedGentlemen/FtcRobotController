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
                new double[] {-17.5266666666667, 102.77, -172.08, -8.76333333333321}, //The x-coefficients
                new double[] {-36.6466666666653, -69.3100000000019, 119.500000000001, -42.621666666667}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case1ParkB = new BezierCurveRoute(
                new double[] {-6.37333333333346, 90.8200000000003, -321.853333333334, 112.33}, //The x-coefficients
                new double[] {-39.8333333333335, -164.91, 309.106666666666, -140.611666666666}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case2ParkB = new BezierCurveRoute(
                new double[] {-6.37333333333346, 90.8200000000003, -321.853333333334, 112.33}, //The x-coefficients
                new double[] {-39.8333333333335, -164.91, 309.106666666666, -140.611666666666}, //The y-coefficients
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
        robot.drivetrain.driveStraight(50, 0.4);
        robot.drivetrain.turnRobotAO(45);
        robot.drivetrain.driveStraight(20, 0.4);
        sleep(200);
        robot.pusher.release();
        sleep(200);
        robot.drivetrain.driveStraight(-20, 0.4);
        robot.drivetrain.turnRobotAO(0);
        //Rij naar de backstage en parkeer.
        case2ParkB.executeWithPointSkip();
    }

    private void leftPixelPlacement() {
        robot.arm.AutoArmToBoardPosition();
        sleep(1000);
        robot.tiltMechanism.TiltMechanismStartPosition();
        sleep(200);
        //Push pixel naar de linker streep.
        case0.executeWithPointSkip();
        sleep(200);
        robot.pusher.release();
        sleep(200);
        //Rij een stuk naar achter zodat de pixel niet meer onder de robot ligt.
        robot.drivetrain.driveStraight(-10, 0.4);
        //Rij naar de backstage en parkeer.
        case0ParkB.executeWithPointSkip();
    }
}