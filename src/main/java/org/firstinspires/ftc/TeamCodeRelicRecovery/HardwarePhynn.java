package org.firstinspires.ftc.TeamCodeRelicRecovery;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

public class HardwarePhynn {

    public enum Ball {
        IS_RED_BALL, IS_BLUE_BALL
    }
    public static Ball ball;

    //Encoder Variables
    private static final double COUNTS_PER_MOTOR_REV = 374;
    private static final double DRIVE_GEAR_REDUCTION = 2.0;
    private static final double WHEEL_DIAMETER = 4.0;
    public static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                                                 (WHEEL_DIAMETER * 3.1415);
    public static final double DRIVE_SPEED = 0.25;
    public static final double TURN_SPEED = 0.4;
    public static final String RIGHT = "RIGHT";
    public static final String LEFT  = "LEFT";
    public static final String RED   = "RED";
    public static final String BLUE  = "BLUE";
    private static final double ROBOT_SHORT_DIAMETER_IN = 16.35;
    private static final double ROBOT_LONG_DIAMETER_IN = 17.4;
    private final double shortSquared = Math.pow(0.5 * ROBOT_SHORT_DIAMETER_IN, 2.0);
    private final double longSquared = Math.pow(0.5 * ROBOT_LONG_DIAMETER_IN, 2.0);
    private final double sqRoot = Math.sqrt(2.0 * (shortSquared + longSquared));
    public final double robotCircumference = (Math.PI * sqRoot);
    public ElapsedTime runtime = new ElapsedTime();

    //Hardware
    public DcMotor motorRight = null;
    public DcMotor motorLeft = null;
    public DcMotor Lift = null;
    public Servo servo1 = null;
    public Servo servo2 = null;
    public Servo servo3 = null;
    public ColorSensor ColorSensor = null;
    public BNO055IMU imu = null;

    //Gyro PID Variables
    public static final double kP = 0.0;
    public static final double kI = 0.0;
    public static final double kD = 0.0;

    //Variables
    public boolean Claws_Open = true;
    public boolean See = true;
    final boolean Red_Ball = ColorSensor.red() > ColorSensor.blue();
    final boolean Blue_Ball = ColorSensor.blue() > ColorSensor.red();


    //Vuforia
    private int cameraMonitorViewId;
    private VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
    private VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(parameters);
    private VuforiaTrackables relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
    private VuforiaTrackable relicTemplate = relicTrackables.get(0);
    RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
    final boolean RRRight = (vuMark == RelicRecoveryVuMark.RIGHT);
    final boolean RRLeft = (vuMark == RelicRecoveryVuMark.LEFT);
    final boolean RRCenter = (vuMark == RelicRecoveryVuMark.CENTER);

    private HardwareMap hwMap = null;

    public HardwarePhynn() {

    }

    public void init(HardwareMap ahwMap) {

        hwMap = ahwMap;

        //Vuforia
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        //Motors
        motorRight = hwMap.get(DcMotor.class, "right");
        motorLeft = hwMap.get(DcMotor.class, "left");
        Lift = hwMap.get(DcMotor.class, "lift");

        motorLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        motorRight.setPower(0);
        motorLeft.setPower(0);
        Lift.setPower(0);

        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Servos
        servo1 = hwMap.get(Servo.class, "servo1");
        servo2 = hwMap.get(Servo.class, "servo2");
        servo3 = hwMap.get(Servo.class, "servo3");

        servo2.setDirection(Servo.Direction.REVERSE);

        //ColorSensor
        ColorSensor = hwMap.get(ColorSensor.class, "mr");

        //Gyro
        imu = hwMap.get(BNO055IMU.class, "imu");

    }
}
