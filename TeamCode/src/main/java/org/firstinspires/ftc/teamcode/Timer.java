package org.firstinspires.ftc.teamcode;

/** Timer class to measure the elapsed time. Includes a reset function */
public class Timer {
    /** The start time of the timer in milliseconds. */
    private double startTime = 0;

    /** Constructor of the timer. The timer is started immediately. */
    public Timer() {
        startTime = System.currentTimeMillis();
    }

    /** Reset the timer back to zero. */
    public void Reset() {
        startTime = System.currentTimeMillis();
    }

    /**
     * Get the elapsed time since the timer was started.
     * 
     * @return The elapsed time in seconds.
     */
    public double getTime() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }
}
