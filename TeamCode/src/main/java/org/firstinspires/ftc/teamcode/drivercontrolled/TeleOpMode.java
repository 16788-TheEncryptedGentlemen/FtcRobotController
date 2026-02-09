package org.firstinspires.ftc.teamcode.drivercontrolled;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class TeleOpMode<RobotType> extends LinearOpMode {
    /**
     * The robot
     */
    public RobotType robot;

    @Override
    public void runOpMode() {
        // Initialisation
        initCode();
        waitForStart();

        while (!isStopRequested()) {
            controlLoop();
            telemetry.update();
        }
    }

    /**
     * Initialisation
     */
    public abstract void initCode();

    /**
     * Repeats program until program is stopped
     */
    public abstract void controlLoop();
}
