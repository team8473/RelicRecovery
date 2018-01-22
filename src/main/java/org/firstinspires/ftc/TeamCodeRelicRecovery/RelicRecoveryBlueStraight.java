package org.firstinspires.ftc.TeamCodeRelicRecovery;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

@Autonomous(name="BlueStraight", group="Gabe")
//@Disabled
public class RelicRecoveryBlueStraight extends LinearOpMode {

    //Define controllers
    private DcMotor MotorRight;
    private DcMotor MotorLeft;
    private DcMotor Lift;
    private Servo Servo1;
    private Servo Servo2;
    private Servo Servo3;
    private ColorSensor ColorSensor;

    //Variables for Encoders
    private ElapsedTime     runtime = new ElapsedTime();

    private static final double     COUNTS_PER_MOTOR_REV    = 374 ;    // eg: ANDY MARK Motor Encoder
    private static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    private static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    private static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    private static final double     DRIVE_SPEED             = 0.25;
    private static final double     TURN_SPEED              = 0.4;

    private double See;
    {
        See = 0;
    }

    @Override
    public void runOpMode() throws InterruptedException {

        MotorRight = hardwareMap.dcMotor.get("front right");
        MotorLeft = hardwareMap.dcMotor.get("front left");
        Servo1 = hardwareMap.servo.get("servo1");
        Servo2 = hardwareMap.servo.get("servo2");
        Servo3 = hardwareMap.servo.get("servo3");
        Lift = hardwareMap.dcMotor.get("lift");
        ColorSensor = hardwareMap.colorSensor.get("mr");

        MotorLeft.setDirection(DcMotor.Direction.REVERSE);
        Servo2.setDirection(Servo.Direction.REVERSE);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AWydOn3/////AAAAGWB2YP4r2ERKmLdFMt7DzdUYnt2f97VKdK1fMvb8c5p8iGeDLgwB9dic+osr9GAHQK3K4uJV/8yxon7KXrJNbgzKN82yuHucjwS7gmWkItkoSB+nTn/66dfKF6OyRhh7vBtZqg70Tpv3Pq75kIeij++F34cQNAA3fWEzIoPnuQkew/QP1NNjyZtnIY4lYZFEHgljmtmIP7qwM5vw5pIQRriTaDAfwWPJ9tJVa4yn8eOfPi/bdJzu7VmH9RxySYlnxImCN/EVXcSRPPPQxtjFxza/+aXM3dvRtsGfBuxfBB9YLsKR9RP6sqLG1hB+oXkjxfDDhNLdF3uMsDNy4GGJGFHewgATWnF5xXWDugOq9asb";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
        telemetry.addData(">", "Press Play to start");
        telemetry.update();

        waitForStart();
        relicTrackables.activate();

        //Set Motors to use Encoders
        MotorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MotorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sleep(10);
        MotorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        MotorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive()) {
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN && See == 0) {
                telemetry.addData("VuMark", "%s visible", vuMark);
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose();
                telemetry.addData("Pose", format(pose));
                if (vuMark == RelicRecoveryVuMark.RIGHT) {
                    Start();
                    if (ColorSensor.red() > ColorSensor.blue()) {
                        Red();
                        encoderDrive(DRIVE_SPEED, -17.5, -17.5, 15);
                        encoderDrive(TURN_SPEED, -14, 14, 10);
                        encoderDrive(DRIVE_SPEED, 19, 19, 10);
                        encoderDrive(TURN_SPEED, -14, 14, 10);
                        End();
                    } else if (ColorSensor.blue() > ColorSensor.red()) {
                        Blue();
                        encoderDrive(DRIVE_SPEED, -21, -21, 10);
                        encoderDrive(TURN_SPEED, -14, 14, 10);
                        encoderDrive(DRIVE_SPEED, 22, 22, 10);
                        encoderDrive(TURN_SPEED, -14, 14, 10);
                        End();

                    }

                }else if (vuMark == RelicRecoveryVuMark.CENTER) {
                    Start();
                    if (ColorSensor.red() > ColorSensor.blue()) {
                        Red();
                        encoderDrive(DRIVE_SPEED, -17.5, -17.5, 10);
                        encoderDrive(TURN_SPEED, -14, 14, 10);
                        encoderDrive(DRIVE_SPEED, 13, 13, 10);
                        encoderDrive(TURN_SPEED, -14, 14, 10);
                        End();
                    } else if (ColorSensor.blue() > ColorSensor.red()) {
                        Blue();
                        encoderDrive(DRIVE_SPEED, -21, -21, 10);
                        encoderDrive(TURN_SPEED, -14, 14, 10);
                        encoderDrive(DRIVE_SPEED, 14, 14, 10);
                        encoderDrive(TURN_SPEED, -14, 14, 10);
                        End();

                    }

                }else if (vuMark == RelicRecoveryVuMark.LEFT) {
                    Start();
                    if (ColorSensor.red() > ColorSensor.blue()) {
                        Red();
                        encoderDrive(DRIVE_SPEED, -17.5, -17.5, 15);
                        encoderDrive(TURN_SPEED, -14, 14, 10);
                        encoderDrive(DRIVE_SPEED, 6, 6, 10);
                        encoderDrive(TURN_SPEED, -14, 14, 10);
                        End();
                    } else if (ColorSensor.blue() > ColorSensor.red()) {
                        Blue();
                        encoderDrive(DRIVE_SPEED, -21, -21, 15);
                        encoderDrive(TURN_SPEED, -14, 14, 10);
                        encoderDrive(DRIVE_SPEED, 7.5, 7.5, 10);
                        encoderDrive(TURN_SPEED, -14, 14, 10);
                        End();

                    }

                }
            }
        }
    }
    private void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = MotorRight.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = MotorLeft.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
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
                    (MotorRight.isBusy() && MotorLeft.isBusy())) {
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
    private void Start()
    {
        Servo1.setPosition(.65);
        Servo2.setPosition(.65);
        Lift.setPower(.4);
        sleep(1500);
        Lift.setPower(0);
        Servo3.setPosition(.3);
        sleep(1000);
        telemetry.addData("Blue", ColorSensor.blue());
        telemetry.addData("Red", ColorSensor.red());
        telemetry.update();
        sleep(3000);
    }
    private void Red()
    {
        encoderDrive(DRIVE_SPEED, -4, -4, 15);
        Servo3.setPosition(.85);
    }
    private void Blue()
    {
        encoderDrive(DRIVE_SPEED, -6, 6, 10);
        Servo3.setPosition(.85);
        encoderDrive(DRIVE_SPEED, 6, -6, 15);
    }
    private void End()
    {
        encoderDrive(DRIVE_SPEED, 5, 5, 10);
        Servo1.setPosition(.1);
        Servo2.setPosition(.1);
        sleep(500);
        encoderDrive(DRIVE_SPEED, 4, 4, 10);
        encoderDrive(DRIVE_SPEED, -3, -3, 10);
        See = 1;

    }
    private String format(OpenGLMatrix transformationMatrix)
    {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }

}
