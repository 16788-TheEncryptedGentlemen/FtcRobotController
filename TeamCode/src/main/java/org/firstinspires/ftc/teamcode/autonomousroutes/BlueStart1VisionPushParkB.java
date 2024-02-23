package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.robotparts.Arm;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
//@Autonomous
public class BlueStart1VisionPushParkB extends LinearOpMode {
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

        case2 = new BezierCurveRoute(
                new double[] {121.093333333333, -167.3, 108.346666666667, -33.0616666666667}, //The x-coefficients
                new double[] {1.59333333333325, -14.3399999999995, 17.5266666666658, 59.3516666666669}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case0ParkB= new BezierCurveRoute(
                new double[] {398.731666666666, -1179.465, 2105.19166666667, -3764.25000000001, 6499.60500000001, -6089.72, 1788.51666666667}, //The x-coefficients
                new double[] {11.1533333333333, -150.569999999999, 487.958333333328, 1798.47500000001, -5203.03000000001, 4349.8, -1221.29}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case1ParkB = new BezierCurveRoute(
                new double[] {398.731666666666, -1179.465, 2105.19166666667, -3764.25000000001, 6499.60500000001, -6089.72, 1788.51666666667}, //The x-coefficients
                new double[] {11.1533333333333, -150.569999999999, 487.958333333328, 1798.47500000001, -5203.03000000001, 4349.8, -1221.29}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case2ParkB = new BezierCurveRoute(
                new double[] {211.913333333333, -619.009999999999, 1171.1, -2830.15833333334, 5939.15000000001, -5902.90166666667, 1761.82833333333}, //The x-coefficients
                new double[] {13.9416666666666, -158.934999999999, 501.899999999994, 1784.53333333334, -5194.66500000001, 4347.01166666667, -1220.89166666667}, //The y-coefficients
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
        robot.drivetrain.driveStraight(70, 0.4);
        //Rij een stuk naar achter zodat de pixel niet meer onder de robot ligt.
        robot.drivetrain.driveStraight(-25, 0.4);
        //Rij naar de backstage en parkeer.
        //case1ParkB.executeWithPointSkip();
    }

    private void rightPixelPlacement() {
        robot.arm.AutoArmToBoardPosition();
        sleep(1000);
        robot.tiltMechanism.TiltMechanismStartPosition();
        sleep(200);
        //Push pixel naar de rechter streep.
        case2.executeWithPointSkip();
        //Rij een stuk naar achter zodat de pixel niet meer onder de robot ligt.
        robot.drivetrain.driveStraight(-10, 0.4);
        //Rij naar de backstage en parkeer.
       // case2ParkB.executeWithPointSkip();
    }

    private void leftPixelPlacement() {
        robot.arm.AutoArmToBoardPosition();
        sleep(1000);
        robot.tiltMechanism.TiltMechanismStartPosition();
        sleep(200);
        robot.drivetrain.driveStraight(70, 0.4);
        robot.drivetrain.turnRobotAO(-45);
        robot.drivetrain.driveStraight(20, 0.4);
        robot.drivetrain.driveStraight(-20, 0.4);
        //robot.drivetrain.turnRobotAO(0);
        //Rij naar de backstage en parkeer.
        //case0ParkB.executeWithPointSkip();
    }
}