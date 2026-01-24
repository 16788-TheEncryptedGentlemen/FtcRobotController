package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.robots.TriangleRobot;

//@TeleOp
public class TriangleRobotDriverControlled extends OpMode
{
    TriangleRobot triangle;

    @Override
    public void init()
    {
        triangle = new TriangleRobot(this);
    }

     public void loop()
    {
        double rightJoyX = gamepad1.right_stick_x;
        double leftJoyY = -gamepad1.left_stick_y;
        double leftJoyX = gamepad1 .left_stick_x;

        triangle.drivetrain.calculateMotorValues(leftJoyX, leftJoyY, rightJoyX);

    }

}