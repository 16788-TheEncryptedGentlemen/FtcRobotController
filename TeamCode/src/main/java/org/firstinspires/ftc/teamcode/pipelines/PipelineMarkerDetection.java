package org.firstinspires.ftc.teamcode.pipelines;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.Arrays;
import java.util.List;

public class PipelineMarkerDetection extends OpenCvPipeline {

    // Target element colors RGB values, not sure why this datatype
    List<Integer> blueColor = Arrays.asList(0, 0, 255);
    List<Integer> redColor = Arrays.asList(255, 0, 0);

    // Variable for simulation, show or dont show the average color in a field
    public int showAvg = 1;
    public int zoneXpos = 10;
    public int zoneYpos = 10;

    // Output variables, 0,1,2 for Left, Middle, Right result
    static int redOutputZone = 1;
    static int blueOutputZone = 1;

    // Mats for storing frame or parts of frame
    Mat originalFrame;
    Mat zoneL;
    Mat zoneM;
    Mat zoneR;

    // Scalar == RGB color value, these will store the average color of a zone
    Scalar avgColorL;
    Scalar avgColorM;
    Scalar avgColorR;

    // These will store how close each average color is from the target color, for red and blue cases
    // Name meaning RcolorDistanceL == RedcolorDistanceLeft
    double RcolorDistanceL = 1;
    double RcolorDistanceM = 1;
    double RcolorDistanceR = 1;
    double BcolorDistanceL = 1;
    double BcolorDistanceM = 1;
    double BcolorDistanceR = 1;

    // This will store how close the closest color is to target colors
    double minColorDistanceR = 0;
    double minColorDistanceB = 0;

    // Rectangles containing vision zones
    Rect zoneLRect = new Rect(100, 100, 280, 280);    //(x, y, width, height)
    Rect zoneMRect = new Rect(500, 100, 280, 280);     //Rect(450, 200, 150, 150);
    Rect zoneRRect = new Rect(900, 100, 280, 280);     //Rect(800, 200, 150, 150);

    // the running funtcion
    @Override
    public Mat processFrame(Mat frame) {
        // Maybe use this if we have color format problems
        // Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGB2GRAY);

        // Creating duplicate of original frame
//        originalFrame = frame.clone();

        // Defining searching zones, these are the possible element locations
        zoneL = frame.submat(zoneLRect);
        zoneM = frame.submat(zoneMRect);
        zoneR = frame.submat(zoneRRect);

        // Take the average color in each zone
        avgColorL = Core.mean(zoneL);
        avgColorM = Core.mean(zoneM);
        avgColorR = Core.mean(zoneR);

        // If enabled, draw the average colors in the zones for debugging, this step is only visual
        if (showAvg == 1) {
            zoneL.setTo(avgColorL);
            zoneM.setTo(avgColorM);
            zoneR.setTo(avgColorR);
        }

        // Calclate how far/close from the element color the average zone color is, for red and blue cases
        double RcolorDistanceL = color_distance(avgColorL, redColor);
        double RcolorDistanceM = color_distance(avgColorM, redColor);
        double RcolorDistanceR = color_distance(avgColorR, redColor);

        double BcolorDistanceL = color_distance(avgColorL, blueColor);
        double BcolorDistanceM = color_distance(avgColorM, blueColor);
        double BcolorDistanceR = color_distance(avgColorR, blueColor);

        // Get the smallest of the color distances
        minColorDistanceR = Math.min(RcolorDistanceR, Math.min(RcolorDistanceL, RcolorDistanceM));
        minColorDistanceB = Math.min(BcolorDistanceR, Math.min(BcolorDistanceL, BcolorDistanceM));

        // Set output zones, output most red and most blue zone and write telemetry
        // TODO: currently default position == right, change to best case for each side
        if (minColorDistanceR == RcolorDistanceL) {
//        telemetry.addData("Left has Red Element, Dist = ", RcolorDistanceL);
            redOutputZone = 0;
        } else if (minColorDistanceR == RcolorDistanceM) {
//        telemetry.addData("Middle has Red Element, Dist = ", RcolorDistanceM);
            redOutputZone = 1;
        } else {
//        telemetry.addData("Right has Red Element, Dist = ", RcolorDistanceR);
            redOutputZone = 2;
        }

        if (minColorDistanceB == BcolorDistanceL) {
//        telemetry.addData("Left has Blue Element, Dist = ", BcolorDistanceL);
            blueOutputZone = 0;
        } else if (minColorDistanceB == BcolorDistanceM) {
//        telemetry.addData("Middle has Blue Element, Dist = ", BcolorDistanceM);
            blueOutputZone = 1;
        } else {
//        telemetry.addData("Right has Blue Element, Dist = ", BcolorDistanceR);
            blueOutputZone = 2;
        }

//        telemetry.update();

        return frame;


    }

    // Function for calculating how close together two colors are
    public double color_distance(Scalar color1, List color2) {
        double r1 = color1.val[0];
        double g1 = color1.val[1];
        double b1 = color1.val[2];

        int r2 = (int) color2.get(0);
        int g2 = (int) color2.get(1);
        int b2 = (int) color2.get(2);

        // That greek triangle guy
        return Math.sqrt(Math.pow((r1 - r2), 2) + Math.pow((g1 - g2), 2) + Math.pow((b1 - b2), 2));
    }

    // Returns the most red position, call in Red side auto programs
    public int getRedPosition() {
        return redOutputZone;
    }

    // Returns the most blue position, call in Blue side auto programs
    public int getBluePosition() {
        return blueOutputZone;
    }
}
