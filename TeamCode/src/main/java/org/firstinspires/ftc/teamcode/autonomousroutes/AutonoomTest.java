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
        int output = 1;
        int output2 = 1;

        while (!isStarted() && !isStopRequested())
        {
            // debugging


            output = robot.webcam.pipeline.getBluePosition();

            output2 = robot.webcam.pipeline.getRedPosition();
            // result
//            result = robot.webcam.getResult();
            telemetry.addData("bluePosition", output);
            telemetry.addData("redPosition", output2);
            telemetry.update();

        }

        /*
         * The START command just came in: snapshot the current analysis now
         * for later use. We must do this because the analysis will continue
         * to change as the camera view changes once the robot starts moving!
         */


        /** Initialisation. */
        waitForStart();


        switch(output) {
            case 0: // LEFT
                robot.drivetrain.turnRobotAO(-20);
                sleep(500);
                robot.drivetrain.driveStraight(20, 0.4);
                break;
            case 2: // RIGHT
                robot.drivetrain.turnRobotAO(20);
                sleep(500);
                robot.drivetrain.driveStraight(20, 0.4);
                break;
            default: // Default MIDDLE
                robot.drivetrain.driveStraight(20, 0.4);
        }



    }
}