package org.firstinspires.ftc.TeamCodeRelicRecovery;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.Ball;
import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.End;
import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.Start;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.DRIVE_SPEED;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.LEFT;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.RIGHT;

@Autonomous(name="Red", group="Gabe")
//@Disabled
public class RelicRecoveryRed extends LinearOpMode {

    private Start start = new Start();
    private End end = new End();
    private HardwarePhynn phynn = new HardwarePhynn();
    private EncoderTemplate encoders = new EncoderTemplate();
    private Ball ball = new Ball();


    @Override
    public void runOpMode() throws InterruptedException {

        phynn.init(hardwareMap);


        waitForStart();

        phynn.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        phynn.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sleep(10);
        phynn.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        phynn.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        while (opModeIsActive()) {
            if (phynn.vuMark != RelicRecoveryVuMark.UNKNOWN && phynn.See) {
                if (phynn.RRCenter) {
                    start.Start();
                    if (phynn.Red_Ball) {
                        ball.Red("RED");
                        encoders.encoderDrive(DRIVE_SPEED, 35.5, 10);
                        encoders.encoderTurn(90, RIGHT); //90 degree turn
                        end.End();
                    }else if (phynn.Blue_Ball) {
                        ball.Blue("RED");
                        encoders.encoderDrive(DRIVE_SPEED, 37, 10);
                        encoders.encoderTurn(90, RIGHT); //90 degree turn
                        end.End();
                    }
                }else if (phynn.RRRight) {
                    start.Start();
                    if (phynn.Red_Ball) {
                        ball.Red("RED");
                        encoders.encoderDrive(DRIVE_SPEED, 27.5, 0);
                        encoders.encoderTurn(90, LEFT); //90 degree turn
                        end.End();
                    }else if (phynn.Blue_Ball) {
                        ball.Blue("RED");
                        encoders.encoderDrive(DRIVE_SPEED, 28.5, 10);
                        encoders.encoderTurn(90, LEFT); //90 degree turn
                        end.End();
                    }
                }else if (phynn.RRLeft) {
                    start.Start();
                    if (phynn.Red_Ball) {
                        ball.Red("RED");
                        encoders.encoderDrive(DRIVE_SPEED, 43.5, 12);
                        encoders.encoderTurn(90, LEFT); //90 degree turn
                        end.End();
                    }else if (phynn.Blue_Ball) {
                        ball.Blue("RED");
                        encoders.encoderDrive(DRIVE_SPEED, 46, 10);
                        encoders.encoderTurn(90, LEFT); //90 degree turn
                        end.End();
                    }
                }
            }
        }
    }
}
