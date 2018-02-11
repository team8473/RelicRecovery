package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.TeamCodeRelicRecovery.EncoderTemplate;
import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;

import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.DRIVE_SPEED;

public class End extends LinearOpMode {

    EncoderTemplate encoders = new EncoderTemplate();
    Grabbers grabbers = new Grabbers();
    HardwarePhynn phynn = new HardwarePhynn();

    public void runOpMode() throws InterruptedException{

    }

    public void End() {
        encoders.encoderDrive(DRIVE_SPEED,8.5, 10);
        grabbers.Open();
        sleep(500);
        encoders.encoderDrive(DRIVE_SPEED,-3.5, 10);
        phynn.See = false;
    }
}
