package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute.DRIVE_METHOD;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
//@Autonomous
public class RedStart2SDParkB extends LinearOpMode {
    public void runOpMode() {

        CompetitionRobot robot = new CompetitionRobot(this);

        BezierCurveRoute RedStart2SDParkB = new BezierCurveRoute(
                new double[] {5.97500000000011, -80.0650000000001, 192.793333333333}, //The x-coefficients
                new double[] {402.715, -434.98, 156.146666666667}, //The y-coefficients
                robot,
                0.4,
                DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                this
        );




        // Initialisation.
        robot.tiltMechanism.TiltMechanismStartPosition();
        waitForStart();

        RedStart2SDParkB.executeWithPointSkip();

        sleep(5000);
    }
}