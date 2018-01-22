package org.firstinspires.ftc.TeamCodeRelicRecovery;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HardwarePhynn {

    public DcMotor motorRight = null;
    public DcMotor motorLeft = null;
    public DcMotor Lift = null;
    public Servo servo1 = null;
    public Servo servo2 = null;
    public Servo servo3 = null;
    public ColorSensor ColorSensor = null;

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
