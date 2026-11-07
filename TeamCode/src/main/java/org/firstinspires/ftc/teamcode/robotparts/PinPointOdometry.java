package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver.EncoderDirection;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class PinPointOdometry {
    public GoBildaPinpointDriver pinpoint;

    public PinPointOdometry(HardwareMap hardwareMap) {
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "PinPoint");
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpoint.setEncoderDirections(EncoderDirection.FORWARD, EncoderDirection.FORWARD);
        pinpoint.setOffsets(0.0, 0.0);
    }

    /** Returns the x position of the odometry. */
    public double getX() {
        return pinpoint.getPosX();
    }

    /** Returns the y position of the odometry. */
    public double getY() {
    return pinpoint.getPosY();
    }

    /** Resets the encoders. */
    public void reset() {
        pinpoint.resetPosAndIMU();
    }
}