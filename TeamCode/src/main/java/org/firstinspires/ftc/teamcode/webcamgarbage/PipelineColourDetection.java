package org.firstinspires.ftc.teamcode.webcamgarbage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class PipelineColourDetection extends OpenCvPipeline {

    /*
     * An enum to define the possible colours
     */
    public enum OutputColour
    {
        RED,
        BLUE,
        YELLOW
    }

    /*
     * Working variables
     */
    Mat rgbImage = new Mat();
    Mat redMat = new Mat();
    Mat blueMat = new Mat();
    Mat yellowMat = new Mat();

    int redCount = 0;
    int blueCount = 0;
    int yellowCount = 0;


    Point pointA = new Point(
            150,
            150);
    Point pointB = new Point(
            100,
            100);

    // Volatile since accessed by OpMode thread w/o synchronization DEFAULT = BLUE
    private volatile OutputColour colourOut = OutputColour.YELLOW;

    /** Use half of the degrees. */
    Scalar redMin = new Scalar(38, 25, 0);
    Scalar redMax = new Scalar(255, 255, 60);
    Scalar blueMin = new Scalar(100,0,0);
    Scalar blueMax = new Scalar(255,0,0);
    Scalar yellowMin = new Scalar(0,100,0);
    Scalar yellowMax = new Scalar(0,255,0);

    Scalar RED = new Scalar(6, 60, 60);
    Scalar BLUE = new Scalar(193, 60, 60);
    Scalar YELLOW = new Scalar(200, 200, 200);

    int font = Imgproc.FONT_HERSHEY_SIMPLEX;
    int scale = 10;
    int thickness = 4;


    // the running funtcion
    @Override
    public Mat processFrame(Mat input) {

        // convert input frame to hsv color format standard BGR
        Imgproc.cvtColor(input, rgbImage, Imgproc.COLOR_BGR2HSV);

        // check frame colors within color min max values, store those pixels in colorMat
        Core.inRange(rgbImage, redMin, redMax, redMat);
//        Core.inRange(rgbImage, blueMin, blueMax, blueMat);
//        Core.inRange(rgbImage, yellowMin, yellowMax, yellowMat);

        // count non empty pixels in colorMats, so count number of pixels of each color and store amount
        redCount = Core.countNonZero(redMat);
        blueCount = Core.countNonZero(blueMat);
        yellowCount = Core.countNonZero(yellowMat);

        // check which color is most common and set output to this value
//        if (redCount > blueCount && redCount > yellowCount) {
            colourOut = OutputColour.RED;
            Imgproc.putText(redMat, "redA", pointA,  font, scale, YELLOW, thickness);
            return redMat;
//        }
//        else if (yellowCount > blueCount && yellowCount > redCount) {
//            colourOut = OutputColour.YELLOW;
//            Imgproc.putText(yellowMat, "yellow", pointA,  font, scale, YELLOW, thickness);
//            return yellowMat;
//        } else {
//            colourOut = OutputColour.BLUE;
//            Imgproc.putText(blueMat, "blue", pointA,  font, scale, YELLOW, thickness);
//            return input;
//        }

        // return input frame to cam preview, so no cool filters yet

    }

    /*
     * Call this from the OpMode thread to obtain the latest analysis
     */
    public OutputColour getAnalysis()
    {
        return colourOut;
    }
}