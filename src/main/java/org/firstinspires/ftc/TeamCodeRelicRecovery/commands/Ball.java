package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;
import org.firstinspires.ftc.TeamCodeRelicRecovery.RelicRecoveryDriving;

import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.DRIVE_SPEED;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.LEFT;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.RIGHT;

public class Ball extends LinearOpMode {

    HardwarePhynn phynn = new HardwarePhynn();
    Encoders encoders = new Encoders();
    RelicRecoveryDriving drive = new RelicRecoveryDriving();
    Encoders encoder = new Encoders();


    @Override
    public void runOpMode() throws InterruptedException{

    }
    public String Blue(String msg) {
        switch (msg) {
            case "BLUE":
                telemetry.addData("Is", "Blue");
                encoders.encoderTurn(45, RIGHT);
                phynn.servo3.setPosition(.85);
                encoders.encoderTurn(45, LEFT);
                break;
            case "RED":
                encoders.encoderDrive(DRIVE_SPEED, -4, 10);
                phynn.servo3.setPosition(.85);
                break;
            default:
                telemetry.addData("No", "Alliance");
                break;
        }
        return msg;
    }

    public String Red(String msg) {
        switch(msg) {
            case "BLUE" :
                encoders.encoderTurn(45, LEFT);
                phynn.servo3.setPosition(.85);
                encoders.encoderTurn(45, RIGHT);
                break;
            case "RED" :
                encoders.encoderDrive(DRIVE_SPEED,4, 10);
                phynn.servo3.setPosition(.85);
                break;
            default :
                telemetry.addData("No", "Alliance");
                break;
        }
        return msg;
    }
}
