package org.firstinspires.ftc.TeamCodeRelicRecovery;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.COUNTS_PER_INCH;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.LEFT;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.RIGHT;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.ROBOT_LONG_DIAMETER_IN;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.ROBOT_SHORT_DIAMETER_IN;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.TURN_SPEED;


@Autonomous(name="EncoderTemplate", group="Gabe")
@Disabled
public class EncoderTemplate extends LinearOpMode {

    //Variables for Encoders
    private HardwarePhynn   robot   = new HardwarePhynn();

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);

    }


    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.motorRight.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.motorLeft.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            robot.motorRight.setTargetPosition(newLeftTarget);
            robot.motorLeft.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            robot.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            robot.runtime.reset();
            robot.motorRight.setPower(Math.abs(speed));
            robot.motorLeft.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opModeIsActive() &&
                    (robot.runtime.seconds() < timeoutS) &&
                    (robot.motorRight.isBusy() && robot.motorLeft.isBusy())) {

                telemetry.addData("Motor1", robot.motorRight.getCurrentPosition());
                telemetry.addData("Motor2", robot.motorLeft.getCurrentPosition());
                telemetry.update();

            }

            // Stop all motion;
            robot.motorRight.setPower(0);
            robot.motorLeft.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }

    }
    public void encoderTurn(double turnAngle,
                             double direction)
    {

        double shortSquared = Math.pow(.5 * ROBOT_SHORT_DIAMETER_IN, 2);
        double longSquared = Math.pow(.5 * ROBOT_LONG_DIAMETER_IN, 2);
        double sqRoot = Math.sqrt(2 * (shortSquared + longSquared));
        double circleFraction = turnAngle / 360;
        double robotCircumference = (3.1415 * sqRoot);
        double inches = (circleFraction * robotCircumference);

        if (opModeIsActive() && direction == RIGHT)
        {
            encoderDrive(TURN_SPEED, inches, -inches, 10);

        }else if (opModeIsActive() && direction == LEFT)
        {
            encoderDrive(TURN_SPEED, -inches, inches, 10);
        }
    }

}
