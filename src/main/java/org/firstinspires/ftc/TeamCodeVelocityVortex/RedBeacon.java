package org.firstinspires.ftc.TeamCodeVelocityVortex;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by NewUser on 9/5/2016.
 */

@Autonomous(name="RedCapFar", group="Gabe")
@Disabled
public class RedBeacon extends LinearOpMode {

    public enum ColorSensorDevice {ADAFRUIT, HITECHNIC_NXT, MODERN_ROBOTICS_I2C}

    ;

    public ColorSensorDevice device = ColorSensorDevice.MODERN_ROBOTICS_I2C;

    //Define controllers
    DcMotor myMotorRight;
    DcMotor myMotorLeft;
    DcMotorController myShooterController;
    DcMotorController myIntakeController;

    ServoController myServoController;
    //TouchSensor myTouchSenor;
    ColorSensor myColorSensor;
    DeviceInterfaceModule cdim;
    LED led;

    //Variables for Encoders
    private ElapsedTime     runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 374 ;    // eg: ANDY MARK Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.25;
    static final double     TURN_SPEED              = 0.4;

    @Override
    public void runOpMode() throws InterruptedException {

        myMotorRight = hardwareMap.dcMotor.get ("aaa");
        myMotorLeft = hardwareMap.dcMotor.get ("bbb");
        myShooterController = hardwareMap.dcMotorController.get("shooter");
        myIntakeController= hardwareMap.dcMotorController.get("intake");
        myServoController = hardwareMap.servoController.get("servos");
        cdim = hardwareMap.deviceInterfaceModule.get("Device Interface Module 1");

        myMotorLeft.setDirection(DcMotor.Direction.REVERSE);

        switch (device) {
            case MODERN_ROBOTICS_I2C:
                myColorSensor = hardwareMap.colorSensor.get("mr");
                break;
        }

        waitForStart();

        //Set Motors to use Encoders
        myMotorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        myMotorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sleep(10);

        myMotorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        myMotorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        sleep(10000);
        encoderDrive(DRIVE_SPEED, -10, -10,  3.0);
        encoderDrive(TURN_SPEED, 12, -12, 3.0);
        encoderDrive(DRIVE_SPEED, -18, -18, 3.0);
        myIntakeController.setMotorPower(1, 1);
        sleep(1500);
        myIntakeController.setMotorPower(1, 0);
        myShooterController.setMotorPower(2, 1);
        sleep(1000);
        myIntakeController.setMotorPower(1, 1);
        myShooterController.setMotorPower(2, 0);
        sleep(1000);
        myIntakeController.setMotorPower(1, 0);
        encoderDrive(DRIVE_SPEED, 31, -31, 4.0);
        encoderDrive(DRIVE_SPEED, 46, 46, 10.0);

    }


    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = myMotorRight.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = myMotorLeft.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            myMotorRight.setTargetPosition(newLeftTarget);
            myMotorLeft.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
        myMotorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        myMotorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // reset the timeout time and start motion.
        runtime.reset();
        myMotorRight.setPower(Math.abs(speed));
        myMotorLeft.setPower(Math.abs(speed));

        // keep looping while we are still active, and there is time left, and both motors are running.
        while (opModeIsActive() &&
                (runtime.seconds() < timeoutS) &&
                (myMotorRight.isBusy() && myMotorLeft.isBusy())) {
            telemetry.addData("Motor1", myMotorRight.getCurrentPosition());
            telemetry.addData("Motor2", myMotorLeft.getCurrentPosition());
            telemetry.update();

        }

        // Stop all motion;
        myMotorRight.setPower(0);
        myMotorLeft.setPower(0);

        // Turn off RUN_TO_POSITION
        myMotorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        myMotorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }

    }

}
