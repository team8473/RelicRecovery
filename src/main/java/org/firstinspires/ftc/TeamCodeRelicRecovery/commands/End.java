package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;

import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.DRIVE_SPEED;

public class End extends LinearOpMode {

    private Encoders encoders = new Encoders();
    private Grabbers grabbers = new Grabbers();
    private HardwarePhynn phynn = new HardwarePhynn();

    public void runOpMode() throws InterruptedException{

    }

    public void Finish() {
        encoders.encoderDrive(DRIVE_SPEED,8.5, 10);
        grabbers.Open();
        sleep(500);
        encoders.encoderDrive(DRIVE_SPEED,-3.5, 10);
        phynn.See = false;
    }
}
