package org.firstinspires.ftc.Testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.Robots.WedstrijdRobot;

@Autonomous

public class Bob extends LinearOpMode {

    public void runOpMode() {
        WedstrijdRobot Robot = new WedstrijdRobot(this);

        waitForStart();


        Robot.Drivetrain.DriveStraight(40, 0.3);
        Robot.Drivetrain.TurnRobotAO(90);


        Robot.Odometry.Reset();

        while (Robot.Odometry.getX() <= 30) {
            Robot.Drivetrain.powerStrafeValues(90, 0.3, 90);

        }

        //Robot.Drivetrain.TurnRobotAO(90);
        // Robot.Intake.Slurp();
        // Robot.Drivetrain.DriveStraight(20, 0.3);
        // Robot.Intake.Stop();
        //Robot.Drivetrain.TurnRobotAO(0);


    }
}