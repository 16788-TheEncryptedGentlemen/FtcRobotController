package org.firstinspires.ftc.teamcode.webcamgarbage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class PipelineColourDetection extends OpenCvPipeline {
    public enum ConeColour {UNKNOWN, RED, BLUE};
    private static final int CONE_CORRECTION_RED = 7000;
    private static final int CONE_CORRECTION_BLUE = 2500;
    public ConeColour cone = ConeColour.UNKNOWN;
    /*
     * Working variables
     */
    Mat hsvImage = new Mat();
    Mat crop = new Mat();
    Mat redMat = new Mat();
    Mat blueMat = new Mat();
    Mat yellowMat = new Mat();

    Rect frame = new Rect(300,100,400,600);

    int redCount = 0;
    int blueCount = 0;
    int yellowCount = 0;

    int result = 1;


    // Making scalars, the limits of the colour that the robot thinks of as red, yellow or blue.
    // Degrees are divided by 2 since OpenCV 0 to 180 degrees.
    //  TODO: change blue because gray = blue according to robot.
    //  TODO: figure out why the second and third number is either 255 or 100.
    Scalar redMin = new Scalar(170,100,100);
    Scalar redMax = new Scalar(180,255,255);
    Scalar yellowMin = new Scalar(20,100,100);
    Scalar yellowMax = new Scalar(30,255,255);
    Scalar blueMin = new Scalar(180 / 2.0,67 * 2.55,50 * 2.55);
    Scalar blueMax = new Scalar(280 / 2.0,255,255);

    int[] out = {0,0,0};


    // the running funtcion
    @Override
    public Mat processFrame(Mat input) {

        crop = new Mat(input, frame);
        Imgproc.cvtColor(crop, hsvImage, Imgproc.COLOR_RGB2HSV);


        // check frame colors within color min max values, store those pixels in colorMat
        Core.inRange(hsvImage, redMin, redMax, redMat);
        Core.inRange(hsvImage, yellowMin, yellowMax, yellowMat);
        Core.inRange(hsvImage, blueMin, blueMax, blueMat);

        // count number of pixels of each color and correct for cone colour.
        redCount = Core.countNonZero(redMat) - (cone == ConeColour.RED ? CONE_CORRECTION_RED : 0);
        yellowCount = Core.countNonZero(yellowMat);
        blueCount = Core.countNonZero(blueMat) - (cone == ConeColour.BLUE ? CONE_CORRECTION_BLUE : 0);

        out[0] = redCount;
        out[1] = yellowCount;
        out[2] = blueCount;

        if (blueCount > yellowCount && blueCount > redCount){
            result = 3; // blue
        } else if (yellowCount > redCount && yellowCount > blueCount) {
            result = 2; // yellow
        } else {
            result = 1; // red
        }

        // return input frame to cam preview, so no cool filters yet
        return crop;
    }

    /*
     * Call this from the OpMode thread to obtain the latest analysis
     */
    public int[] getAnalysis()
    {
        return out;
    }

    public int getResult()
    {
        return result;
    }
}