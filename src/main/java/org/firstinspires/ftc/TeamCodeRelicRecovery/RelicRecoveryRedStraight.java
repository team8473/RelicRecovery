package org.firstinspires.ftc.TeamCodeRelicRecovery;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.Ball;
import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.Encoders;
import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.End;
import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.Start;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.DRIVE_SPEED;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.LEFT;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.RIGHT;

@Autonomous(name="RedStraight", group="Gabe")
//@Disabled
public class RelicRecoveryRedStraight extends LinearOpMode {

    private Start start = new Start();
    private End end = new End();
    private HardwarePhynn phynn = new HardwarePhynn();
    private Encoders encoders = new Encoders();
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
                if (phynn.RRRight) {
                    start.startUp();
                    if (phynn.Red_Ball) {
                        ball.Red("RED");
                        encoders.encoderDrive(DRIVE_SPEED, 22.5,10);
                        encoders.encoderTurn(90, LEFT);
                        encoders.encoderDrive(DRIVE_SPEED, 6,10);
                        encoders.encoderTurn(90, RIGHT);
                        end.Finish();
                    } else if (phynn.Blue_Ball) {
                        ball.Blue("RED");
                        encoders.encoderDrive(DRIVE_SPEED, 26,10);
                        encoders.encoderTurn(90, LEFT);
                        encoders.encoderDrive(DRIVE_SPEED, 7, 10);
                        encoders.encoderTurn(90, RIGHT);
                        end.Finish();

                    }

                }else if (phynn.RRCenter) {
                    start.startUp();
                    if (phynn.Red_Ball) {
                        ball.Red("RED");
                        encoders.encoderDrive(DRIVE_SPEED, 22.5,10);
                        encoders.encoderTurn(90, LEFT);
                        encoders.encoderDrive(DRIVE_SPEED, 15, 10);
                        encoders.encoderTurn(90, RIGHT);
                        end.Finish();
                    } else if (phynn.Blue_Ball) {
                        ball.Blue("RED");
                        encoders.encoderDrive(DRIVE_SPEED, 26,10);
                        encoders.encoderTurn(90, LEFT);
                        encoders.encoderDrive(DRIVE_SPEED, 15,10);
                        encoders.encoderTurn(90, RIGHT);
                        end.Finish();

                    }

                }else if (phynn.RRLeft) {
                    start.startUp();
                    if (phynn.Red_Ball) {
                        ball.Red("RED");
                        encoders.encoderDrive(DRIVE_SPEED, 22.5,10);
                        encoders.encoderTurn(90, LEFT);
                        encoders.encoderDrive(DRIVE_SPEED, 21.5, 10);
                        encoders.encoderTurn(90, RIGHT);
                        end.Finish();
                    } else if (phynn.Blue_Ball) {
                        ball.Blue("RED");
                        encoders.encoderDrive(DRIVE_SPEED, 26,15);
                        encoders.encoderTurn(90, LEFT);
                        encoders.encoderDrive(DRIVE_SPEED, 23.5,0);
                        encoders.encoderTurn(90, RIGHT);
                        end.Finish();

                    }
                }
            }
        }
    }
}

