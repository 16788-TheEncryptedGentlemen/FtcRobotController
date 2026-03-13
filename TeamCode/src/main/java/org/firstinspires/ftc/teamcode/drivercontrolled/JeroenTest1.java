package org.firstinspires.ftc.teamcode.drivercontrolled;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp
public class JeroenTest1 extends JeroenTest0 {
    /**
     * Initialisation
     */
    @Override
    public void initCode() {
        initRobot();
        position = new PositionTracker(robot.odometry, robot.imu);
        position.setParameters(false, true);
        telemetry.clear();
        print("Gebruik controller 1 zoals normaal. rijd ergens heen, niet te dicht bij objecten, en druk op A.");
    }
}
