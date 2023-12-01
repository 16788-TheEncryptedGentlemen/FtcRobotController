package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class BlueStart1SDParkB extends LinearOpMode {
    public void runOpMode() {

        CompetitionRobot robot = new CompetitionRobot(this);

        BezierCurveRoute BlueStart1SDParkB = new BezierCurveRoute(
                new double[] {90.8199999999991, -157.739999999999, 39.8333333333326, -213.108333333333}, //The x-coefficients
                new double[] {521.02, -683.539999999999, 366.466666666666, -73.6916666666666}, //The y-coefficients
                robot,
                0.4,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );





        // Initialisation.
        robot.tiltMechanism.TiltMechanismStartPosition();
        waitForStart();

        BlueStart1SDParkB.executeWithPointSkip();

        sleep(5000);
    }
}