package org.firstinspires.ftc.TeamCodeRelicRecovery;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.Drive;
import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.Grabbers;
import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.Lift;

@TeleOp(name = "RelicRecovery", group = "Gabe")

public class RelicRecoveryDriving extends LinearOpMode {

    //Importing all outside programs used
    private HardwarePhynn phynn      = new HardwarePhynn();
    private Grabbers      grabbers   = new Grabbers();
    private Drive         drive      = new Drive();
    private Drive         fast       = new Drive();
    private Lift          lift       = new Lift();

    @Override
    public void runOpMode() throws InterruptedException{

        //Hardware
        phynn.init(hardwareMap);
        
        waitForStart();

        while (opModeIsActive()){

            //lift
            lift.lift();

            //Color sensor arm
            phynn.servo3.setPosition(.85);

            //Grabbers
            if (gamepad2.a && phynn.Claws_Open)
            {
                grabbers.Close();
                sleep(250);
            }
            if (gamepad2.a && !phynn.Claws_Open)
            {
                grabbers.Open();
                sleep(250);
            }
            if (gamepad2.b)
            {
               grabbers.Half();
            }

            //Driving
            if (gamepad1.right_bumper && !gamepad2.right_bumper)
            {
                drive.slowDrive();
            }
            else if(gamepad1.left_bumper && !gamepad2.right_bumper)
            {
                fast.fastDrive();
            }
            else if(gamepad2.right_bumper)
            {
                drive.stopDrive();
            }
            else if (!gamepad1.right_bumper && !gamepad2.right_bumper)
            {
                drive.drive();
            }
        }
    }
}
