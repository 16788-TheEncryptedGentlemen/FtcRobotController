package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class AutonoomTest extends LinearOpMode {

    @Override
    public void runOpMode() {

        //Variables
        CompetitionRobot robot = new CompetitionRobot(this);
//        robot.webcam.pipeline.cone = PipelineRedMarkerDetection.ConeColour.RED;
        double output;

        while (!isStarted() && !isStopRequested())
        {
            // debugging


            output = robot.webcam.getResult();
            // result
//            result = robot.webcam.getResult();
            telemetry.addData("eenOfAnderGetal", output);
            telemetry.update();

        }

        /*
         * The START command just came in: snapshot the current analysis now
         * for later use. We must do this because the analysis will continue
         * to change as the camera view changes once the robot starts moving!
         */


        /** Initialisation. */
        waitForStart();



    }
}