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


@Autonomous(name="Blue", group="Gabe")
//@Disabled
public class RelicRecoveryBlue extends LinearOpMode {
    
    private HardwarePhynn   phynn       = new HardwarePhynn();
    private Ball            ball        = new Ball();
    private Encoders encoders    = new Encoders();
    private Start           start       = new Start();
    private End             end         = new End();

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
                    start.Start();
                    if (phynn.Red_Ball) {
                        ball.Red("BLUE");
                        encoders.encoderDrive(DRIVE_SPEED, -33.5, 10);
                        encoders.encoderTurn(45, LEFT);
                        end.End();
                    }else if (phynn.Blue_Ball){
                        ball.Blue("BLUE");
                        encoders.encoderDrive(DRIVE_SPEED, -38, 10);
                        encoders.encoderTurn(45, LEFT);
                        end.End();
                    }
                }else if (phynn.RRCenter) {
                    start.Start();
                    if (phynn.Red_Ball) {
                        ball.Red("BLUE");
                        encoders.encoderDrive(DRIVE_SPEED, -26, 10);
                        encoders.encoderTurn(45, LEFT);
                        end.End();
                    }else if (phynn.Blue_Ball){
                        ball.Blue("BLUE");
                        encoders.encoderDrive(DRIVE_SPEED, -30.5, 10);
                        encoders.encoderTurn(45, LEFT);
                        end.End();
                    }
                }else if (phynn.RRLeft) {
                    start.Start();
                        if (phynn.Red_Ball) {
                            ball.Red("BLUE");
                            encoders.encoderDrive(DRIVE_SPEED,-18, 10);
                            encoders.encoderTurn(45, LEFT);
                            end.End();
                        }else if (phynn.Blue_Ball){
                            ball.Blue("BLUE");
                            encoders.encoderDrive(DRIVE_SPEED,-22.5, 10);
                            encoders.encoderTurn(45, LEFT);
                            end.End();
                        }
                    }
                }
            }
        }
    }
