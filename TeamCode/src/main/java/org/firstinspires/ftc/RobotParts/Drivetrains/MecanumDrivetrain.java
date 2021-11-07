package org.firstinspires.ftc.RobotParts.Drivetrains;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.RobotParts.IMU;
import org.firstinspires.ftc.OtherObjects.Timer;
import org.firstinspires.ftc.RobotParts.Odometry.OdometryOmniWheels;



public class MecanumDrivetrain 
{
    //-----------------------------------------------------------
    //Used Variables:
    // * runningOpMode: The LinearOpMode to stop the robot
    // * MotorSpeed: An array to store the four different motor speeds
    // * RV: Front right motor (RechtsVoor)
    // * RA: Back right motor  (RechtsAchter)
    // * LV: Front left motor (LinksVoor)
    // * LA: Back left motor (LinksAchter)
    // * Odometry: The odometry of the robot
    // * IMU: The Integrated Measurement Unit of the robot
    //-----------------------------------------------------------
        private LinearOpMode runningOpMode;
        public double[] MotorSpeed = new double[4]; 
        public DcMotorEx RV;
        public DcMotorEx RA;
        public DcMotorEx LV;
        public DcMotorEx LA;
        private OdometryOmniWheels Odometry;
        private IMU imu;
    //-----------------------------------------------------------
    //Used Variables
    //-----------------------------------------------------------




