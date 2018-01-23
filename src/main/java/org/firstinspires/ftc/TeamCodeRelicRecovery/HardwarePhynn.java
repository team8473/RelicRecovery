package org.firstinspires.ftc.TeamCodeRelicRecovery;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwarePhynn {

    public DcMotor motorRight = null;
    public DcMotor motorLeft = null;
    public DcMotor Lift = null;
    public Servo servo1 = null;
    public Servo servo2 = null;
    public Servo servo3 = null;
    public ColorSensor ColorSensor = null;

    public ElapsedTime runtime = new ElapsedTime();

    public static final double     COUNTS_PER_MOTOR_REV    = 374 ;      // eg: ANDY MARK Motor Encoder
    public static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    public static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference of the wheel
    public static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    public static final double     DRIVE_SPEED             = 0.25;
    public static final double     TURN_SPEED              = 0.4;
    public static final double     RIGHT                   = 1;
    public static final double     LEFT                    = 0;
    public static final double     ROBOT_SHORT_DIAMETER_IN = 16.35; // Used to find the circumference of the robots ellipse
    public static final double     ROBOT_LONG_DIAMETER_IN  = 17.4;  // Used to find the circumference of the robots ellipse

    public static final double     kP                      = 0;
    public static final double     kI                      = 0;
    public static final double     kD                      = 0;

    boolean Open = true;
    boolean See = true;

    HardwareMap hwMap = null;

    public HardwarePhynn(){

    }
    
    public void init (HardwareMap ahwMap){

        hwMap = ahwMap;

        motorRight = hwMap.get(DcMotor.class, "right");
        motorLeft = hwMap.get(DcMotor.class, "left");
        Lift = hwMap.get(DcMotor.class, "lift");
        
        motorLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        motorRight.setPower(0);
        motorLeft.setPower(0);
        Lift.setPower(0);

        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        servo1 = hwMap.get(Servo.class, "servo1");
        servo2 = hwMap.get(Servo.class, "servo2");
        servo3 = hwMap.get(Servo.class, "servo3");
        
        servo2.setDirection(Servo.Direction.REVERSE);
        
        ColorSensor = hwMap.get(ColorSensor.class, "mr");
        
        
    }
}
