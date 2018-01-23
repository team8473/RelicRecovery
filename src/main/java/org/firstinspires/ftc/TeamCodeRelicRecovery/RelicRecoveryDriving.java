package org.firstinspires.ftc.TeamCodeRelicRecovery;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.DriveFast;
import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.DriveNormal;
import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.DriveSlow;
import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.DriveStop;
import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.GrabbersClose;
import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.GrabbersHalf;
import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.GrabbersOpen;
import org.firstinspires.ftc.TeamCodeRelicRecovery.commands.Lift;

@TeleOp(name = "RelicRecovery", group = "Gabe")

public class RelicRecoveryDriving extends LinearOpMode {

    //Importing all outside programs used
    private HardwarePhynn robot  = new HardwarePhynn();
    private GrabbersClose close  = new GrabbersClose();
    private GrabbersOpen  open   = new GrabbersOpen();
    private GrabbersHalf  half   = new GrabbersHalf();
    private DriveNormal   normal = new DriveNormal();
    private DriveFast     fast   = new DriveFast();
    private DriveSlow     slow   = new DriveSlow();
    private DriveStop     stop   = new DriveStop();
    private Lift          lift   = new Lift();

    @Override
    public void runOpMode() throws InterruptedException{

        //Hardware
        robot.init(hardwareMap);
        
        waitForStart();

        while (opModeIsActive()){

            //Lift
            lift.Lift();

            //Color sensor arm
            robot.servo3.setPosition(.85);

            //Grabbers
            if (gamepad2.a && robot.Open)
            {
                close.Close();
                sleep(250);
            }
            if (gamepad2.a && !robot.Open)
            {
                open.Open();
                sleep(250);
            }
            if (gamepad2.b)
            {
               half.Half();
            }

            //Driving
            if (gamepad1.right_bumper && !gamepad2.right_bumper)
            {
                slow.slowDrive();
            }
            else if(gamepad1.left_bumper && !gamepad2.right_bumper)
            {
                fast.fastDrive();
            }
            else if(gamepad2.right_bumper)
            {
                stop.stopDrive();
            }
            else if (!gamepad1.right_bumper && !gamepad2.right_bumper)
            {
                normal.normalDrive();
            }
        }
    }
}
