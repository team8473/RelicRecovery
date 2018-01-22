package org.firstinspires.ftc.TeamCodeRelicRecovery;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="Test2", group="Gabe")
public class Test2 extends LinearOpMode {

    private DcMotor MotorRight;
    private DcMotor MotorLeft;

    //Variables for Encoders
    private ElapsedTime     runtime = new ElapsedTime();

    private static final double     COUNTS_PER_MOTOR_REV    = 380 ;    // eg: ANDY MARK Motor Encoder
    private static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    private static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    private static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    private static final double     DRIVE_SPEED             = .2;
    private static final double     TURN_SPEED              = 0.4;
    private static final double     ROBOT_SHORT_DIAMETER_IN = 16.3;
    private static final double     ROBOT_LONG_DIAMETER_IN  = 17.4;
    private static final double     RIGHT                   = 1;
    private static final double     LEFT                    = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        MotorRight = hardwareMap.dcMotor.get("front right");
        MotorLeft = hardwareMap.dcMotor.get("front left");

        MotorLeft.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();

        //Set Motors to use Encoders
        MotorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MotorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sleep(10);

        MotorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


            encoderDrive(TURN_SPEED, 30, 30, 100);
            encoderTurn(90, RIGHT);
            encoderTurn(360, LEFT);


        //encoderDrive(Speed, distance in inches for left, distance in inces for right, time in seconds to stop)
        // Ex: encoderDrive(TURN_SPEED, -34, 34, 10.0); move 34 inches forward
    }


    private void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;
        double newSpeed;
        int distance;
        int distanceTraveled;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = MotorRight.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = MotorLeft.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            distance = newLeftTarget - MotorRight.getCurrentPosition();
            distanceTraveled = newLeftTarget - distance;
            newSpeed = distance / (speed + (leftInches * COUNTS_PER_INCH) + distanceTraveled);
            MotorRight.setTargetPosition(newLeftTarget);
            MotorLeft.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            MotorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            MotorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            MotorRight.setPower(Math.abs(speed));
            MotorLeft.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (MotorRight.isBusy() && MotorLeft.isBusy()) && speed == DRIVE_SPEED) {
                if (newSpeed > .3) {
                    distance = newLeftTarget - MotorRight.getCurrentPosition();
                    distanceTraveled = newLeftTarget - distance;
                    newSpeed = distance / (speed + (leftInches * COUNTS_PER_INCH) + distanceTraveled);
                    MotorRight.setPower(Math.abs(newSpeed));
                    MotorLeft.setPower(Math.abs(newSpeed));
                } else if (newSpeed <= .3){
                    MotorRight.setPower(.25);
                    MotorLeft.setPower(.25);
                }

                telemetry.addData("Motor1", MotorRight.getCurrentPosition());
                telemetry.addData("Motor2", MotorLeft.getCurrentPosition());
                telemetry.update();

            }
            while(opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (MotorRight.isBusy() && MotorLeft.isBusy()) && speed == TURN_SPEED){

                telemetry.addData("Motor1", MotorRight.getCurrentPosition());
                telemetry.addData("Motor2", MotorLeft.getCurrentPosition());
                telemetry.update();

            }

            // Stop all motion;
            MotorRight.setPower(0);
            MotorLeft.setPower(0);

            // Turn off RUN_TO_POSITION
            MotorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            MotorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }

    }
    private void encoderTurn(double turnAngle,
                             double direction){
        double inches;
        double circleFraction;
        double robotCircumference;
        double shortSquared = Math.pow(.5 * ROBOT_SHORT_DIAMETER_IN, 2);
        double longSquared = Math.pow(.5 * ROBOT_LONG_DIAMETER_IN, 2);
        double sqRoot = Math.sqrt(2 * (shortSquared + longSquared));

        if (opModeIsActive() && direction == RIGHT)
        {
            circleFraction = turnAngle / 360;
            robotCircumference = (3.1415 * sqRoot);
            inches = ((circleFraction * robotCircumference));
            encoderDrive(TURN_SPEED, inches, -inches, 10);

        }else if (opModeIsActive() && direction == LEFT)
        {
            circleFraction = turnAngle / 360;
            robotCircumference = (3.1415 * sqRoot);
            inches = ((circleFraction * robotCircumference));
            encoderDrive(TURN_SPEED, -inches, inches, 10);
        }
    }

}
