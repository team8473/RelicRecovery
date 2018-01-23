package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;


public class GrabbersOpen extends LinearOpMode{

    private HardwarePhynn robot = new HardwarePhynn();

    public void runOpMode(){

        robot.init(hardwareMap);

    }
    public void Open(){

        robot.servo1.setPosition(.25);
        robot.servo2.setPosition(.25);
        robot.Open = true;

    }
}
