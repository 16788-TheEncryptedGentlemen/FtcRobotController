package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.CompetitionRobot;
import org.firstinspires.ftc.teamcode.webcamgarbage.PipelineColourDetection;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class RedStart1HighParkVision extends LinearOpMode {

    int[] output;
    int result;

    @Override
    public void runOpMode() {

        //Variables
        CompetitionRobot robot = new CompetitionRobot(this);
        robot.webcam.pipeline.cone = PipelineColourDetection.ConeColour.RED;


        while (!isStarted() && !isStopRequested())
        {
            // debugging
            output = robot.webcam.getAnalysis();
            telemetry.addData("R", output[0]);
            telemetry.addData("Y", output[1]);
            telemetry.addData("B", output[2]);

            // result
            result = robot.webcam.getResult();
            telemetry.addData("Result", result);
            telemetry.update();

        }

        /*
         * The START command just came in: snapshot the current analysis now
         * for later use. We must do this because the analysis will continue
         * to change as the camera view changes once the robot starts moving!
         */

        /*
         * Show that snapshot on the telemetry
         */
        telemetry.addData("Final result", result);
        telemetry.update();

        robot.imu.resetAngularOrientation();

        //Autonomous routes:
        // Add bezier curves!

        /** Initialisation. */
        waitForStart();


        robot.drivetrain.turnRobotAO(90);

    }
}