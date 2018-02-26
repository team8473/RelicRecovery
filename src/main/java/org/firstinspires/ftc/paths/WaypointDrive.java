package org.firstinspires.ftc.paths;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;

import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.COUNTS_PER_INCH;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.LEFT;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.RIGHT;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.TURN_SPEED;


@Autonomous(name="Encoders", group="Gabe")
@Disabled
public class WaypointDrive extends LinearOpMode {

    //Variables for Encoders
    private HardwarePhynn phynn = new HardwarePhynn();

    @Override
    public void runOpMode() throws InterruptedException {

        phynn.init(hardwareMap);

    }


    public void drive(double leftSpeed, double rightSpeed,
                             double leftDistance, double rightDistance,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        if (opModeIsActive()) {
            newLeftTarget = phynn.motorLeft.getCurrentPosition() + (int)(leftDistance * COUNTS_PER_INCH);
            newRightTarget = phynn.motorRight.getCurrentPosition() + (int)(rightDistance * COUNTS_PER_INCH);

            phynn.motorRight.setTargetPosition(newRightTarget);
            phynn.motorLeft.setTargetPosition(newLeftTarget);

            phynn.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            phynn.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            phynn.runtime.reset();
            phynn.motorRight.setPower(Math.abs(rightSpeed));
            phynn.motorLeft.setPower(Math.abs(leftSpeed));

            while (opModeIsActive() &&
                    (phynn.runtime.seconds() < timeoutS) &&
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
}
