package org.firstinspires.ftc.teamcode.robotparts.TestPrograms;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robotparts.FlowerArm;

@TeleOp
public class FlowerArmTester extends OpMode {

    public FlowerArm flowerArm;

    public void init(){
        flowerArm = new FlowerArm(hardwareMap);
    }

    @Override
    public void loop(){
        ControlFlowerArm();
    }

    public void ControlFlowerArm() {
        if (gamepad2.dpad_up){
            telemetry.addLine("Arm up");
            flowerArm.moveArmToTop();
        }
        else if(gamepad2.dpad_down){
            telemetry.addLine("Arm down");
            flowerArm.moveArmToBotom();
        }
        else if(gamepad2.dpad_left){
            telemetry.addLine("Grabber closed");
            flowerArm.closeBallGrabber();
        }
        else if(gamepad2.dpad_right){
            telemetry.addLine("Grabber open");
            flowerArm.openBallGrabber();
        }
    }

}
