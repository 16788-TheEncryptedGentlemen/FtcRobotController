package org.firstinspires.ftc.teamcode.robotparts;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver.EncoderDirection;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.robotparts.template.BaseOdometry;

public class PinPointOdometry extends BaseOdometry {
    public GoBildaPinpointDriver pinpoint;

    public PinPointOdometry(HardwareMap hardwareMap) {
        pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "PinPoint");
        pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        pinpoint.setEncoderDirections(EncoderDirection.FORWARD, EncoderDirection.FORWARD);
        pinpoint.setOffsets(0.0, 0.0, DistanceUnit.MM);
    }

    /** Returns the x position of the pinpointodometry. */
    @Override
    public double getX() { return pinpoint.getPosX(DistanceUnit.MM); }

    /** Returns the y position of the pinpointodometry. */
    @Override
    public double getY() { return pinpoint.getPosY(DistanceUnit.MM); }

    /** Resets the encoders. */
    @Override
    public void reset() { pinpoint.resetPosAndIMU(); }
}