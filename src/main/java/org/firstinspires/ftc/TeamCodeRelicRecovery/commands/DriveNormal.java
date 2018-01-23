package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;

public class DriveNormal extends LinearOpMode{

    private HardwarePhynn robot = new HardwarePhynn();

    public void runOpMode(){

        robot.init(hardwareMap);

    }
    public void normalDrive(){

        float right = gamepad1.left_stick_y;
        float left = -gamepad1.right_stick_y;

        left = Range.clip(-left, -1, 1);
        right = Range.clip(-right, -1, 1);

        robot.motorRight.setPower(Range.clip(right, -.6, .6));
        robot.motorLeft.setPower(Range.clip(left, -.6, .6));

    }
}
