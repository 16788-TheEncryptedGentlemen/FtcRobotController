package org.firstinspires.ftc.teamcode.drivercontrolled;

import static java.lang.Math.abs;
import static java.lang.Math.atan;
import static java.lang.Math.hypot;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robotparts.Timer;
import org.firstinspires.ftc.teamcode.robots.CompetitionRobot;


@TeleOp
public class JeroenTest0 extends DriverControlled {
    protected PositionTracker position;
    private final Timer speedUpTimer = new Timer();

    static final double FIELD_SIDE = 365.75; // side length in cm
    static final double RPM_PER_CM = 4.15; // power estimate

    /**
     * Initialisation
     */
    public void initCode() {
        robot = new CompetitionRobot(this);
        position = new PositionTracker(robot.odometry, robot.imu);
        telemetry.clearAll();

        // Reset autonoom
        print("Reset sensoren:");
        robot.odometry.reset();
        robot.imu.reset();
        if (abs(robot.odometry.getX()) > 0.1) {
            print("Hier gaat iets mis. X is niet 0.");
            return;
        }
        if (abs(robot.odometry.getY()) > 0.1) {
            print("Hier gaat iets mis. Y is niet 0.");
            return;
        }
        if (abs(robot.imu.getAngle()) > 3) {
            print("Hier gaat iets mis. hoek is niet 0.");
            return;
        }

        print("Duw de robot ongeveer één tegel rechtvooruit.");

        // check y richting
        while (abs(getY()) < 50) {
            if (isStopRequested()) {
                return;
            }
            if (abs(getX()) > 10) {
                print("Hier gaat iets mis. X verandert als je vooruit gaat.");
                return;
            }
        }
        if (getY() < 0) {
            print("Hier gaat iets mis. Y neemt af als je vooruit gaat.");
            return;
        }

        // check x richting
        telemetry.clear();
        print("Heel goed! Duw nu de robot één tegel naar rechts (strafen).");
        while (abs(getX()) < 50) {
            if (isStopRequested()) {
                return;
            }
            if (abs(getY() - 60) > 10) {
                print("Hier gaat iets mis. Y verandert als je naar rechts gaat.");
                return;
            }
        }
        boolean invertX = getX() < 0;

        // check rotatie
        telemetry.clear();
        print("Bijna klaar! Draai nu de robot 90 graden met de klok mee.");
        while (abs(getHeading()) < 80) {
            if (isStopRequested()) {
                return;
            }
        }
        boolean invertHeading = getHeading() < 0;
        int parameters = (invertX ? 2 : 0) + (invertHeading ? 1 : 0);
        if (parameters == 0) {
            parameters = 4;
        }

        position.setParameters(invertX, invertHeading);
        telemetry.clear();
        print("Gelukt! zet nu de robot precies midden tegen de onderkant (publiekszijde) van het veld, kijkend naar boven.");
        print("Druk daarna op start. Gebruik de volgende keer opMode JeroenTest" + parameters);
        print("Gebruik controller 1 zoals normaal. rijd ergens heen, niet te dicht bij objecten, en druk op A.");
        waitForStart();
        position.reset();
    }

    double getX() {
        return robot.odometry.getX();
    }

    double getY() {
        return robot.odometry.getY();
    }

    double getHeading() {
        return robot.imu.getAngle();
    }

    void print(String line) {
        telemetry.addLine(line);
        telemetry.update();
    }

    void shoot() {
        robot.drivetrain.stop();
        boolean leftGoal = position.right < 0;
        double dx = FIELD_SIDE / 2 - abs(position.right);
        double dy = FIELD_SIDE - position.forward;
        double distance = hypot(dx, dy);
        double direction = atan(dx / dy);
        if (leftGoal) {
            direction = -direction;
        }
        // shooter is on the back-side:
        if (direction > 0) {
            direction -= 180;
        } else {
            direction += 180;
        }

        robot.shooter.changeRPM((int) (distance * RPM_PER_CM));
        robot.shooter.start();
        robot.drivetrain.turnRobotAO(direction, -0.3);
        speedUpTimer.Reset();
        while (speedUpTimer.getTime() < 1.0) {
            if (isStopRequested()) return;
        }
        robot.transport.deliverBall();
    }

    /**
     * Repeats program until program is stopped
     */
    public void controlLoop() {
        super.controlLoop();
        // Jeroens code
        trackPosition();
        if (gamepad1.a) {
            shoot();
            print("You can use controller 2 as normal too, continue shooting with 'A' on gamepad 1");
        }
    }

    protected void trackPosition() {
        position.update();
        telemetry.addData("Forward", position.forward);
        telemetry.addData("Right", position.right);
    }
}
