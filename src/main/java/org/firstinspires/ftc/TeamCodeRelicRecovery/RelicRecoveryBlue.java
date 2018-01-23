package org.firstinspires.ftc.TeamCodeRelicRecovery;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.GrabbersClose;
import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.GrabbersOpen;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.COUNTS_PER_INCH;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.DRIVE_SPEED;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.LEFT;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.RIGHT;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.TURN_SPEED;


@Autonomous(name="Blue", group="Gabe")
//@Disabled
public class RelicRecoveryBlue extends LinearOpMode {
    
    private HardwarePhynn   robot    = new HardwarePhynn();
    private EncoderTemplate encoders = new EncoderTemplate();
    private GrabbersClose   close    = new GrabbersClose();
    private GrabbersOpen    open     = new GrabbersOpen();
    
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
                        encoders.encoderDrive(DRIVE_SPEED, -33.5, -33.5, 10);
                        encoders.encoderTurn(45, LEFT);
                        End();
                    }else if (robot.ColorSensor.blue() > robot.ColorSensor.red()){
                        Blue();
                        encoders.encoderDrive(DRIVE_SPEED, -38, -38, 10);
                        encoders.encoderTurn(45, LEFT);
                        End();
                    }
                }else if (vuMark == RelicRecoveryVuMark.CENTER) {
                    Start();
                    if (robot.ColorSensor.red() > robot.ColorSensor.blue()) {
                        Red();
                        encoders.encoderDrive(DRIVE_SPEED, -26, -26, 10);
                        encoders.encoderTurn(45, LEFT);
                        End();
                    }else if (robot.ColorSensor.blue() > robot.ColorSensor.red()){
                        Blue();
                        encoders.encoderDrive(DRIVE_SPEED, -30.5, -30.5, 10);
                        encoders.encoderTurn(45, LEFT);
                        End();
                    }
                }else if (vuMark == RelicRecoveryVuMark.LEFT) {
                    Start();
                        if (robot.ColorSensor.red() > robot.ColorSensor.blue()) {
                            Red();
                            encoders.encoderDrive(DRIVE_SPEED, -18, -18, 10);
                            encoders.encoderTurn(45, LEFT);
                            End();
                        }else if (robot.ColorSensor.blue() > robot.ColorSensor.red()){
                            Blue();
                            encoders.encoderDrive(DRIVE_SPEED, -22.5, -22.5, 10);
                            encoders.encoderTurn(45, LEFT);
                            End();
                        }
                    }
                }
            }
        }
    
    private void Start()
    {
        close.Close();
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

    private void Red()
    {
        encoders.encoderDrive(DRIVE_SPEED, -4, -4, 10);
        robot.servo3.setPosition(.85);
    }
    private void Blue()
    {
        encoders.encoderTurn(45, RIGHT);
        robot.servo3.setPosition(.85);
        encoders.encoderTurn(45, LEFT);

    }
    private void End()
    {
        encoders.encoderDrive(DRIVE_SPEED, 8.5, 8.5, 10);
        robot.servo1.setPosition(.1);
        robot.servo2.setPosition(.1);
        sleep(500);
        encoders.encoderDrive(DRIVE_SPEED, -3.5, -3.5, 10);
        robot.See = false;
    }

    private String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
