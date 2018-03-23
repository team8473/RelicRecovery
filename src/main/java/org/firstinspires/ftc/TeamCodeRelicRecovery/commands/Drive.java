package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;

public class Drive extends LinearOpMode{

    private HardwarePhynn phynn = new HardwarePhynn();

    public void runOpMode(){

        phynn.init(hardwareMap);

    }
    public void drive(){

        float right = gamepad1.left_stick_y;
        float left = -gamepad1.right_stick_y;

        left = Range.clip(-left, -1, 1);
        right = Range.clip(-right, -1, 1);

        phynn.motorRight.setPower(Range.clip(right, -.6, .6));
        phynn.motorLeft.setPower(Range.clip(left, -.6, .6));

    }
    public void stopDrive(){

        float right = gamepad1.left_stick_y;
        float left = -gamepad1.right_stick_y;

        left = Range.clip(-left, -1, 1);
        right = Range.clip(-right, -1, 1);

        phynn.motorRight.setPower(Range.clip(right, -0, 0));
        phynn.motorLeft.setPower(Range.clip(left, -0, 0));

    }
    public void fastDrive(){

        float right = gamepad1.left_stick_y;
        float left = -gamepad1.right_stick_y;

        left = Range.clip(-left, -1, 1);
        right = Range.clip(-right, -1, 1);

        phynn.motorRight.setPower(Range.clip(right, -1, 1));
        phynn.motorLeft.setPower(Range.clip(left, -1, 1));

    }
    public void slowDrive(){

        float right = gamepad1.left_stick_y;
        float left = -gamepad1.right_stick_y;

        left = Range.clip(-left, -1, 1);
        right = Range.clip(-right, -1, 1);

        phynn.motorRight.setPower(Range.clip(right, -.4, .4));
        phynn.motorLeft.setPower(Range.clip(left, -.4, .4));
    }
}
