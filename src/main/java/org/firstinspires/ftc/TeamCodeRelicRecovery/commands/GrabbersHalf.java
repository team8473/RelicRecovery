package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;


public class GrabbersHalf extends LinearOpMode{

    private HardwarePhynn robot = new HardwarePhynn();

    public void runOpMode(){

        robot.init(hardwareMap);

    }
    public void Half(){

        robot.servo1.setPosition(.45);
        robot.servo2.setPosition(.45);

    }
}
