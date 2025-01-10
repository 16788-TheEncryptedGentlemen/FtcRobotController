package org.firstinspires.ftc.teamcode.autonomousroutes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;

/** Comment to make the program disappear from the driverstation app. */
@Autonomous
public class TestMechanumDrivetrainRoute extends LinearOpMode {
    CompetitionRobot robot;

    final double POWER = 0.2;

    public void runOpMode() {

        // Initialisation.
        robot = new CompetitionRobot(this);
        waitForStart();

        // Rijd in een vierkant
        rijdInEenVierkant(50);
    }

    /** Rijd in een vierkant. Vooruit en rechts.
     * @param zijde de lengte van een zijde in cm.
     */
    public void rijdInEenVierkant(double zijde) {
        // doe vier keer:
        for (int i = 0; i < 4; i++) {
            // rijd naar voren
            robot.drivetrain.driveStraight(zijde, POWER);
            // draai 90 graden
            robot.drivetrain.turnRobotAO(90*(i+1), POWER);
        }   }
}