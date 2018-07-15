package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;

/**
 * The start of every autonomous mode
 */
@Disabled
public class Start extends LinearOpMode {

    private HardwarePhynn phynn = new HardwarePhynn();
    private Grabbers grabbers = new Grabbers();

    @Override
    public void runOpMode() throws InterruptedException{
    }

    public void startUp(){
        grabbers.Close();
        phynn.liftMotor.setPower(.4);
        sleep(1000);
        phynn.liftMotor.setPower(0);
        phynn.armServo.setPosition(.3);
        sleep(1000);
        telemetry.addData("Blue", phynn.colorSensor.blue());
        telemetry.addData("Red", phynn.colorSensor.red());
        telemetry.update();
        sleep(2500);
    }
}
