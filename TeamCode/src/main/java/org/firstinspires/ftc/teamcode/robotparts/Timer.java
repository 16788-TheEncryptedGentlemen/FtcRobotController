package org.firstinspires.ftc.teamcode.robotparts;

public class Timer {
    /**
     * The total time elapsed since {@link Timer#reset()} was called.
     */
    private double time;
    public Timer()
    {
        time = System.currentTimeMillis();
    }

    /**
     * Reset the timer.
     */
    public void reset()
    {
        time = System.currentTimeMillis();
    }

    /**
     * Get the elapsed time since instantiated or since {@link Timer#reset()}.
     * @return double
     */
    public double getTime()
    {
        return (System.currentTimeMillis() - time)/1000;
    }
}