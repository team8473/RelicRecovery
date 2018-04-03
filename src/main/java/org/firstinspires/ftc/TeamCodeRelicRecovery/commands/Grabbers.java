package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;

import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.GRABBERS_CLOSED;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.GRABBERS_HALF;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.GRABBERS_OPEN;

/**
 * Movements for the grabbers
 */
public class Grabbers extends LinearOpMode{

    private HardwarePhynn robot = new HardwarePhynn();

    @Override
    public void runOpMode(){
        robot.init(hardwareMap);
    }

    public void Half(){
        telemetry.addData("Initialized", null);
        telemetry.update();
        robot.rightGrabber.setPosition(GRABBERS_HALF);
        robot.leftGrabber.setPosition(GRABBERS_HALF);
        telemetry.addData("End", null);
        telemetry.update();
        if(robot.rightGrabber.getPosition() != GRABBERS_HALF || robot.leftGrabber.getPosition() != GRABBERS_HALF) {
            telemetry.addData("Interrupted", null);
            telemetry.update();
        }
    }

    public void Open(){
        telemetry.addData("Initialized", null);
        telemetry.update();
        robot.rightGrabber.setPosition(GRABBERS_OPEN);
        robot.leftGrabber.setPosition(GRABBERS_OPEN);
        robot.clawsOpen = true;
        telemetry.addData("End", null);
        telemetry.update();
        if(robot.rightGrabber.getPosition() != GRABBERS_OPEN || robot.leftGrabber.getPosition() != GRABBERS_OPEN) {
            telemetry.addData("Interrupted", null);
            telemetry.update();
        }
    }

    public void Close(){
        telemetry.addData("Initialized", null);
        telemetry.update();
        robot.rightGrabber.setPosition(GRABBERS_CLOSED);
        robot.leftGrabber.setPosition(GRABBERS_CLOSED);
        robot.clawsOpen = false;
        telemetry.addData("End", null);
        telemetry.update();
        if(robot.rightGrabber.getPosition() != GRABBERS_CLOSED || robot.leftGrabber.getPosition() != GRABBERS_CLOSED) {
            telemetry.addData("Interrupted", null);
            telemetry.update();
        }
    }
}

