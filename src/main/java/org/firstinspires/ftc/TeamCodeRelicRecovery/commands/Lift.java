package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;


public class Lift extends LinearOpMode {

    private HardwarePhynn phynn = new HardwarePhynn();

    public void runOpMode(){

        phynn.init(hardwareMap);

    }
    public void lift()
    {
        float lift = gamepad2.left_stick_y;
        lift = Range.clip(-lift, -1, 1);
        phynn.Lift.setPower(Range.clip(lift, -.6, .6));
    }
}
