/*package org.firstinspires.ftc.AutonomousRoute;

import static org.firstinspires.ftc.RobotParts.Webcam.Webcam.LEFT;
import static org.firstinspires.ftc.RobotParts.Webcam.Webcam.MIDDLE;
import static org.firstinspires.ftc.RobotParts.Webcam.Webcam.RIGHT;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.Robots.WedstrijdRobot;

//Uncomment @Autonomous to show up on the DC controller app
@Autonomous
public class BlueStart1BlockDuckWarehouse extends LinearOpMode {
    public void runOpMode() {
        //Variables------------------------------------
        WedstrijdRobot Robot = new WedstrijdRobot(this);

        BezierCurveRoute BlueStart1Block = new BezierCurveRoute(
                new double[]{17.9250000000009, -26.8875000000027, 68.712500000002}, //The x-coefficients
                new double[]{225.855, -231.2325, 75.8825}, //The y-coefficients
                Robot,
                0.6,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        BezierCurveRoute BlueBlockDuck = new BezierCurveRoute(
                new double[]{-510.862500000005, 2151.00000000004, -5420.52000000018, 8958.9150000004, -3839.53500000058, -12145.9799999995, 19208.4299999997, -9770.91749999991, 1337.80249999999}, //The x-coefficients
                new double[]{26.8875000000006, -43.0200000000024, -10891.23, 50892.66, -103968.585, 116641.56, -75973.3200000001, 27737.145, -4551.1575}, //The y-coefficients
                Robot,
                0.6,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );

        //Initialisation-----------------------------------
        waitForStart();

        //Run program------------------------------------
        //Run program------------------------------------
        int duckPosition = Robot.webcam.getPositionOfDuck();
        BlueStart1Block.ExecuteWithPointSkip();
        if (duckPosition == LEFT) {
       //     Robot.grabber.moveToShippingHubBottom();
        } else if (duckPosition == MIDDLE) {
       //     Robot.grabber.moveToShippingHubMiddle();
        } else if (duckPosition == RIGHT) {
       //     Robot.grabber.moveToShippingHubTop();
        } else {
            //doe die ene die het slechtst zichtbaar is
        }

//            rijd misschien een stukje naar voren
       // Robot.grabber.drop();
       // Robot.grabber.moveToBasePosition();

        BlueBlockDuck.ExecuteWithPointSkip();
        Robot.DuckWheel.Turn();
        sleep(2500);
        Robot.DuckWheel.Stop();
        Robot.Drivetrain.DriveStraight(120.0, 0.4);
        Robot.Drivetrain.powerStrafeValues(-90.0, 0.3);
        sleep(3000);
        Robot.Drivetrain.DriveStraight(90.0, 0.2);

    }

}
*/