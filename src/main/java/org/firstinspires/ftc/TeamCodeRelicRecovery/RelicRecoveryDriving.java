package org.firstinspires.ftc.TeamCodeRelicRecovery;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.Drive;
import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.Grabbers;
import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.Lift;

@TeleOp(name = "RelicRecovery", group = "Gabe")
//@Disabled
public class RelicRecoveryDriving extends OpMode{

    private HardwarePhynn phynn    = new HardwarePhynn();
    private ElapsedTime timer    = new ElapsedTime();
    private Grabbers claw      = new Grabbers();
    private Drive drive      = new Drive();
    private Lift lift      = new Lift();


    @Override
    public void init() {
        phynn.init(hardwareMap);

        phynn.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        phynn.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        phynn.servo3.setPosition(0.85);
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        timer.reset();
    }

    @Override
    public void loop() {
        //Lift
        lift.lift();

        //Grabbing
        if (gamepad2.a && phynn.Claws_Open) {
            claw.Close();
            timer.reset();
            while(timer.milliseconds() <= 250){
                telemetry.addData("Wait", timer.milliseconds());
            }
        }
        if (gamepad2.a && !phynn.Claws_Open) {
            claw.Open();
            timer.reset();
            while(timer.milliseconds() <= 250){
                telemetry.addData("Wait", timer.milliseconds());
            }
        }
        if (gamepad2.b) {
            claw.Half();
        }

        //Driving
        if (gamepad1.right_bumper && !gamepad2.right_bumper) {
            drive.slowDrive();
        } else if(gamepad1.left_bumper && !gamepad2.right_bumper) {
            drive.fastDrive();
        } else if(gamepad2.right_bumper) {
            drive.stopDrive();
        } else {
            drive.drive();
        }
    }

    @Override
    public void stop() {
        claw.Open();
    }
}
