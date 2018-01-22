package org.firstinspires.ftc.TeamCodeRelicRecovery;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;


@Autonomous(name="Blue", group="Gabe")
//@Disabled
public class RelicRecoveryBlue extends LinearOpMode {
    
    private HardwarePhynn   robot   = new HardwarePhynn();
    private ElapsedTime     runtime = new ElapsedTime();

    private static final double     COUNTS_PER_MOTOR_REV    = 374 ;    // eg: ANDY MARK Motor Encoder
    private static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    private static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    private static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    private static final double     DRIVE_SPEED             = 0.25;
    private static final double     TURN_SPEED              = 0.4;

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);

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

        robot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sleep(10);
        robot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        while (opModeIsActive()) {
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN && robot.See) {
                telemetry.addData("VuMark", "%s visible", vuMark);
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose();
                telemetry.addData("Pose", format(pose));
                if (vuMark == RelicRecoveryVuMark.RIGHT) {
                    Start();
                    if (robot.ColorSensor.red() > robot.ColorSensor.blue()) {
                        Red();
                        encoderDrive(DRIVE_SPEED, -33.5, -33.5, 10);
                        encoderDrive(TURN_SPEED, 14.5, -14.5, 10); //90 degree turn
                        End();
                    }else if (robot.ColorSensor.blue() > robot.ColorSensor.red()){
                        Blue();
                        encoderDrive(DRIVE_SPEED, -38, -38, 10);
                        encoderDrive(TURN_SPEED, 13, -13, 10); //90 degree turn
                        End();
                    }
                }else if (vuMark == RelicRecoveryVuMark.CENTER) {
                    Start();
                    if (robot.ColorSensor.red() > robot.ColorSensor.blue()) {
                        Red();
                        encoderDrive(DRIVE_SPEED, -26, -26, 10);
                        encoderDrive(TURN_SPEED, 14.5, -14.5, 10); //90 degree turn
                        End();
                    }else if (robot.ColorSensor.blue() > robot.ColorSensor.red()){
                        Blue();
                        encoderDrive(DRIVE_SPEED, -30.5, -30.5, 10);
                        encoderDrive(TURN_SPEED, 14, -14, 10); //90 degree turn
                        End();
                    }
                }else if (vuMark == RelicRecoveryVuMark.LEFT) {
                    Start();
                        if (robot.ColorSensor.red() > robot.ColorSensor.blue()) {
                            Red();
                            encoderDrive(DRIVE_SPEED, -18, -18, 10);
                            encoderDrive(TURN_SPEED, 14.5, -14.5, 15); //90 degree turn
                            End();
                        }else if (robot.ColorSensor.blue() > robot.ColorSensor.red()){
                            Blue();
                            encoderDrive(DRIVE_SPEED, -22.5, -22.5, 10);
                            encoderDrive(TURN_SPEED, 14, -14, 10); //90 degree turn
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
    private void Start()
    {
        robot.servo1.setPosition(.65);
        robot.servo2.setPosition(.65);
        robot.Lift.setPower(.4);
        sleep(1000);
        robot.Lift.setPower(0);
        robot.servo3.setPosition(.3);
        sleep(1000);
        telemetry.addData("Blue", robot.ColorSensor.blue());
        telemetry.addData("Red", robot.ColorSensor.red());
        telemetry.update();
        sleep(2500);
    }

    private String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
    private void Red()
    {
        encoderDrive(DRIVE_SPEED, -4, -4, 10);
        robot.servo3.setPosition(.85);
    }
    private void Blue()
    {
        encoderDrive(DRIVE_SPEED, -6, 6, 10);
        robot.servo3.setPosition(.85);
        encoderDrive(DRIVE_SPEED, 6, -6, 10);
    }
    private void End()
    {
        encoderDrive(DRIVE_SPEED, 8.5, 8.5, 10);
        robot.servo1.setPosition(.1);
        robot.servo2.setPosition(.1);
        sleep(500);
        encoderDrive(DRIVE_SPEED, -3.5, -3.5, 10);
        robot.See = false;
    }

}
