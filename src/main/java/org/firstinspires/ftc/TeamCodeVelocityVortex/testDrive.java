package org.firstinspires.ftc.TeamCodeVelocityVortex;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.ftcrobotcontroller.R;
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

@Autonomous(name="testDrive", group="Gabe")
@Disabled
public class testDrive extends LinearOpMode {

    public enum ColorSensorDevice {ADAFRUIT, HITECHNIC_NXT, MODERN_ROBOTICS_I2C}

    ;

    public ColorSensorDevice device = ColorSensorDevice.MODERN_ROBOTICS_I2C;

    //Define controllers
    DcMotor myMotorAAA;
    DcMotor myMotorBBB;

    ServoController myServoController;
    //TouchSensor myTouchSenor;
    ColorSensor myColorSensor;
    DeviceInterfaceModule cdim;
    LED led;
    
    //Variables for Encoders
    private ElapsedTime     runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {
        myMotorAAA = hardwareMap.dcMotor.get("aaa");
        myMotorBBB = hardwareMap.dcMotor.get("bbb");

        myServoController = hardwareMap.servoController.get("servos");

        // Get a reference to the touch sensor
        //myTouchSenor = hardwareMap.touchSensor.get("touch");

        cdim = hardwareMap.deviceInterfaceModule.get("Device Interface Module 1");

        //myServoController.setServoPosition(1, 0);

        switch (device) {
            //case HITECHNIC_NXT:
            //colorSensor = hardwareMap.colorSensor.get("nxt");
            //break;
            //case ADAFRUIT:
            //colorSensor = hardwareMap.colorSensor.get("lady");
            // break;
            case MODERN_ROBOTICS_I2C:
                myColorSensor = hardwareMap.colorSensor.get("mr");
                break;
        }

        
        //Set Motors to use Encoders
        myMotorAAA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        myMotorBBB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        myMotorAAA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        myMotorBBB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //If using encoders, tell them to use encoder control. Do this for each motor.
        //controllerL.setMotorChannelMode(motorRightF, DcMotorController.RunMode.RUN_USING_ENCODERS);
        waitForStart();
        
        //Testing the servo
        /*myServoController.setServoPosition(1, .2);
        sleep(1000);

        myServoController.setServoPosition(1, .4);
        sleep(1000);

        myServoController.setServoPosition(1, .6);
        sleep(1000);

        myServoController.setServoPosition(1, .8);
        sleep(1000);*/

        myMotorAAA.setTargetPosition(0);
        myMotorBBB.setTargetPosition(0);



        //Testing the motor
        myMotorAAA.setTargetPosition(360);
        myMotorBBB.setTargetPosition(360);
        sleep(1000);

        /*myMotorController.setTargetPosition(0);
        sleep(500);

        myMotorController.setTargetPosition(-360);
        sleep(1000);

        myMotorController.setTargetPosition(0);*/

        //Setting servo back to 0
        //myServoController.setServoPosition(1, 0);

        //Testing the color sensor
        float hsvValues[] = {0, 0, 0};
        final float values[] = hsvValues;
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(R.id.RelativeLayout);
        /*while (opModeIsActive()) {

            //enableLed(myTouchSenor.isPressed());

            switch (device) {
                //case HITECHNIC_NXT:
                // Color.RGBToHSV(colorSensor.red(), colorSensor.green(), colorSensor.blue(), hsvValues);
                // break;
                //case ADAFRUIT:
                // Color.RGBToHSV((colorSensor.red() * 255) / 800, (colorSensor.green() * 255) / 800, (colorSensor.blue() * 255) / 800, hsvValues);
                // break;
                case MODERN_ROBOTICS_I2C:
                    Color.RGBToHSV(myColorSensor.red() * 8, myColorSensor.green() * 8, myColorSensor.blue() * 8, hsvValues);
                    break;
            }

            telemetry.addData("Clear", myColorSensor.alpha());
            telemetry.addData("Red", myColorSensor.red());
            telemetry.addData("Green", myColorSensor.green());
            telemetry.addData("Blue", myColorSensor.blue());
            telemetry.addData("Hue", hsvValues[0]);
            //telemetry.addData("touch", myTouchSenor);

            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
                }
            });

            //Testing touch sensor
            /*while (opModeIsActive()) {
                if (myTouchSenor.isPressed()) {
                    //Start the motors if the touch sensor is pressed
                    myMotorController.setTargetPosition(360);
                } else {
                    //Stop the motor when touch sensor is not pressed
                    myMotorController.setTargetPosition(0);
                    //If the sensor senses blue then it moves the servo

                    if (myColorSensor.blue() > 3) {
                        myServoController.setServoPosition(1, 1);
                        sleep(1000);
                        myServoController.setServoPosition(1, 0);
                    }
                    //If the color sensor senses red then it moves the motor
                    if (myColorSensor.red() > 3) {
                    //if(myColorSensor.red() > myColorSensor.blue() || myColorSensor.red() > myColorSensor.green()) {
                        myMotorController.setTargetPosition(-360);
                        sleep(1000);
                        myMotorController.setTargetPosition(0);
                    }
                }
            //telemetry.addData("isPressed", String.valueOf(touchSensor.isPressed()));

            // Wait for a hardware cycle to allow other processes to run
            //waitOneFullHardwareCycle();
        }*/  //End while loop
    }

//}

