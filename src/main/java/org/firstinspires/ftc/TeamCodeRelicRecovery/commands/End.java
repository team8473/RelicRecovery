package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;

import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.DRIVE_SPEED;

/**
 * The end of every autonomous
 */
public class End extends LinearOpMode {

    private Encoders encoders = new Encoders();
    private Grabbers grabbers = new Grabbers();
    private HardwarePhynn phynn = new HardwarePhynn();

    @Override
    public void runOpMode() throws InterruptedException{
        phynn.init(hardwareMap);
    }

    public void Finish() {
        encoders.encoderDrive(DRIVE_SPEED,8.5);
        grabbers.Open();
        sleep(500);
        encoders.encoderDrive(DRIVE_SPEED,-3.5);
        phynn.see = false;
    }
}
