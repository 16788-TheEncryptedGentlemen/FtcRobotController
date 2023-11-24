package org.firstinspires.ftc.teamcode.robotparts;

public class Timer {

    //-----------------------------------------------------------------------
    //Used variables:
    // * Time: The start time of the timer in ms
    // * TimeLastChecked: The time since the last check on getTimeElapsedSinceLastCheck() in ms
    //-----------------------------------------------------------------------
    public double Time = 0;
    double TimeLastChecked = 0;
    //-----------------------------------------------------------------------
    //Used variables
    //-----------------------------------------------------------------------





    //-----------------------------------------------------------------------
    //Constructor
    //-----------------------------------------------------------------------
    public Timer()
    {
        Time = System.currentTimeMillis();
    }
    //-----------------------------------------------------------------------
    //Constructor
    //-----------------------------------------------------------------------





    //-----------------------------------------------------------------------
    //Methods:
    // * Reset(): Resets the Timer by setting the Time variable to the current time in ms
    // * getTime(): Gets the Time since the object was created in ms
    // * getTimeSinceLastCheck(): Returns the time since this specific method was last checked
    // * isBetween(): Returns true if the current time of this Timer is between the two given values
    //-----------------------------------------------------------------------
    public void Reset()
    {
        Time = System.currentTimeMillis();
    }

    public double getTime()
    {
        return (System.currentTimeMillis() - Time)/1000;
    }

    public double getTimeSinceLastCheck()
    {
        double TimeElapsed = getTime() - TimeLastChecked;
        TimeLastChecked = getTime();

        return TimeElapsed;
    }

    public boolean isBetween(double time1, double time2)
    {
        double CurrentTime = getTime();
        return CurrentTime > time1 && CurrentTime < time2;
    }
    //-----------------------------------------------------------------------
    //Methods
    //-----------------------------------------------------------------------

}

// TODO: Documenteren