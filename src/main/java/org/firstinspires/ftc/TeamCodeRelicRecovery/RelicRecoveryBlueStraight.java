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

@Autonomous(name="BlueStraight", group="Gabe")
//@Disabled
public class RelicRecoveryBlueStraight extends LinearOpMode {

    private HardwarePhynn   phynn       = new HardwarePhynn();
    private Ball ball        = new Ball();
    private Encoders encoders    = new Encoders();
    private Start start       = new Start();
    private End end         = new End();

    @Override
    public void runOpMode() throws InterruptedException {
        
        phynn.init(hardwareMap);

        waitForStart();

        //Set Motors to use Encoders
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
                        ball.Red("BLUE");
                        encoders.encoderDrive(DRIVE_SPEED, -17.5, 15);
                        encoders.encoderTurn(90, LEFT);
                        encoders.encoderDrive(DRIVE_SPEED, 19, 10);
                        encoders.encoderTurn(90, LEFT);
                        end.Finish();
                    } else if (phynn.Blue_Ball) {
                        ball.Blue("BLUE");
                        encoders.encoderDrive(DRIVE_SPEED, -21, 10);
                        encoders.encoderTurn(90, LEFT);
                        encoders.encoderDrive(DRIVE_SPEED, 22, 10);
                        encoders.encoderTurn(90, LEFT);
                        end.Finish();

                    }

                }else if (phynn.RRCenter) {
                    start.startUp();
                    if (phynn.Red_Ball) {
                        ball.Red("BLUE");
                        encoders.encoderDrive(DRIVE_SPEED, -17.5, 10);
                        encoders.encoderTurn(90, LEFT);
                        encoders.encoderDrive(DRIVE_SPEED, 13,10);
                        encoders.encoderTurn(90, LEFT);
                        end.Finish();
                    } else if (phynn.Blue_Ball) {
                        ball.Blue("BLUE");
                        encoders.encoderDrive(DRIVE_SPEED, -21, 10);
                        encoders.encoderTurn(90, RIGHT);
                        encoders.encoderDrive(DRIVE_SPEED, 14, 10);
                        encoders.encoderTurn(90, RIGHT);
                        end.Finish();

                    }

                }else if (phynn.RRLeft) {
                    start.startUp();
                    if (phynn.Red_Ball) {
                        ball.Red("BLUE");
                        encoders.encoderDrive(DRIVE_SPEED, -17.5, 15);
                        encoders.encoderTurn(90, LEFT);
                        encoders.encoderDrive(DRIVE_SPEED, 6, 10);
                        encoders.encoderTurn(90, LEFT);
                        end.Finish();
                    } else if (phynn.Blue_Ball) {
                        ball.Blue("BLUE");
                        encoders.encoderDrive(DRIVE_SPEED, -21, 15);
                        encoders.encoderTurn(90, LEFT);
                        encoders.encoderDrive(DRIVE_SPEED, 7.5, 10);
                        encoders.encoderTurn(90, LEFT);
                        end.Finish();
                    }
                }
            }
        }
    }
}
