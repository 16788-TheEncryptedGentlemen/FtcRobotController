package org.firstinspires.ftc.teamcode.drivercontrolled;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;


@TeleOp
public class JeroenTest3 extends JeroenTest0 {
    /**
     * Initialisation
     */
    @Override
    public void initCode() {
        robot = new CompetitionRobot(this);
        position = new PositionTracker(robot.odometry, robot.imu);
        position.setParameters(true, true);
        telemetry.clear();
        print("Gebruik controller 1 zoals normaal. rijd ergens heen, niet te dicht bij objecten, en druk op A.");
    }
}
