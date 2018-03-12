package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;

public class Start extends LinearOpMode {

    private HardwarePhynn phynn = new HardwarePhynn();
    private Grabbers grabbers = new Grabbers();

    public void runOpMode() throws InterruptedException{
    }

    public void startUp(){
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
}
