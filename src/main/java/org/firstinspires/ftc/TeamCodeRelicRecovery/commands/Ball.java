package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;

import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.Ball.IS_BLUE_BALL;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.Ball.IS_RED_BALL;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.DRIVE_SPEED;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.LEFT;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.RIGHT;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.BLUE;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.RED;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.ball;

/**
 * Knocking the correct ball off in autonomous
 */
@Disabled
public class Ball extends LinearOpMode {

    private HardwarePhynn phynn = new HardwarePhynn();
    private Encoders encoders = new Encoders();

    @Override
    public void runOpMode() throws InterruptedException {
        phynn.init(hardwareMap);
    }

    public void Blue(String alliance) {
        ball = IS_BLUE_BALL;
        switch (alliance) {
            case BLUE:
                encoders.encoderTurn(45, RIGHT);
                phynn.armServo.setPosition(.85);
                encoders.encoderTurn(45, LEFT);
                break;
            case RED:
                encoders.encoderDrive(DRIVE_SPEED, -4);
                phynn.armServo.setPosition(.85);
                break;
            default:
                telemetry.addData("No", "Alliance");
                break;
        }
    }

    public void Red(String alliance) {
        ball = IS_RED_BALL;
        switch (alliance) {
            case BLUE:
                encoders.encoderTurn(45, LEFT);
                phynn.armServo.setPosition(.85);
                encoders.encoderTurn(45, RIGHT);
                break;
            case RED:
                encoders.encoderDrive(DRIVE_SPEED,4);
                phynn.armServo.setPosition(.85);
                break;
            default:
                telemetry.addData("No", "Alliance");
                break;
        }
    }
}
