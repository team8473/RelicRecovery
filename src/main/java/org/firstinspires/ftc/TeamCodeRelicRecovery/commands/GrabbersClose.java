package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;


public class GrabbersClose extends LinearOpMode {

    private HardwarePhynn robot = new HardwarePhynn();

    public void runOpMode(){

        robot.init(hardwareMap);

    }
    public void Close(){

        robot.servo1.setPosition(.65);
        robot.servo2.setPosition(.65);
        robot.Open = false;

    }
}
