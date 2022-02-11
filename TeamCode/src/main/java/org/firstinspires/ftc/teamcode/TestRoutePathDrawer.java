package org.firstinspires.ftc.opmodeTemplates;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.Robots.WedstrijdRobot;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute;
import org.firstinspires.ftc.Autonomousclasses.BezierCurveRoute.DRIVE_METHOD;

@Autonomous
public class TestRoutePathDrawer extends LinearOpMode
{
    //Variables------------------------------------
    
    
    public void runOpMode()
    {
        //Initialisation------------------------------------
        WedstrijdRobot Robot = new WedstrijdRobot(this); 
        
        BezierCurveRoute route_1_situatie_3 = new BezierCurveRoute(
    new double[] {-96.7950000000001, 914.175, -728.950000000002, -2258.55, 3832.365, -1613.8475}, //The x-coefficients 
    new double[] {423.03, -2016.5625, 3214.55, 466.05, -3086.685, 1269.6875}, //The y-coefficients 
    Robot, 
    06,
    DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW 
    this 
);
 
        
        BezierCurveRoute route_2_situaie_3 = new BezierCurveRoute(
            new double[] {-440.955, 544.92, -221.6725}, //The x-coefficients 
            new double[] {-26.8875, 53.775, -148.18}, //The y-coefficients 
            Robot, 
            0.6,
            DRIVE_METHOD.STRAFE, //STRAFE or FOLLOW 
            this 
        );

        

        
        waitForStart();
        
        route_1_situatie_3.ExecuteWithPointSkip();
        route_2_situaie_3.ExecuteWithPointSkip();
        
        //Run program------------------------------------
        
        
    }
    
}
