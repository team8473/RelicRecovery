package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;

import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.DRIVE_SPEED;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.FAST_DRIVE_SPEED;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.SLOW_DRIVE_SPEED;

/**
 * Driving the robot
 */
@Disabled
public class Drive extends LinearOpMode{

    private HardwarePhynn phynn = new HardwarePhynn();

    @Override
    public void runOpMode() throws InterruptedException {
        phynn.init(hardwareMap);
    }

    public void drive(){
        float right = gamepad1.left_stick_y;
        float left = -gamepad1.right_stick_y;

        left = Range.clip(-left, -1, 1);
        right = Range.clip(-right, -1, 1);

        phynn.motorRight.setPower(Range.clip(right, -DRIVE_SPEED, DRIVE_SPEED));
        phynn.motorLeft.setPower(Range.clip(left, -DRIVE_SPEED, DRIVE_SPEED));
    }

    public void stopDrive(){
        phynn.motorRight.setPower(0.0);
        phynn.motorLeft.setPower(0.0);
    }

    public void fastDrive(){
        float right = gamepad1.left_stick_y;
        float left = -gamepad1.right_stick_y;

        left = Range.clip(-left, -1, 1);
        right = Range.clip(-right, -1, 1);

        phynn.motorRight.setPower(Range.clip(right, -FAST_DRIVE_SPEED, FAST_DRIVE_SPEED));
        phynn.motorLeft.setPower(Range.clip(left, -FAST_DRIVE_SPEED, FAST_DRIVE_SPEED));
    }

    public void slowDrive(){
        float right = gamepad1.left_stick_y;
        float left = -gamepad1.right_stick_y;

        left = Range.clip(-left, -1, 1);
        right = Range.clip(-right, -1, 1);

        phynn.motorRight.setPower(Range.clip(right, -SLOW_DRIVE_SPEED, SLOW_DRIVE_SPEED));
        phynn.motorLeft.setPower(Range.clip(left, -SLOW_DRIVE_SPEED, SLOW_DRIVE_SPEED));
    }
}