    private void enableLed ( boolean value) {
        switch (device) {
            // case HITECHNIC_NXT:
            //  colorSensor.enableLed(value);
            // break;
            // case ADAFRUIT:
            //led.enable(value);
            //break;
            case MODERN_ROBOTICS_I2C:
                myColorSensor.enableLed(value);
                break;

            //Go fwd for 3 secs, We'll set power at 50%.
       /* setMotorPower(.5);
        sleep(3000);
        setMotorPower(0);


        sleep(1000);

        ///backup for 3 secs
        setMotorPower(-.5);
        sleep(3000);
        setMotorPower(0);


            }*/

    /*void setMotorPower(double myPower){
        controllerL.setMotorPower(motorLeftB, myPower);
        controllerL.setMotorPower(motorLeftF, myPower);
        controllerR.setMotorPower(motorRightF, -myPower);
        controllerR.setMotorPower(motorRightB, -myPower);
    }*/

        }
    }

        public void encoderDrive(double speed,
        double leftInches, double rightInches,
        double timeoutS) {
            int newLeftTarget;
            int newRightTarget;

            // Ensure that the opmode is still active
            if (opModeIsActive()) {

                // Determine new target position, and pass to motor controller
                newLeftTarget = myMotorAAA.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
                newRightTarget = myMotorBBB.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
                myMotorAAA.setTargetPosition(newLeftTarget);
                myMotorBBB.setTargetPosition(newRightTarget);

                // Turn On RUN_TO_POSITION
                myMotorAAA.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                myMotorBBB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                // reset the timeout time and start motion.
                runtime.reset();
                myMotorAAA.setPower(Math.abs(speed));
                myMotorBBB.setPower(Math.abs(speed));

                // keep looping while we are still active, and there is time left, and both motors are running.
                while (opModeIsActive() &&
                        (runtime.seconds() < timeoutS) &&
                        (myMotorAAA.isBusy() && myMotorBBB.isBusy())) {

                    // Display it for the driver.
                    /*telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                    telemetry.addData("Path2",  "Running at %7d :%7d",
                            myMotorAAA.getCurrentPosition(),
                            myMotorBBB.getCurrentPosition());
                    telemetry.update();
                    */
                }

                // Stop all motion;
                myMotorAAA.setPower(0);
                myMotorBBB.setPower(0);

                // Turn off RUN_TO_POSITION
                myMotorAAA.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                myMotorBBB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                //  sleep(250);   // optional pause after each move
            }

        }


    }
//}
