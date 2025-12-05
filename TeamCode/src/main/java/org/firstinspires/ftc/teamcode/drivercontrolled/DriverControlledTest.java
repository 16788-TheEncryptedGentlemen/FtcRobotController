package org.firstinspires.ftc.teamcode.drivercontrolled;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.robotparts.Timer;
import org.firstinspires.ftc.teamcode.robots.DrivetrainOnly;
import org.firstinspires.ftc.teamcode.robots.DrivetrainTest;


@TeleOp
public class DriverControlledTest extends OpMode {

    /**
     * The robot
     */
    DrivetrainOnly robot;

    /**
     * The desired heading when strafing.
     */
    private double desiredHeading = 0;
    private Timer antiJerkTimer;
    public final double DefaultPosition = 0.8;

    @Override
    /** Initialisation */
    public void init() {
        robot = new DrivetrainOnly(this);
        antiJerkTimer = new Timer();
    }

    @Override
    /** Repeats program until program is stopped */
    public void loop() {
        controlDrivetrain();
        telemetry.addData("X", robot.odometry.getX());
        telemetry.addData("Y", robot.odometry.getY());
//        controlShooter();
//        BallDelivery();
//        Intake();


    }

    private void controlDrivetrain() {
        // If start is pressed then the current heading of the imu is set to 0.
        if (gamepad1.start) {
            robot.imu.reset();
        }


        // The up and down movements of the left joystick on the gamepad of player 1.
        // There is a minus because up is negative and down is positive on the controller.
        double leftJoyY = -gamepad1.left_stick_y;
        // The left and right movements of the left joystick on the gamepad of player 1.
        double leftJoyX = gamepad1.left_stick_x;

        // Show distance (x,y) of the robot on the driver hub for debugging.
        //  telemetry.addData("X", robot.odometry.getX());
        //   telemetry.addData("Y", robot.odometry.getY());
        telemetry.addData("IMU", robot.imu.getAngle());

        // The speed of strafing (between 0 and 1).
        double strafeSpeed = Math.hypot(leftJoyX, leftJoyY);

        // The control stick is not perfect and it can have a radius bigger than 1.
        if (strafeSpeed > 1) {
            strafeSpeed = 1;
        }

        // The direction in which the robot should strafe in <-180, 180].
        double strafeAngle = 0;
        if (strafeSpeed != 0 && leftJoyX != 0) {
            // If the robot is moving towards the side, then calculate the direction.
            strafeAngle = Math.signum(leftJoyX) * Math.toDegrees(Math.acos(leftJoyY / strafeSpeed));
        } else if (leftJoyY < 0) {
            // If the robot is moving backwards, then the direction is 180 degrees.
            strafeAngle = 180;
        } else {
            // If the robot is moving forward or not strafing then the direction is 0 degrees.
            strafeAngle = 0;
        }
        // Set strafe values of the drivetrain.
        robot.drivetrain.setStrafeValues(strafeAngle, strafeSpeed);

        // The speed of turning between -1 (left) and 1 (right).
        double turnSpeed = gamepad1.right_stick_x;
        // Two of the speeds are reversed because they
        robot.drivetrain.addSpeed(-turnSpeed, -turnSpeed, turnSpeed, turnSpeed);

        // Correct the strafing angle when strafing and not rotating.
        if (strafeSpeed > 0 && turnSpeed == 0) {
            correctHeading();
        } else {
           // Otherwise, keep the timer at 0.
           antiJerkTimer.Reset();
        }

        robot.drivetrain.fixMotorSpeedOverflow();

        if (gamepad1.right_bumper) robot.drivetrain.multiplySpeed(0.7);
        else if (gamepad1.left_bumper) robot.drivetrain.multiplySpeed(0.2);
        else robot.drivetrain.multiplySpeed(0.5);

        robot.drivetrain.setPower();
    }

    public class ShooterV extends LinearOpMode {
        DcMotorEx MotorL;
        DcMotorEx MotorR;


