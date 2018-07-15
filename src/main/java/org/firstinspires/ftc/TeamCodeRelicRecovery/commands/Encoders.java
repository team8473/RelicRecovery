package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;

import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.COUNTS_PER_INCH;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.ROBOT_CIRCUMFERENCE;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.LEFT;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.RIGHT;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.TURN_SPEED;

/**
 * Code for using encoders
 */
@Autonomous(name="Encoders", group="Gabe")
@Disabled
public class Encoders extends LinearOpMode {

    private HardwarePhynn phynn = new HardwarePhynn();
    private ElapsedTime timer = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        phynn.init(hardwareMap);
    }

    public void encoderDrive(double speed,
                             double distance) {
        int newTarget;

        if (opModeIsActive()) {
            newTarget = phynn.motorLeft.getCurrentPosition() + (int)(distance * COUNTS_PER_INCH);

            if (speed == TURN_SPEED) {
                phynn.motorRight.setTargetPosition(newTarget);
                phynn.motorLeft.setTargetPosition(-newTarget);
            } else if (speed == -TURN_SPEED) {
                phynn.motorRight.setTargetPosition(-newTarget);
                phynn.motorLeft.setTargetPosition(newTarget);
            } else {
                phynn.motorRight.setTargetPosition(newTarget);
                phynn.motorLeft.setTargetPosition(newTarget);
            }

            phynn.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            phynn.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            timer.reset();
            phynn.motorRight.setPower(Math.abs(speed));
            phynn.motorLeft.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (timer.seconds() < 10) &&
                    (phynn.motorRight.isBusy() && phynn.motorLeft.isBusy())) {

                telemetry.addData("Motor1", phynn.motorRight.getCurrentPosition());
                telemetry.addData("Motor2", phynn.motorLeft.getCurrentPosition());
                telemetry.update();

            }
            phynn.motorRight.setPower(0);
            phynn.motorLeft.setPower(0);

            phynn.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            phynn.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
    public void encoderTurn(double turnAngle,
                            String direction) {
        double circleFraction = turnAngle / 360;
        double inches = (circleFraction * ROBOT_CIRCUMFERENCE);

        switch (direction) {
            case RIGHT :
                encoderDrive(TURN_SPEED, inches);
                break;
            case LEFT :
                encoderDrive(-TURN_SPEED, inches);
                break;
        }
    }
}