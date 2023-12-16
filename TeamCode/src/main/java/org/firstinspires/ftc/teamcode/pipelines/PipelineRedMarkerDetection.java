package org.firstinspires.ftc.teamcode.pipelines;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class PipelineRedMarkerDetection extends OpenCvPipeline {

    public enum ConeColour {UNKNOWN, RED, BLUE};
    private static final int CONE_CORRECTION_RED = 14000;
    private static final int CONE_CORRECTION_BLUE = 1000;
    private static final int YELLOW_CONE_PIXELS = 1000;
    private static final int RED_CONE_PIXELS = 7000;
    private static final int BLUE_CONE_PIXELS = 20;
    public ConeColour cone = ConeColour.UNKNOWN;
    /*
     * Working variables
     */
    Mat greyImg = new Mat();
    Mat edges = new Mat();
    Mat crop = new Mat();
    Mat redMat = new Mat();
    Mat frameOut = new Mat();


    Rect frame = new Rect(300,100,400,600);

    int redCount = 0;

    double result = 1;// 0 is left, 1 is middle and 2 is right.


    // Making scalars, the limits of the colour that the robot thinks of as red, yellow or blue.
    // Degrees are divided by 2 since OpenCV 0 to 180 degrees.
    //  TODO: figure out why the second and third number is either 255 or 100.
    Scalar redMin = new Scalar(170,100,100);
    Scalar redMax = new Scalar(180,255,255);

    Scalar blueMin = new Scalar(180 / 2.0,67 * 2.55,50 * 2.55);
    Scalar blueMax = new Scalar(280 / 2.0,255,255);

    int[] out = {0,0,0};


    // the running funtcion
    @Override
    public Mat processFrame(Mat input) {

//        crop = new Mat(input, frame);
        Imgproc.cvtColor(input, greyImg, Imgproc.COLOR_BGR2GRAY);

        //Blurring the image
        Imgproc.blur(greyImg, edges, new Size(3, 3));
        //Detecting the edges
        Imgproc.Canny(edges, edges, 100, 100*3);

        Mat dest = new Mat();
        Core.add(dest, Scalar.all(0), dest);
        frameOut.copyTo(dest, edges);

        return frameOut;

//        frameOut = edges;


        // check frame colors within color min max values, store those pixels in colorMat
//        Core.inRange(hsvImage, redMin, redMax, redMat);

        // count number of pixels of each color and correct for cone colour.
//        redCount = Core.countNonZero(redMat) - (cone == ConeColour.RED ? CONE_CORRECTION_RED : 0);

//        out[0] = redCount;

//        List<MatOfPoint> contours = new ArrayList<>();
//        Mat hierarchy = new Mat();
//        // find contours
//        Imgproc.findContours(greyImg, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);

        // if any contour exist...
//        if (hierarchy.size().height > 0 && hierarchy.size().width > 0) {
//            // for each contour, display it in yellow
//            for (int idx = 0; idx >= 0; idx = (int) hierarchy.get(0, idx)[0]) {
//                Imgproc.drawContours(frameOut, contours, idx, new Scalar(0, 255, 255));
//            }
//        }
//        for (int i = 0; i < contours.size(); i++) {
//            Scalar color = new Scalar(255, 255, 255);
//            Imgproc.drawContours(frameOut, contours, i, color, 5);
//        }
//
//        result = hierarchy.size().height;

    }
    /*
     * Call this from the OpMode thread to obtain the latest analysis
     */
    public int[] getAnalysis()
    {
        return out;
    }

    public double getResult()
    {
        return result;
    }
}