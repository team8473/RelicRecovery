package org.firstinspires.ftc.TeamCodeRelicRecovery;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.Ball;
import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.Encoders;
import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.End;
import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.Start;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.paths.RedCenter;

import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.DRIVE_SPEED;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.LEFT;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.RED;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.RIGHT;

@Autonomous(name="Red_Turn", group="Gabe")
//@Disabled
public class RelicRecoveryRed extends LinearOpMode {

    private Start start = new Start();
    private End end = new End();
    private HardwarePhynn phynn = new HardwarePhynn();
    private Encoders encoders = new Encoders();
    private Ball balls = new Ball();


    @Override
    public void runOpMode() throws InterruptedException {

        phynn.init(hardwareMap);

        RedCenter center = new RedCenter();

        waitForStart();

        phynn.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        phynn.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sleep(10);
        phynn.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        phynn.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        while (opModeIsActive()) {
            if (phynn.vuMark != RelicRecoveryVuMark.UNKNOWN && phynn.See) {
                if (phynn.RRCenter) {
                    start.startUp();
                    if (phynn.Red_Ball) {
                        balls.Red(RED);
                        center.buildPath();
                        end.Finish();
                    }else if (phynn.Blue_Ball) {
                        balls.Blue(RED);
                        encoders.encoderDrive(DRIVE_SPEED, 37, 10);
                        encoders.encoderTurn(90, RIGHT); //90 degree turn
                        end.Finish();
                    }
                }else if (phynn.RRRight) {
                    start.startUp();
                    if (phynn.Red_Ball) {
                        balls.Red(RED);
                        encoders.encoderDrive(DRIVE_SPEED, 27.5, 0);
                        encoders.encoderTurn(90, LEFT); //90 degree turn
                        end.Finish();
                    }else if (phynn.Blue_Ball) {
                        balls.Blue(RED);
                        encoders.encoderDrive(DRIVE_SPEED, 28.5, 10);
                        encoders.encoderTurn(90, LEFT); //90 degree turn
                        end.Finish();
                    }
                }else if (phynn.RRLeft) {
                    start.startUp();
                    if (phynn.Red_Ball) {
                        balls.Red(RED);
                        encoders.encoderDrive(DRIVE_SPEED, 43.5, 12);
                        encoders.encoderTurn(90, LEFT); //90 degree turn
                        end.Finish();
                    }else if (phynn.Blue_Ball) {
                        balls.Blue(RED);
                        encoders.encoderDrive(DRIVE_SPEED, 46, 10);
                        encoders.encoderTurn(90, LEFT); //90 degree turn
                        end.Finish();
                    }
                }
            }
        }
    }
}
