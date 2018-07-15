package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;

import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.DOWN;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.MAX_POS;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.MIN_POS;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.UP;

/**
 * Manual control of the lift
 */
@Disabled
public class Lift extends LinearOpMode {

    private HardwarePhynn phynn = new HardwarePhynn();

    private int currentPreset = 0;
    private int liftZero = phynn.liftMotor.getCurrentPosition();
    private  static Position[] positionFromPreset = new Position[]{Position.BOTTOM, Position.MIDDLE, Position.TOP};

    @Override
    public void runOpMode() throws InterruptedException {
        phynn.init(hardwareMap);
    }

    public void lift() {
        float lift = gamepad2.left_stick_y;
        lift = Range.clip(-lift, -1, 1);
        phynn.liftMotor.setPower(Range.clip(lift, -.6, .6));
    }

    private int safetyCheck(int position) {
        return Math.max(Math.min(MAX_POS, position), MIN_POS);
    }

    private void setPosition(Position position) {
        phynn.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        switch (position) {
            case BOTTOM:
                telemetry.addData("Bottom", "Position");
                telemetry.update();
                int BOTTOM_POS = 0;
                int currentGoalPosition = BOTTOM_POS - liftZero;
                setMotorPosition(safetyCheck(currentGoalPosition));
                break;
            case MIDDLE:
                telemetry.addData("Middle", "Position");
                telemetry.update();
                int MIDDLE_POS = 0;
                currentGoalPosition = MIDDLE_POS - liftZero;
                setMotorPosition(safetyCheck(currentGoalPosition));
                break;
            case TOP:
                telemetry.addData("Top", "Position");
                telemetry.update();
                int TOP_POS = 0;
                currentGoalPosition = TOP_POS - liftZero;
                setMotorPosition(safetyCheck(currentGoalPosition));
                break;
            default:
                break;
        }
    }

    public void cycleLift(int direction) {
        switch (direction) {
            case UP:
                currentPreset = Math.min(currentPreset + 1, positionFromPreset.length - 1);
                setPosition(positionFromPreset[currentPreset]);
                break;
            case DOWN:
                currentPreset = Math.max(currentPreset - 1, 0);
                setPosition(positionFromPreset[currentPreset]);
                break;
            default:
                break;
        }
    }

    private void setMotorPosition(int position) {
        phynn.liftMotor.setTargetPosition(position);

        phynn.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        phynn.liftMotor.setPower(0.6);

        while(opModeIsActive() && phynn.liftMotor.isBusy()) {
            telemetry.addData("Lifting", phynn.liftMotor.getCurrentPosition());
            telemetry.update();
        }

        phynn.liftMotor.setPower(0.0);

        phynn.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public enum Position {
        BOTTOM, MIDDLE, TOP
    }
}
