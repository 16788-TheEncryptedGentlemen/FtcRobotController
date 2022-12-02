package org.firstinspires.ftc.teamcode.webcamgarbage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class PipelineColourDetection extends OpenCvPipeline {

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


    //making scalars, TODO: check this
    Scalar yellowMin = new Scalar(20,100,100);
    Scalar yellowMax = new Scalar(30,255,255);
    Scalar blueMin = new Scalar(75,100,100);
    Scalar blueMax = new Scalar(135,255,255);
    Scalar redMin = new Scalar(170,100,100);
    Scalar redMax = new Scalar(180,255,255);

    int[] out = {0,0,0};


    // the running funtcion
    @Override
    public Mat processFrame(Mat input) {

        crop = new Mat(input, frame);
        Imgproc.cvtColor(crop, hsvImage, Imgproc.COLOR_RGB2HSV);


        // check frame colors within color min max values, store those pixels in colorMat
        Core.inRange(hsvImage, redMin, redMax, redMat);
        Core.inRange(hsvImage, blueMin, blueMax, blueMat);
        Core.inRange(hsvImage, yellowMin, yellowMax, yellowMat);

        // count non empty pixels in colorMats, so count number of pixels of each color and store amount
        redCount = Core.countNonZero(redMat);
        blueCount = Core.countNonZero(blueMat);
        yellowCount = Core.countNonZero(yellowMat);

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