package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;

import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.DRIVE_SPEED;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.LEFT;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.RIGHT;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.BLUE;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.RED;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.ball;

public class Ball extends LinearOpMode {

    private HardwarePhynn phynn = new HardwarePhynn();
    private Encoders encoders = new Encoders();

    @Override
    public void runOpMode() throws InterruptedException{

    }
    public void Blue(String alliance) {
        ball = HardwarePhynn.Ball.IS_BLUE_BALL;
        switch (alliance) {
            case BLUE :
                encoders.encoderTurn(45, RIGHT);
                phynn.servo3.setPosition(.85);
                encoders.encoderTurn(45, LEFT);
                break;
            case RED :
                encoders.encoderDrive(DRIVE_SPEED, -4, 10);
                phynn.servo3.setPosition(.85);
                break;
            default:
                telemetry.addData("No", "Alliance");
                break;
        }
    }

    public void Red(String alliance) {
        ball = HardwarePhynn.Ball.IS_RED_BALL;
        switch (alliance) {
            case BLUE :
                encoders.encoderTurn(45, LEFT);
                phynn.servo3.setPosition(.85);
                encoders.encoderTurn(45, RIGHT);
                break;
            case RED :
                encoders.encoderDrive(DRIVE_SPEED,4, 10);
                phynn.servo3.setPosition(.85);
                break;
            default :
                telemetry.addData("No", "Alliance");
                break;
        }
    }
}
