package org.firstinspires.ftc.TeamCodeRelicRecovery;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="EncoderTemplate", group="Gabe")
@Disabled
public class EncoderTemplate extends LinearOpMode {

    //Variables for Encoders
    private HardwarePhynn   robot   = new HardwarePhynn();
    private ElapsedTime     runtime = new ElapsedTime();

    private static final double     COUNTS_PER_MOTOR_REV    = 374 ;      // eg: ANDY MARK Motor Encoder
    private static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    private static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference of the wheel
    private static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    private static final double     DRIVE_SPEED             = 0.25;
    private static final double     TURN_SPEED              = 0.4;
    private static final double     RIGHT                   = 1;
    private static final double     LEFT                    = 0;
    private static final double     ROBOT_SHORT_DIAMETER_IN = 16.35; // Used to find the circumference of the robots ellipse
    private static final double     ROBOT_LONG_DIAMETER_IN  = 17.4;  // Used to find the circumference of the robots ellipse

    @Override
    public void runOpMode() throws InterruptedException {
        
        waitForStart();

        robot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sleep(10);
        robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            encoderTurn(90, RIGHT);
            encoderDrive(DRIVE_SPEED, 15, 15, 15);
            encoderTurn(180, LEFT);

    }


    private void encoderDrive(double speed,
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
            runtime.reset();
            robot.motorRight.setPower(Math.abs(speed));
            robot.motorLeft.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
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
    private void encoderTurn(double turnAngle,
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
