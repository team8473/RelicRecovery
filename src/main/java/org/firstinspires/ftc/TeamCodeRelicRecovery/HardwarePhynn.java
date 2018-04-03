package org.firstinspires.ftc.TeamCodeRelicRecovery;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

public class HardwarePhynn {

    public static Ball ball;

    //Encoder Variables
    private static final double COUNTS_PER_MOTOR_REV = 374.0;
    private static final double DRIVE_GEAR_REDUCTION = 2.0;
    private static final double WHEEL_DIAMETER = 4.0;
    private static final double ROBOT_SHORT_DIAMETER_IN = 16.35;
    private static final double ROBOT_LONG_DIAMETER_IN = 17.4;
    private static final double SHORT_SQUARED = Math.pow(0.5 * ROBOT_SHORT_DIAMETER_IN, 2.0);
    private static final double LONG_SQUARED = Math.pow(0.5 * ROBOT_LONG_DIAMETER_IN, 2.0);
    private static final double SQUARE_ROOT = Math.sqrt(2.0 * (SHORT_SQUARED + LONG_SQUARED));
    public static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER * 3.1415);
    public static final double DRIVE_SPEED = 0.6;
    public static final double FAST_DRIVE_SPEED = 1.0;
    public static final double SLOW_DRIVE_SPEED = 0.4;
    public static final double TURN_SPEED = 0.4;
    public static final double ROBOT_CIRCUMFERENCE = (Math.PI * SQUARE_ROOT);

    //Grabber Variables
    public static final double GRABBERS_OPEN = 0.25;
    public static final double GRABBERS_CLOSED = 0.65;
    public static final double GRABBERS_HALF = 0.45;

    //Strings
    public static final String RIGHT = "RIGHT";
    public static final String LEFT  = "LEFT";
    public static final String BLUE  = "BLUE";
    public static final String RED   = "RED";

    //Hardware
    public ColorSensor colorSensor = null;
    public DcMotor motorRight = null;
    public Servo rightGrabber = null;
    public Servo leftGrabber = null;
    public DcMotor motorLeft = null;
    public DcMotor liftMotor = null;
    public Servo armServo = null;

    //Vuforia
    private int cameraMonitorViewId;
    private VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
    private VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(parameters);
    private VuforiaTrackables relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
    private VuforiaTrackable relicTemplate = relicTrackables.get(0);
    RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

    //Booleans
    public boolean see = true;
    public boolean clawsOpen = true;
    final boolean left = vuMark == RelicRecoveryVuMark.LEFT;
    final boolean right = vuMark == RelicRecoveryVuMark.RIGHT;
    final boolean center = vuMark == RelicRecoveryVuMark.CENTER;
    final boolean redBall = colorSensor.red() > colorSensor.blue();
    final boolean blueBall = colorSensor.blue() > colorSensor.red();

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
        relicTemplate.setName("relicVuMarkTemplate"); //Can help in debugging; otherwise not necessary

        //Motors
        motorRight = hwMap.get(DcMotor.class, "right");
        motorLeft = hwMap.get(DcMotor.class, "left");
        liftMotor = hwMap.get(DcMotor.class, "liftMotor");

        motorRight.setDirection(DcMotorSimple.Direction.FORWARD);
        motorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorRight.setPower(0);
        motorLeft.setPower(0);
        liftMotor.setPower(0);

        //Servos
        rightGrabber = hwMap.get(Servo.class, "rightGrabber");
        leftGrabber = hwMap.get(Servo.class, "leftGrabber");
        armServo = hwMap.get(Servo.class, "armServo");

        rightGrabber.setDirection(Servo.Direction.FORWARD);
        leftGrabber.setDirection(Servo.Direction.REVERSE);
        armServo.setDirection(Servo.Direction.FORWARD);

        //Color Sensor
        colorSensor = hwMap.get(ColorSensor.class, "mr");
    }

    public enum Ball {
        IS_RED_BALL, IS_BLUE_BALL
    }
}
