package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;

/**
 * Manual control of the lift
 */
public class Lift extends LinearOpMode {

    private HardwarePhynn phynn = new HardwarePhynn();

    @Override
    public void runOpMode() throws InterruptedException {
        phynn.init(hardwareMap);
    }

    public void lift() {
        float lift = gamepad2.left_stick_y;
        lift = Range.clip(-lift, -1, 1);
        phynn.liftMotor.setPower(Range.clip(lift, -.6, .6));
    }
}
