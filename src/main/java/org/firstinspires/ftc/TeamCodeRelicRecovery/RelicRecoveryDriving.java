package org.firstinspires.ftc.TeamCodeRelicRecovery;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "RelicRecovery", group = "Gabe")

public class RelicRecoveryDriving extends LinearOpMode {
    
    private HardwarePhynn robot = new HardwarePhynn();

    @Override
    public void runOpMode() throws InterruptedException{

        robot.init(hardwareMap);
        
        waitForStart();

        while (opModeIsActive()){
            float right = gamepad1.left_stick_y;
            float left = -gamepad1.right_stick_y;
            float lift = gamepad2.left_stick_y;

            robot.servo3.setPosition(.85);

            left = Range.clip(-left, -1, 1);
            right = Range.clip(-right, -1, 1);
            lift = Range.clip(-lift, -1, 1);

            robot.Lift.setPower(Range.clip(lift, -.6, .6));

            if (gamepad2.a && robot.Open){
                robot.servo1.setPosition(.65);
                robot.servo2.setPosition(.65);
                robot.Open = false;
                sleep(250);
            }
            if (gamepad2.a && !robot.Open){
                robot.servo1.setPosition(.25);
                robot.servo2.setPosition(.25);
                robot.Open = true;
                sleep(250);

            }
            if (gamepad2.b){
                robot.servo1.setPosition(.45);
                robot.servo2.setPosition(.45);

            }
            if (gamepad1.right_bumper && !gamepad2.right_bumper){
                robot.motorRight.setPower(Range.clip(right, -.4, .4));
                robot.motorLeft.setPower(Range.clip(left, -.4, .4));

            }else if(gamepad1.left_bumper && !gamepad2.right_bumper){
                robot.motorRight.setPower(Range.clip(right, -1, 1));
                robot.motorLeft.setPower(Range.clip(left, -1, 1));
            }else if(gamepad2.right_bumper){
                robot.motorRight.setPower(Range.clip(right, 0, 0));
                robot.motorLeft.setPower(Range.clip(left, 0, 0));
            }else if (!gamepad1.right_bumper && !gamepad2.right_bumper){
                robot.motorRight.setPower(Range.clip(right, -.6, .6));
                robot.motorLeft.setPower(Range.clip(left, -.6, .6));
            }

        }

    }


}
