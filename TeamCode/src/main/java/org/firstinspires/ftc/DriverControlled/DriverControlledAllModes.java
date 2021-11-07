package org.firstinspires.ftc.DriverControlled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.Robots.WedstrijdRobot;
import org.firstinspires.ftc.OtherObjects.Timer;

@TeleOp
public class DriverControlledAllModes extends OpMode 
{

    //MODE enum ---------------------------------------------------
        private static enum MODE
        {
            MECANUM_GYROCORRECT,
            ABSOLUTE_ORIENTATION,
            AUTOMATIC_ABSOLUTE_ORIENTATION;
            
            private static MODE[] vals = values();
            
            public void next()
            {
                Mode = vals[(this.ordinal()+1) % vals.length];
            }
        }
        
        public static MODE Mode = MODE.MECANUM_GYROCORRECT;
    //MODE enum ---------------------------------------------------
    
    
    
    //Initialization of all variables and delcaration of Robot -----------------
        WedstrijdRobot Robot;
        private boolean Press = false;
    
        //De strafe x en y snelheid
        private double LeftJoyX = 0;
        private double LeftJoyY = 0;
            
        //De snelheid waarop de robot moet strafen
        private double StrafeSpeed = 0;

        //De hoek waarop de robot moet strafen (tussen -180 en 180 graden)    
        private double StrafeAngle = 0; 
        
        //De snelheid van het draaien
        private double TurnSpeed = 0;        
        
        //Direction of movement
        private double DirectionAngle = 0;
        //Direction of the robot
        private double RobotAngle = 0;    
            
        //De hoek waarmee de robot van zijn gewenste hoek af zit
        private double DeviationAngle = 0;
        
        //De corrigerende factor van de afwijkende hoek
        private double CorrectionFactor = 0;
        //g is de hoek die de robot moet aanhouden bij het het mecanum drive strafen
        private double GyroCorrectionAngle = 0;
        //time is het variabel waardoor de robot even wacht met corrigeren, zodat het niet 'jerky' wordt
        private Timer timer;
    //Initialization of all variables and delcaration of Robot -----------------
    
    
    
    @Override
    //Laten zien welke motoren wat zijn
    public void init() 
    {
        Robot = new WedstrijdRobot(this);   //Init robot with OpMode (this)
        timer = new Timer();                                    //Init timer
    }