        @Override
        public void runOpMode() throws InterruptedException {

            MotorL = hardwareMap.get(DcMotorEx.class, "Motor");
            MotorR = hardwareMap.get(DcMotorEx.class, "Motor");

            // Reset the encoder during initialization
            MotorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            MotorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            waitForStart();

            // Switch to RUN_TO_POSITION mode
            MotorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            MotorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Start the MotorL moving by setting the max velocity to 200 ticks per second
            MotorL.setVelocity(200);
            MotorR.setVelocity(200);

            // While the Op Mode is running, show the MotorL's status via telemetry
            while (opModeIsActive()) {
                telemetry.addData("velocity", MotorL.getVelocity());
                telemetry.addData("position", MotorL.getCurrentPosition());
                telemetry.addData("is at target", !MotorL.isBusy());
                telemetry.update();
            }


        }
    }

   private void correctHeading() {
        // The measured robot angle, from the IMU.
        double robotAngle = robot.imu.getAngle();
       telemetry.addData("robot angle", robotAngle);
       telemetry.addData("desired heading", desiredHeading);

        // Only update the correction angle during the first 0.5s of strafing.
        if (antiJerkTimer.getTime() < 0.1) {
            desiredHeading = robotAngle;
            // This also means deviationAngle will be 0, so we can skip the rest.
            return;
        }

        // Compute the deviation from the desired angle.
        double deviationAngle = robotAngle - desiredHeading;
        telemetry.addData("DeviationAngle", deviationAngle);

        // Do not correct the angle if there is a big jump in angle.
        if (Math.abs(deviationAngle) >= 90) {
            return;
        }

        // Correct small angles proportional to the angle, capped at +/-1.
        double correctionFactor = 0;
        if (deviationAngle > -30 && deviationAngle < 30) {
            correctionFactor = deviationAngle / 30;
        } else {
            correctionFactor = Math.signum(deviationAngle);
        }


        robot.drivetrain.addSpeed(-correctionFactor, -correctionFactor, correctionFactor, correctionFactor);
        telemetry.addData("GyroCorrectionFactor", correctionFactor);
    }


    //:todo makeshure it works with the shooter after the bois reassembled the robot
    /*public void runOpMode() throw InterruptedException
{
    ElapsedTime mStateTime = new ElapsedTime();
    int v_state = 0;
    ...
    waitForStart();
    while (opModeIsActive())
    {
        switch (v_state)
        {
            case 0:
                // set the arm servo to the up position.
                servo.setPosition(UP_POSITION);
                // The servo needs 2 seconds to be in the UP position.
                // So set up the timer and move to the next state.
                mStateTime.reset();
                v_state++;
                break;
            case 1:
                // check if 2 seconds has past and move on
                if (mStateTime.time() >= 2.0)
                {
                    // do whatever you want to do when times up.
                    ...
                    v_state++;
                }
                break;
             ...
        }
    }
}*/

 /*   //controles shooter
    private void BallDelivery(){
        if (gamepad2.left_stick_y > 0.5){
            telemetry.addLine("Give Ball");
            robot.shooter.DeliverBall();
        }
        else if(gamepad2.left_stick_y < -0.5){
            telemetry.addLine("Reverse Ball");
            robot.shooter.ReverseBall();
        }
        else{
            telemetry.addLine("New Ball");
            robot.shooter.NewBall();
        }
    }
    private void controlShooter(){

        if (gamepad2.right_bumper) {
            telemetry.addLine("Shooting");
            robot.shooterV.shootV(0);
        } else if (gamepad2.left_trigger > 0.5) {
            telemetry.addLine("Shooting faster");
            robot.shooter.shootFaster(0);
        }
        else{
            telemetry.addLine("Don't shoot");
            robot.shooter.stopMotor();
        }
    }

    private void Intake(){
       if (gamepad2.left_bumper) {
           telemetry.addLine("Taking ball in");
           robot.intake.IntakeStart(0.0);
       }
       else{
           telemetry.addLine("No taking in");
           robot.intake.IntakeStop();
       }
    }
*/
}
