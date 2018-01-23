package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;


public class Lift extends LinearOpMode {

    HardwarePhynn robot = new HardwarePhynn();

    public void runOpMode(){

        robot.init(hardwareMap);

    }
    public void Lift()
    {
        float lift = gamepad2.left_stick_y;
        lift = Range.clip(-lift, -1, 1);
        robot.Lift.setPower(Range.clip(lift, -.6, .6));
    }
}
