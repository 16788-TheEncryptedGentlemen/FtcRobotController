package org.firstinspires.ftc.teamcode.robots;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.robotparts.TriangleDrivetrain;
import org.firstinspires.ftc.teamcode.robotparts.TriangleDrivetrainSnel;

public class TriangleRobot {
    public TriangleDrivetrain drivetrain;
    public TriangleDrivetrainSnel drivetrainsnel;

    public TriangleRobot(OpMode runningOpMode) {
        drivetrain = new TriangleDrivetrain(runningOpMode);
        drivetrainsnel = new TriangleDrivetrainSnel(runningOpMode);
    }

 }