    //-----------------------------------------------------------
    //Constructors
    // * The first constructor is the default MecanumDrivetrain constructor
    // * The second constructor calls the first constructor with an added LinearOpMode for autonomous
    //-----------------------------------------------------------
        public MecanumDrivetrain(HardwareMap hardwareMap, OdometryOmniWheels _Odometry, IMU _imu)
        {
            Odometry = _Odometry;
            imu = _imu;
            RV = hardwareMap.get(DcMotorEx.class, "RV");
            RA = hardwareMap.get(DcMotorEx.class, "RA");
            LV = hardwareMap.get(DcMotorEx.class, "LV");
            LA = hardwareMap.get(DcMotorEx.class, "LA");
            
            //Reversing the left motors because they are mirrored
            LV.setDirection(DcMotorEx.Direction.REVERSE);
            LA.setDirection(DcMotorEx.Direction.REVERSE);
            
            //Run all motors with encoders
            RV.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);    
            RA.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            LV.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
            LA.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        }
    
    
        
        //Constructor for MecanumDrivetrain with an LinearOpMode as argument instead of hardwareMap
        public MecanumDrivetrain(LinearOpMode _runningOpMode, OdometryOmniWheels _Odometry, IMU _imu)
        {
            this(_runningOpMode.hardwareMap, _Odometry, _imu);
            runningOpMode = _runningOpMode;
        }
    //-----------------------------------------------------------
    //Constructors
    //-----------------------------------------------------------





    //---------------------------------------------------------
    //Set, add, power and other speed methods:
    // * setSpeed(): Sets speed values to the MotorSpeed array. The following arguments can be entered:
    // * - 4 doubles
    // * - 1 double
    // * - 1 array 
    // * addSpeed(): Adds speed values to the MotorSpeed array. The following arguments can be entered:
    // * - 4 doubles
    // * - 1 double
    // * - 1 array 
    // * setPower(): Sets power to the motors. The following arguments can be entered:
    // * - No arguments. This will cause the motors to be powered with the values in the MotorSpeed array
    // * - 4 doubles
    // * - 1 double
    // * - 1 array 
    // * Stop(): Stops the motors
    // * MultiplySpeed(): Multiplies all speed values in the MotorSpeed array with a factor
    // * GetVelocity(): Returns an array of the velocities of the motors
    // * GetPower(): Returns an array of the power values of the motors
    //---------------------------------------------------------
        public void setSpeed(double SpeedRV, double SpeedRA, double SpeedLV, double SpeedLA)
        {
            MotorSpeed[0] = SpeedRV;
            MotorSpeed[1] = SpeedRA;
            MotorSpeed[2] = SpeedLV;
            MotorSpeed[3] = SpeedLA;
        }
        public void setSpeed(double Speed)
        {
            MotorSpeed[0] = Speed;
            MotorSpeed[1] = Speed;
            MotorSpeed[2] = Speed;
            MotorSpeed[3] = Speed;
        }
        public void setSpeed(double[] Speedarr)
        {
            MotorSpeed[0] = Speedarr[0];
            MotorSpeed[1] = Speedarr[1];
            MotorSpeed[2] = Speedarr[2];
            MotorSpeed[3] = Speedarr[3];
        }            



        public void addSpeed(double SpeedRV, double SpeedRA, double SpeedLV, double SpeedLA)
        {
            MotorSpeed[0] += SpeedRV;
            MotorSpeed[1] += SpeedRA;
            MotorSpeed[2] += SpeedLV;
            MotorSpeed[3] += SpeedLA;
        }
        public void addSpeed(double Speed)
        {
            MotorSpeed[0] += Speed;
            MotorSpeed[1] += Speed;
            MotorSpeed[2] += Speed;
            MotorSpeed[3] += Speed;
        }
        public void addSpeed(double[] Speedarr)
        {
            MotorSpeed[0] += Speedarr[0];
            MotorSpeed[1] += Speedarr[1];
            MotorSpeed[2] += Speedarr[2];
            MotorSpeed[3] += Speedarr[3];
        }        
        
        

        public void setPower()
        {
            RV.setPower(MotorSpeed[0]);
            RA.setPower(MotorSpeed[1]);
            LV.setPower(MotorSpeed[2]); 
            LA.setPower(MotorSpeed[3]);    
        } 
        public void setPower(double SpeedRV, double SpeedRA, double SpeedLV, double SpeedLA)
        {
            RV.setPower(SpeedRV);
            RA.setPower(SpeedRA);
            LV.setPower(SpeedLV); 
            LA.setPower(SpeedLA);    
        }  
        public void setPower(double Speed)
        {
            RV.setPower(Speed);
            RA.setPower(Speed);
            LV.setPower(Speed); 
            LA.setPower(Speed);    
        }      
        public void setPower(double[] Speedarr)
        {
            RV.setPower(Speedarr[0]);
            RA.setPower(Speedarr[1]);
            LV.setPower(Speedarr[2]); 
            LA.setPower(Speedarr[3]);    
        }            
        
        
        
        public void Stop()
        {
            setPower(0.0);  
        }
        
        public void MultiplySpeed(double factor)
        {
            MotorSpeed[0] *= factor;
            MotorSpeed[1] *= factor;
            MotorSpeed[2] *= factor;
            MotorSpeed[3] *= factor;
        }
        
        public double[] getVelocities()
        {
            return new double[] {RV.getVelocity(), RA.getVelocity(), LV.getVelocity(), LA.getVelocity()};
        }        
        public double[] getPowers()
        {
            return new double[] {RV.getPower(), RA.getPower(), LV.getPower(), LA.getPower()};
        }
    //---------------------------------------------------------
    //Set, add and power speed methods
    //---------------------------------------------------------
    
    
    
    //---------------------------------------------------------
    //Simple drive methods:
    // * DriveStraight(): Drives the robot forward a certain amount of cm with a given Speed
    // * setSpeedValuesTurnRobot(): Sets speed values to the robot for turning a certain amount of degrees in the absolute orientation plane
    // * TurnRobotNoLoop(): Turns the robot to the robot a certain degrees without a while loop
    // * Follow(): The robot will follow a theoretical line with a specific angle. Note that this method does not loop
    // * TurnRobotAO(): Turns the robot on the absolute orientation plane with a certain angle
    //---------------------------------------------------------
        public void DriveStraight(double Distance, double Speed)
        {
            double OriginYPos = Odometry.getY();
            double endDistance = Math.abs(Distance + OriginYPos);
            
            while(Math.abs(Odometry.getY()) < endDistance && !runningOpMode.isStopRequested())
                setPower(Speed);
            
            setPower(0);            
        }
        
        public void setSpeedValuesTurnRobot(double NewAngle)
        {
            double[] SpeedValues = imu.getTurnCorrectionValues(NewAngle, 20);
            setSpeed(SpeedValues);
        }
        
        public void TurnRobotNoLoop(double NewAngle)
        {
            setSpeedValuesTurnRobot(NewAngle);
            setPower();
        }
        
        public void Follow(double NewAngle, double Speed)
        {
            setSpeedValuesTurnRobot(NewAngle);
            addSpeed(Speed);
            FixMotorSpeedOverflow();
            setPower();
        }        
        
        public void TurnRobotAO(double Angle)
        {
            while(Math.abs(Angle - imu.getAngle()) > 2 && !runningOpMode.isStopRequested())
            {
                double[] SpeedValues = imu.getTurnCorrectionValues(Angle, 70);
                setPower(SpeedValues);
            }
            Stop();
        }
        
    //---------------------------------------------------------
    //Simple drive functions
    //---------------------------------------------------------    
    
    
    

    //---------------------------------------------------------
    //Strafing methods:
    // * getStrafeValues(): Gets the strafe values of the motors for a certain Angle and speed value
    // * setStrafeValues(): sets speed values to robot for strafing. This method has two isomorphic methods:
    // * - Angle and speed value: Sets the MotorSpeed array values to associated values for strafing
    // * - Angle, speed and Desired angle: Sets the MotorSpeed array values to associated values for strafing and corrects for a desired angle in the absolute orientation plane
    // * powerStrafeValues(): powers robot to strafe in the desired direction. This method has two isomorphic methods:
    // * - Angle and speed value: Powers the motors to associated values for strafing
    // * - Angle, speed and Desired angle: Powers the motor values to associated values for strafing and corrects for a desired angle in the absolute orientation plane
    //---------------------------------------------------------    
        public double[] getStrafeValues(double StrafeAngle, double speed)
        {
            double MotorSpeedStrafe;
            
            if(StrafeAngle >= 0 && StrafeAngle <= 90)
            {
                MotorSpeedStrafe = (speed*(45 - StrafeAngle))/45;
                return new double[]{MotorSpeedStrafe, speed, speed, MotorSpeedStrafe};
            }   
            else if(StrafeAngle >= 90 && StrafeAngle <= 180)
            { 
                MotorSpeedStrafe = (speed*(135 - StrafeAngle))/45;
                return new double[]{-speed, MotorSpeedStrafe, MotorSpeedStrafe, -speed};
            }
            else if(StrafeAngle >= -90 && StrafeAngle <= 0)
            { 
                MotorSpeedStrafe = (speed*(45 + StrafeAngle))/45;
                return new double[]{speed, MotorSpeedStrafe, MotorSpeedStrafe, speed};
            }  
            else
            {
                MotorSpeedStrafe = (speed*(135 + StrafeAngle))/45;
                return new double[]{MotorSpeedStrafe, -speed, -speed, MotorSpeedStrafe};
            }
        }
    
    
    
        public void setStrafeValues(double StrafeAngle, double Speed)
        {
            setSpeed(getStrafeValues(StrafeAngle, Speed));
        }
        
        public void setStrafeValues(double StrafeAngle, double Speed, double DesiredAngle)
        {
            setStrafeValues(StrafeAngle, Speed);
            double[] CorrectionFactors = imu.getTurnCorrectionValues(DesiredAngle, 30);
            addSpeed(CorrectionFactors);
        }
        
        
        
        public void powerStrafeValues(double StrafeAngle, double Speed)
        {
            setStrafeValues(StrafeAngle, Speed);
            setPower();
        }

        public void powerStrafeValues(double StrafeAngle, double Speed, double DesiredAngle)
        {
            setStrafeValues(StrafeAngle, Speed, DesiredAngle);
            FixMotorSpeedOverflow();
            setPower();
        }
    
    //---------------------------------------------------------
    //Strafing methods
    //---------------------------------------------------------
    
    
    
    
    
    //---------------------------------------------------------
    //Other methods
    // * FixMotorSpeedOverflow(): Decreases all values to be under a value of 1 if needed
    // * EmptyValues(): Sets all values in MotorSpeed to 0
    //---------------------------------------------------------    
    
        public void FixMotorSpeedOverflow()
        {
            double max = 1; 
            for(int i = 0; i < 4; i++)
            {
                if(Math.abs(MotorSpeed[i]) > max)
                    max = Math.abs(MotorSpeed[i]);
            }
            MultiplySpeed(1.0/max);
        }
        
        public void EmptyMotorSpeedValues()
        {
           MotorSpeed = new double[4];  
        }
    
    //---------------------------------------------------------
    //Other methods
    //---------------------------------------------------------       
 
}
