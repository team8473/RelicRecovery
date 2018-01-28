package org.firstinspires.ftc.TeamCodeRelicRecovery;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.Grabbers;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.DRIVE_SPEED;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.LEFT;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.RIGHT;


@Autonomous(name="Blue", group="Gabe")
//@Disabled
public class RelicRecoveryBlue extends LinearOpMode {
    
    private HardwarePhynn phynn = new HardwarePhynn();
    private EncoderTemplate encoders = new EncoderTemplate();
    private Grabbers        grabbers    = new Grabbers();

    
    @Override
    public void runOpMode() throws InterruptedException {

        phynn.init(hardwareMap);


        waitForStart();
        phynn.relicTrackables.activate();

        phynn.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        phynn.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sleep(10);
        phynn.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        phynn.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive()) {

            if (phynn.vuMark != RelicRecoveryVuMark.UNKNOWN && phynn.See) {
                if (phynn.RRRight) {
                    Start();
                    if (phynn.Red_Ball) {
                        Red();
                        encoders.encoderDrive(DRIVE_SPEED, -33.5, 10);
                        encoders.encoderTurn(45, LEFT);
                        End();
                    }else if (phynn.Blue_Ball){
                        Blue();
                        encoders.encoderDrive(DRIVE_SPEED, -38, 10);
                        encoders.encoderTurn(45, LEFT);
                        End();
                    }
                }else if (phynn.RRCenter) {
                    Start();
                    if (phynn.Red_Ball) {
                        Red();
                        encoders.encoderDrive(DRIVE_SPEED, -26, 10);
                        encoders.encoderTurn(45, LEFT);
                        End();
                    }else if (phynn.Blue_Ball){
                        Blue();
                        encoders.encoderDrive(DRIVE_SPEED, -30.5, 10);
                        encoders.encoderTurn(45, LEFT);
                        End();
                    }
                }else if (phynn.RRLeft) {
                    Start();
                        if (phynn.Red_Ball) {
                            Red();
                            encoders.encoderDrive(DRIVE_SPEED,-18, 10);
                            encoders.encoderTurn(45, LEFT);
                            End();
                        }else if (phynn.Blue_Ball){
                            Blue();
                            encoders.encoderDrive(DRIVE_SPEED,-22.5, 10);
                            encoders.encoderTurn(45, LEFT);
                            End();
                        }
                    }
                }
            }
        }
    
    private void Start()
    {
        grabbers.Close();
        phynn.Lift.setPower(.4);
        sleep(1000);
        phynn.Lift.setPower(0);
        phynn.servo3.setPosition(.3);
        sleep(1000);
        telemetry.addData("Blue", phynn.ColorSensor.blue());
        telemetry.addData("Red", phynn.ColorSensor.red());
        telemetry.update();
        sleep(2500);
    }

    private void Red()
    {
        encoders.encoderDrive(DRIVE_SPEED,-4, 10);
        phynn.servo3.setPosition(.85);
    }
    private void Blue()
    {
        encoders.encoderTurn(45, RIGHT);
        phynn.servo3.setPosition(.85);
        encoders.encoderTurn(45, LEFT);

    }
    private void End()
    {
        encoders.encoderDrive(DRIVE_SPEED,8.5, 10);
        grabbers.Open();
        sleep(500);
        encoders.encoderDrive(DRIVE_SPEED,-3.5, 10);
        phynn.See = false;
    }
}
