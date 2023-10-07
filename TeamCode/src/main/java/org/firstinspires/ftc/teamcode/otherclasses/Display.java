package org.firstinspires.ftc.teamcode.otherclasses;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Display {
    /** Telemetry instance for displaying data. */
    private final Telemetry telemetry;

    /** Constructor for the display class. */
    public Display(Telemetry telemetryArgument) {
        telemetry = telemetryArgument;
    }

    /** Shows loading on screen. */
    public void showLoading(String object) {
        telemetry.addData("Loading", object);
        telemetry.update();
    }
}