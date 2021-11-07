package org.firstinspires.ftc.Testing;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.Robots.KipRobot;

@TeleOp
public class KipRobotOpModeTest extends OpMode
{
    public KipRobot CyberKip;

    @Override
    public void init() 
    {
        CyberKip = new KipRobot(this); 
    }
    
    @Override
    public void loop()
    {
        if(gamepad1.a)
        {
            CyberKip.Shooter.Shoot();
        }
        else
        {
            CyberKip.Shooter.Stop();
        }
        
        /*
        if (gamepad1.left_stick_y > 0)
        {
            CyberKip.Intake.In();
        }
        else if (gamepad1.left_stick_y < 0)
        {
            CyberKip.Intake.Out();
        }
        else 
        {
            CyberKip.Intake.Stop();
        }
        */
        //CyberKip.Intake.In(gamepad1.left_stick_y);
        
        //CyberKip.Drivetrain.setPower(-0.5);
        //CyberKip.Drivetrain.setPower(gamepad1.left_stick_y);
        //CyberKip.Drivetrain.Strafe(-0.5);
        //CyberKip.Drivetrain.Strafe(gamepad1.left_stick_x);
        if (gamepad1.left_stick_x == 0)
        {
            CyberKip.Drivetrain.setPower(gamepad1.left_stick_y);
        }
        else if (gamepad1.left_stick_y == 0)
        {
            CyberKip.Drivetrain.Strafe(gamepad1.left_stick_x);
        }
        else
        {
            CyberKip.Drivetrain.Stop();
        }
        telemetry.addData("y-coordinaat", gamepad1.left_stick_y);   
    }
}
