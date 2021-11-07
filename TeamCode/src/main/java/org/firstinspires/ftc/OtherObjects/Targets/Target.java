package org.firstinspires.ftc.OtherObjects.Targets;

public class Target extends TARGET_ENUM_CLASS
{
    private TARGET TargetValue = TARGET.NONE;



    public Target(TARGET target)
    {
        TargetValue = target;
    }
    
    public void set(TARGET target)
    {
        TargetValue = target;
    }
    
    public TARGET get()
    {
        return TargetValue;
    }
    
    public boolean equals(TARGET target)
    {
        return TargetValue == target;
    }

}
