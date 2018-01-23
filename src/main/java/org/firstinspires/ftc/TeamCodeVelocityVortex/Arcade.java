package org.firstinspires.ftc.TeamCodeVelocityVortex;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="ArcadeMode", group="Gabe")
@Disabled
public class Arcade extends OpMode {

    //DcMotorController myShooterController;
    DcMotor myMotorRight;
    DcMotor myMotorLeft;
    ServoController myServoController;
    DcMotorController myIntakeController;
    DcMotorController myShooterController;

    int motorLeft = 1;
    int motorRight = 3;

    int flipperL = 6; //channel 3 (port 3)
    int flipperR = 1;//channel 1 (port 1)
    //int arm = 4;/channel 4 (port 4)
    //int claw = 2;//channel 2 (port 2)

    /**
     * Constructor
     */
    public Arcade() {

    }

    /*
     * Code to run when the op mode is initialized goes here
     *
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#init()
     */
    @Override
    public void init() {

        //myShooterController = hardwareMap.dcMotorController.get ("shooter");
        myMotorRight = hardwareMap.dcMotor.get("aaa");
        myMotorLeft = hardwareMap.dcMotor.get("bbb");
        myServoController = hardwareMap.servoController.get("servos");
        myIntakeController = hardwareMap.dcMotorController.get("intake");
        myShooterController = hardwareMap.dcMotorController.get("shooter");


    }

    @Override
    public void loop() {
        myShooterController.setMotorPower(1, 1);

     /*
      * zipline arm control release loop
      * pressnig the L/R bumper extends the corresponding arm via servo
      */



        float right = gamepad1.right_stick_y;
        float left = gamepad1.left_stick_x;

        right = Range.clip(-right, -1, 1);
        left = Range.clip(-left, -1, 1);

        if (gamepad1.right_bumper){
            myMotorRight.setPower(Range.clip(-left, -.5, .5));
            myMotorLeft.setPower(Range.clip(-right, -.5, .5));
        }
        if (right > 0){
        myMotorRight.setPower(Range.clip(right, -1, -1));
        myMotorLeft.setPower(Range.clip(right, -1, -1));
        }
        else if (left > 0){
            myMotorRight.setPower(Range.clip(-left, -1, 1));
            myMotorLeft.setPower(Range.clip(-left, -1, 1));
        }
        else{
            myMotorRight.setPower(Range.clip(0, -1, 1));
            myMotorLeft.setPower(Range.clip(0, -1, 1));
        }


        if (gamepad2.right_bumper) {
            myIntakeController.setMotorPower(2, 1);
            myShooterController.setMotorPower(2, 1);
        }
        else if (gamepad2.left_bumper) {
            myIntakeController.setMotorPower(2, -1);
            myShooterController.setMotorPower(2, -1);
        }
        else {
            myIntakeController.setMotorPower(2, 0);
            myShooterController.setMotorPower(2, 0);
        }

        if (gamepad2.a) {
            myIntakeController.setMotorPower(1, 1);
        }
        else if (!(gamepad2.a)) {
            myIntakeController.setMotorPower(1, 0);
        }


    }
    @Override
    public void stop(){

    }
}