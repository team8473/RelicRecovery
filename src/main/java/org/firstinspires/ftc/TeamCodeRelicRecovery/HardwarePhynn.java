package org.firstinspires.ftc.TeamCodeRelicRecovery;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

public class HardwarePhynn {

    //Vuforia
    int cameraMonitorViewId;
    VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
    VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(parameters);
    VuforiaTrackables relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
    VuforiaTrackable relicTemplate = relicTrackables.get(0);
    OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) relicTemplate.getListener()).getPose();
    RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

    //Hardware
    public DcMotor motorRight = null;
    public DcMotor motorLeft = null;
    public DcMotor Lift = null;
    public Servo servo1 = null;
    public Servo servo2 = null;
    public Servo servo3 = null;
    public ColorSensor ColorSensor = null;
    public BNO055IMU imu = null;


    //Encoder Variables
    public static final double     COUNTS_PER_MOTOR_REV    = 374 ;      // eg: ANDY MARK Motor Encoder
    public static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    public static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference of the wheel
    public static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                                                             (WHEEL_DIAMETER_INCHES * 3.1415);
    public static final double     DRIVE_SPEED             = 0.25;
    public static final double     TURN_SPEED              = 0.4;
    public static final double     RIGHT                   = 1;
    public static final double     LEFT                    = 0;
    public static final double     ROBOT_SHORT_DIAMETER_IN = 16.35; // Used to find the circumference of the robots ellipse
    public static final double     ROBOT_LONG_DIAMETER_IN  = 17.4;  // Used to find the circumference of the robots ellipse
    public final double shortSquared = Math.pow(.5 * ROBOT_SHORT_DIAMETER_IN, 2);
    public final double longSquared = Math.pow(.5 * ROBOT_LONG_DIAMETER_IN, 2);
    public final double sqRoot = Math.sqrt(2 * (shortSquared + longSquared));
    public final double robotCircumference = (Math.PI * sqRoot);

    //Gyro PID Variable
    public static final double     kP                       = 0;
    public static final double     kI                       = 0;
    public static final double     kD                       = 0;

    //Variables
    public boolean Claws_Open = true;
    public boolean See = true;
    public final boolean Red_Ball = ColorSensor.red() > ColorSensor.blue();
    public final boolean Blue_Ball = ColorSensor.blue() > ColorSensor.red();

    public ElapsedTime runtime = new ElapsedTime();

    HardwareMap hwMap = null;

    public final boolean RRRight = (vuMark == RelicRecoveryVuMark.RIGHT);
    public final boolean RRLeft = (vuMark == RelicRecoveryVuMark.LEFT);
    public final boolean RRCenter = (vuMark == RelicRecoveryVuMark.CENTER);

    public HardwarePhynn(){

    }
    
    public void init (HardwareMap ahwMap){

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
