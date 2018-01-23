package org.firstinspires.ftc.Off_Season_Tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;

@TeleOp(name = "Touch", group = "Phynn")
@Disabled
public class TouchSensorTest extends LinearOpMode {

    private HardwarePhynn robot = new HardwarePhynn();

    private DigitalChannel digitalTouch = null;

    @Override
    public void runOpMode() throws InterruptedException{

        robot.init(hardwareMap);

        digitalTouch.setMode(DigitalChannel.Mode.INPUT);

        waitForStart();

        while (opModeIsActive()){

            if (digitalTouch.getState() == false)
            {
                telemetry.addData("Touch", "Is Pressed");
                telemetry.update();
            }
            else
            {
                telemetry.addData("Touch", "Is Not Pressed");
                telemetry.update();
            }
        }

    }
}