    @Override
    public void loop() 
    {
        
        //Gets left controller input which is used for strafing and driving
        LeftJoyY = -gamepad1.left_stick_y;
        LeftJoyX = gamepad1.left_stick_x;        
        
        //The Angle of the robot is gotten with IMU
        RobotAngle = Robot.imu.getAngle();
      
        //StrafeSpeed is calculated
        StrafeSpeed = Math.sqrt(Math.pow(LeftJoyX,2) + Math.pow(LeftJoyY,2));  

        //DirectionAngle is calculated
        if(StrafeSpeed != 0 && LeftJoyX != 0)
            DirectionAngle = Math.signum(LeftJoyX)*Math.toDegrees(Math.acos(LeftJoyY/StrafeSpeed));
        else if(LeftJoyY < 0)
            DirectionAngle = 180;
        else
            DirectionAngle = 0;

        //This is only checked now, as the DirectionAngle will otherwise not be correct
        if(StrafeSpeed > 1)
            StrafeSpeed = 1;
            
        //De x-coordinaat van de rechterjoystick bepaald de draaisnelheid
        TurnSpeed = gamepad1.right_stick_x;             

        //Als start wordt ingedrukt: re-initialiseer de gyro sensor
        if(gamepad1.start) 
            Robot.imu.ResetAngularOrientation();
        
        
        
        //ALLE HULPSTUKKEN HIER -----------------------------------------------------
        
        //Take the rings to the shooter in the robot
        /*
        if(gamepad2.a && !Robot.Shooter.Busy)
            Robot.Shooter.setToBusy();
        if(Robot.Shooter.Busy)
            Robot.Shooter.LoadRingToShooter();  
        */
        
        //Shoot rings
        /*
        if(gamepad2.right_bumper)
            Robot.Shooter.PowerShooter(0.85);
        else 
            Robot.Shooter.PowerShooter(0);
        */
        
        Robot.Intake.Slurp(-gamepad2.left_stick_y);
        //ALLE HULPSTUKKEN HIER -----------------------------------------------------
        
        
        //-----------------------------------------------------------------------------------------------------
        if(Mode == MODE.MECANUM_GYROCORRECT)
        {
            //Bereken de waardes van de motoren tijdens het strafen en set de speed value
            Robot.Drivetrain.setStrafeValues(DirectionAngle, StrafeSpeed);
            Robot.Drivetrain.addSpeed(-TurnSpeed, -TurnSpeed, TurnSpeed, TurnSpeed);            
            
            //If we are turning, reset the timer.
            if(gamepad1.right_stick_x != 0) 
                timer.Reset();
                
            //Corrigerende GyroCorrectionAngle instellen als de 250 ms nog niet voorbij zijn
            if(timer.getTime() < 0.25)
                GyroCorrectionAngle = Robot.imu.getAngle();
             
            //Afwijkingshoek berekenen
            DeviationAngle = RobotAngle - GyroCorrectionAngle;             
            
            //If there are no big jumps in angle, we are not standing still, not turning and 250 seconds has elapsed with no turning:   
            if(Math.abs(DeviationAngle) < 90 && StrafeSpeed != 0 && gamepad1.right_stick_x == 0 && timer.getTime() > 0.25)              
            {
                if(DeviationAngle > -45 && DeviationAngle < 45){
                    CorrectionFactor = DeviationAngle/45;
                }
                else{
                    CorrectionFactor = Math.signum(DeviationAngle);
                }
                
                Robot.Drivetrain.addSpeed(CorrectionFactor,CorrectionFactor,-CorrectionFactor,-CorrectionFactor);
            }
            
            telemetry.addData("MODE",Mode);
        }
        
        
        //-----------------------------------------------------------------------------------------------------  
        else if(Mode == MODE.ABSOLUTE_ORIENTATION)
        {      
            //De strafeangle berekenen m.b.v de directie waarin we willen strafen en de hoek van de robot op het moment
            StrafeAngle = DirectionAngle - RobotAngle;
            
            //Exceptie voor als de hoek groter is dan 180 graden of kleiner dan -180
            if(StrafeAngle > 180)
                StrafeAngle = StrafeAngle - 360;
            else if(StrafeAngle < -180)
                StrafeAngle = 360 + StrafeAngle;
            
            //Bereken de waardes van de motoren tijdens het strafen en set de speed value
            Robot.Drivetrain.setStrafeValues(StrafeAngle, StrafeSpeed);
            Robot.Drivetrain.addSpeed(-TurnSpeed, -TurnSpeed, TurnSpeed, TurnSpeed);        
            
            telemetry.addData("MODE",Mode);
        }
    
    
        //-----------------------------------------------------------------------------------------------------  
        else if(Mode == MODE.AUTOMATIC_ABSOLUTE_ORIENTATION)
        {
            //De strafeangle berekenen m.b.v de directie waarin we willen strafen en de hoek van de robot op het moment 
            StrafeAngle = DirectionAngle - RobotAngle;
        
            //Exceptie voor als de hoek groter is dan 180 graden of kleiner dan -180
            if(StrafeAngle > 180)
                StrafeAngle = StrafeAngle - 360;
            else if(StrafeAngle < -180)
                StrafeAngle = 360 + StrafeAngle;
            
            //Berekend de motor snelheid strafe waardes voor de robot
            Robot.Drivetrain.setStrafeValues(StrafeAngle, StrafeSpeed);
            
            //Laat de robot naar de DirectionAngle draaien
            if(Math.abs(StrafeAngle) < 45){
                CorrectionFactor = StrafeSpeed*(StrafeAngle/45);
            }
            else{
                CorrectionFactor = Math.signum(StrafeAngle)*StrafeSpeed;
            }
            
            Robot.Drivetrain.addSpeed(-CorrectionFactor, -CorrectionFactor, CorrectionFactor, CorrectionFactor);

            telemetry.addData("MODE",Mode);                        
        }        
        //-----------------------------------------------------------------------------------------------------  
        
        
        
        //Voorkomt overflow van de motor snelheid waardes
        Robot.Drivetrain.FixMotorSpeedOverflow();
        
        //Auto positioning for shooting ----------------------
        if(gamepad1.left_bumper)
            Robot.Drivetrain.setSpeed(Robot.ColorSensors.getSpeedValues());
        
        
        //Auto positioning for shooting ----------------------
        
        //Slow mode ---------------------
        if(gamepad1.right_bumper)
            Robot.Drivetrain.MultiplySpeed(0.25);
        //Slow mode ---------------------
        
        //zet de motoren aan
        Robot.Drivetrain.setPower();
        
        //Houdt de modes bij in een simpele teller
        if(gamepad1.back && !Press)
        {
            Mode.next();
            Press = true;
        }
        else if(!gamepad1.back)
            Press = false;

   }
}


