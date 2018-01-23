package org.firstinspires.ftc.TeamCodeVelocityVortex;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;



@Autonomous(name="TankDrive", group="Gabe")
@Disabled
public class TestTeleop extends OpMode {

    //DcMotorController ShooterController;
    DcMotor MotorRight;
    DcMotor MotorLeft;
    DcMotor Lift;
    Servo Servo1;
    Servo Servo2;
    Servo Servo3;
    ColorSensor ColorSensor;

    int motorLeft = 1;
    int motorRight = 3;

    int right = 1; //channel 3 (port 3)
    int left = 2;//channel 1 (port 1)
    //int arm = 4;//channel 4 (port 4)
    //int claw = 2;//channel 2 (port 2)
    boolean ServoPos = true;

    /**
     * Constructor
     */
    public TestTeleop() {

    }


    /*
     * Code to run when the op mode is initialized goes here
     *
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#init()
     */
    @Override
    public void init() {

        //ShooterController = hardwareMap.dcMotorController.get ("shooter");
        MotorRight = hardwareMap.dcMotor.get("front right");
        MotorLeft = hardwareMap.dcMotor.get("front left");
        Servo1 = hardwareMap.servo.get("servo1");
        Servo2 = hardwareMap.servo.get("servo2");
        Servo3 = hardwareMap.servo.get("servo3");
        Lift = hardwareMap.dcMotor.get("lift");
        ColorSensor = hardwareMap.colorSensor.get("mr");

        Servo2.setDirection(Servo.Direction.REVERSE);


    }


    @Override
    public void loop() {
     /*
      * zipline arm control release loop
      * pressnig the L/R bumper extends the corresponding arm via servo
      */



        telemetry.addData("Clear", ColorSensor.alpha());
        telemetry.addData("Red  ",ColorSensor.red());
        telemetry.addData("Green", ColorSensor.green());
        telemetry.addData("Blue ", ColorSensor.blue());
        telemetry.update();


        float right = gamepad1.left_stick_y;
        float left = -gamepad1.right_stick_y;
        float lift = gamepad2.right_stick_y;

        left = Range.clip(-left, -1, 1);
        right = Range.clip(-right, -1, 1);
        lift = Range.clip(-lift, -1, 1);


        MotorRight.setPower(Range.clip(right, -1, 1));
        MotorLeft.setPower(Range.clip(left, -1, 1));
        Lift.setPower(Range.clip(lift, -.6, .6));



        /*if (gamepad2.x){
            Servo1.setPosition(.1);
            Servo2.setPosition(.1);

        }else if(gamepad2.b){
            Servo1.setPosition(.65);
            Servo2.setPosition(.65);

        }*/


        if (gamepad2.a && ServoPos == false){
            Servo1.setPosition(.65);
            Servo2.setPosition(.65);
            ServoPos = true;

        }
        if (gamepad2.a && ServoPos == true){
            Servo1.setPosition(.1);
            Servo2.setPosition(.1);
            ServoPos = false;

        }


    }
    @Override
    public void stop(){

    }
}