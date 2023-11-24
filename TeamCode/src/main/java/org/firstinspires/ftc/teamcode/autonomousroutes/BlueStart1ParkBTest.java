package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.teamcode.autonomousclasses.BezierCurveRoute.DRIVE_METHOD;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class BlueStart1ParkBTest extends LinearOpMode {
    public void runOpMode() {

        CompetitionRobot robot = new CompetitionRobot(this);


            BezierCurveRoute Test = new BezierCurveRoute(
                    new double[] {5.97499999999985, -83.6499999999998, -107.151666666667}, //The x-coefficients
                    new double[] {229.44, -229.44, 18.7216666666667}, //The y-coefficients
                    robot,
                    0.5,
                    DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW
                    this
            );


        // Initialisation.
        waitForStart();

        Test.executeWithPointSkip();

        sleep(5000);
    }
}