package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class BlueStart2SDParkB extends LinearOpMode {
    public void runOpMode() {

        CompetitionRobot robot = new CompetitionRobot(this);

        BezierCurveRoute BlueStart2SDParkB = new BezierCurveRoute(
                new double[] {-9.55999999999996, 38.2399999999997, 17.5266666666672, -164.511666666667}, //The x-coefficients
                new double[] {528.986666666668, -829.330000000002, 591.126666666668, -165.706666666667}, //The y-coefficients
                robot,
                0.4,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );





        // Initialisation.
        robot.tiltMechanism.TiltMechanismStartPosition();
        waitForStart();

        BlueStart2SDParkB.executeWithPointSkip();

        sleep(5000);
    }
}