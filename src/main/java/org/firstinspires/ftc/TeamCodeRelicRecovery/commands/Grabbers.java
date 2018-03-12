package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;

public class Grabbers extends LinearOpMode{

    private HardwarePhynn robot = new HardwarePhynn();

        public void runOpMode(){

        robot.init(hardwareMap);

        }
        public void Half(){
        robot.servo1.setPosition(.45);
        robot.servo2.setPosition(.45);
        }
        public void Open(){
            robot.servo1.setPosition(.25);
            robot.servo2.setPosition(.25);
            robot.Claws_Open = true;
        }
        public void Close(){
            robot.servo1.setPosition(.65);
            robot.servo2.setPosition(.65);
            robot.Claws_Open = false;
        }
    }

