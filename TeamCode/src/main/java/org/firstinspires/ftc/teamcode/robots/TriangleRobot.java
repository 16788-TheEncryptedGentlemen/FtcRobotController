package org.firstinspires.ftc.teamcode.robots;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.robotparts.TriangleDrivetrain;

public class TriangleRobot {
    public TriangleDrivetrain drivetrain;

    public TriangleRobot(OpMode runningOpMode) {
        drivetrain = new TriangleDrivetrain(runningOpMode);
    }

 }