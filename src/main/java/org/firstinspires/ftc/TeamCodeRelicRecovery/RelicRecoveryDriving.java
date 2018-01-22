package org.firstinspires.ftc.TeamCodeRelicRecovery;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "RelicRecovery", group = "Gabe")

public class RelicRecoveryDriving extends LinearOpMode {

    private double out = .1;
    private double ServoPos = out;

    @Override
    public void runOpMode() throws InterruptedException{

        DcMotor motorRight = hardwareMap.dcMotor.get("front right");
        DcMotor motorLeft = hardwareMap.dcMotor.get("front left");
        Servo servo1 = hardwareMap.servo.get("servo1");
        Servo servo2 = hardwareMap.servo.get("servo2");
        Servo servo3 = hardwareMap.servo.get("servo3");
        DcMotor lift1 = hardwareMap.dcMotor.get("lift");

        servo2.setDirection(Servo.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()){
            double in = .65;

            float right = gamepad1.left_stick_y;
            float left = -gamepad1.right_stick_y;
            float lift = gamepad2.left_stick_y;

            servo3.setPosition(.85);

            left = Range.clip(-left, -1, 1);
            right = Range.clip(-right, -1, 1);
            lift = Range.clip(-lift, -1, 1);

            lift1.setPower(Range.clip(lift, -.6, .6));

            if (gamepad2.a && ServoPos == out){
                servo1.setPosition(.65);
                servo2.setPosition(.65);
                ServoPos = in;
                sleep(250);
            }
            if (gamepad2.a && ServoPos == in){
                servo1.setPosition(.25);
                servo2.setPosition(.25);
                ServoPos = out;
                sleep(250);

            }
            if (gamepad2.b){
                servo1.setPosition(.45);
                servo2.setPosition(.45);

            }
            if (gamepad1.right_bumper && !gamepad2.right_bumper){
                motorRight.setPower(Range.clip(right, -.4, .4));
                motorLeft.setPower(Range.clip(left, -.4, .4));

            }else if(gamepad1.left_bumper && !gamepad2.right_bumper){
                motorRight.setPower(Range.clip(right, -1, 1));
                motorLeft.setPower(Range.clip(left, -1, 1));
            }else if(gamepad2.right_bumper){
                motorRight.setPower(Range.clip(right, 0, 0));
                motorLeft.setPower(Range.clip(left, 0, 0));
            }else if (!gamepad1.right_bumper && !gamepad2.right_bumper){
                motorRight.setPower(Range.clip(right, -.6, .6));
                motorLeft.setPower(Range.clip(left, -.6, .6));
            }

        }

    }


}
