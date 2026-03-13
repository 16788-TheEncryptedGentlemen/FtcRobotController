package org.firstinspires.ftc.teamcode.drivercontrolled;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.acos;
import static java.lang.Math.asin;
import static java.lang.Math.hypot;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robotparts.Timer;


@TeleOp
public class JeroenTest0 extends DriverControlled {
    protected PositionTracker position;
    public double distance = 0;
    public double direction = 180;
    private final Timer speedUpTimer = new Timer();

    static final double FIELD_SIDE = 365.75; // side length in cm
    static final double RPM_PER_CM = 4.15; // power estimate

    /**
     * Initialisation
     */
    public void initCode() {
        initRobot();
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
        print("Gelukt! zet nu de robot precies midden tegen de onderkant (publiekszijde) van het veld, kijkend naar boven.\n" +
                "Druk daarna op start. Gebruik de volgende keer opMode JeroenTest" + parameters + "\n" +
                "Gebruik controller 1 zoals normaal. rijd ergens heen, niet te dicht bij objecten, en druk op A.");
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

    private void shoot() {
        robot.shooter.changeRPM((int) (distance * RPM_PER_CM));
        robot.shooter.start();
        robot.drivetrain.turnRobotAO(direction, -0.3);
        speedUpTimer.Reset();
        while (speedUpTimer.getTime() < 1.0) {
            if (isStopRequested()) return;
        }
        robot.transport.deliverBall();
        speedUpTimer.Reset();
        while (speedUpTimer.getTime() < 3.0) {
            if (isStopRequested()) return;
        }
        robot.transport.stop();
    }

    private void aim() {
        double dx = FIELD_SIDE / 2 - abs(position.right);
        double dy = FIELD_SIDE - position.forward;
        distance = hypot(dx, dy);
        if (distance == 0) {
            print("Afstand tot de goal mag geen nul zijn!");
            direction = 180;
        }
        if (abs(dx) < abs(dy)) {
            direction = asin(dx / distance) * 180 / PI;
        } else {
            direction = acos(dy / distance) * 180 / PI;
        }
        if (position.right < 0) {
            direction = -direction;
        }

        // shooter is on the back-side:
        if (direction > 0) {
            direction -= 180;
        } else {
            direction += 180;
        }
    }

    /**
     * Repeats program until program is stopped
     */
    public void controlLoop() {
        super.controlLoop();
        // Jeroens code
        trackPosition();
        aim();
        if (gamepad1.a) {
            robot.drivetrain.stop();
            shoot();
            print("You can use controller 2 as normal too, continue shooting with 'A' on gamepad 1");
        }
    }

    protected void trackPosition() {
        position.update();
        telemetry.addData("Forward", position.forward);
        telemetry.addData("Right", position.right);
        telemetry.addData("Heading", position.heading);

        // berekende waardes:
        telemetry.addData("Direction", direction);
        telemetry.addData("Distance", distance);
    }
}
