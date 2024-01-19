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
                new double[] {-211.913333333333, 451.709999999998, -557.66666666666, 1672.99999999999, -4215.95999999999, 4084.90833333333, -960.779999999998}, //The x-coefficients
                new double[] {-22.3066666666668, -25.0949999999984, 334.599999999993, 2342.20000000001, -6591.62000000001, 5557.14833333334, -1523.625}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case1ParkB = new BezierCurveRoute(
                new double[] {-577.185, 2082.885, -4433.45, 6315.575, -5939.14999999999, 3039.28333333333, -251.348333333334}, //The x-coefficients
                new double[] {58.5550000000002, -777.944999999999, 4461.33333333333, -8922.66666666666, 8390.09499999999, -3847.89999999999, 701.464999999999}, //The y-coefficients
                robot,
                0.4,
                BezierCurveRoute.DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        case2ParkB = new BezierCurveRoute(
                new double[] {-607.856666666667, 1790.11, -2147.01666666667, 613.433333333332, 334.600000000003, 75.2849999999969, 158.935000000001}, //The x-coefficients
                new double[] {33.46, -334.6, 1812.41666666667, -1101.39166666667, -3145.24, 4399.99, -1596.91833333333}, //The y-coefficients
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