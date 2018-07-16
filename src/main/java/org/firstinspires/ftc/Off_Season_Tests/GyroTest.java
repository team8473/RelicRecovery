package org.firstinspires.ftc.Off_Season_Tests;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.kP;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.kI;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.kD;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.COUNTS_PER_INCH;

@Autonomous(name = "Sensor: BNO055 IMU", group = "Sensor")
@Disabled
public class GyroTest extends LinearOpMode {

    private HardwarePhynn phynn = new HardwarePhynn();
    private ElapsedTime runtime = new ElapsedTime();

    private double lastError, integralError;
    private double goalAngle;

    private Orientation angles = null;

    @Override public void runOpMode() {

        goalAngle = angles.secondAngle;
        lastError = 0.0;
        integralError = 0.0;

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        phynn.imu = hardwareMap.get(BNO055IMU.class, "imu");
        phynn.imu.initialize(parameters);

        waitForStart();


        // startUp the logging of measured acceleration
        phynn.imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);

    }

    public void gyroDrive(double speed,
                              double leftInches, double rightInches,
                              double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        if (opModeIsActive()) {

            newLeftTarget = phynn.motorRight.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget = phynn.motorLeft.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            phynn.motorRight.setTargetPosition(newLeftTarget);
            phynn.motorLeft.setTargetPosition(newRightTarget);

            phynn.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            phynn.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            phynn.motorRight.setPower(Math.abs(speed));
            phynn.motorLeft.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (phynn.motorRight.isBusy() && phynn.motorLeft.isBusy())) {

                double error = goalAngle - angles.secondAngle;
                integralError += error;
                double deltaError = error - lastError;

                double Pterm = kP * error;
                double Iterm = kI * integralError;
                double Dterm = kD * deltaError;

                double correction = Pterm + Iterm + Dterm;
                correction = Math.min(0.4, correction);
                correction = Math.max(-0.4, correction);

                phynn.motorRight.setPower(speed - correction);
                phynn.motorLeft.setPower(speed + correction);

                lastError = error;

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
