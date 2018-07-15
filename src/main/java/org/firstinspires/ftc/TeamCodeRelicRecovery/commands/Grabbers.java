package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn;

import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.GRABBERS_CLOSED;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.GRABBERS_HALF;
import static org.firstinspires.ftc.TeamCodeRelicRecovery.HardwarePhynn.GRABBERS_OPEN;

/**
 * Movements for the grabbers
 */
@Disabled
public class Grabbers extends LinearOpMode{

    private HardwarePhynn phynn = new HardwarePhynn();

    private int x = 0;
    private int currentPosition = 0;

    public static Position[] position = new Position[]{Position.GRABBERS_OPEN, Position.GRABBERS_CLOSE};

    @Override
    public void runOpMode(){
        phynn.init(hardwareMap);
    }

    public void Half(){
        phynn.rightGrabber.setPosition(GRABBERS_HALF);
        phynn.leftGrabber.setPosition(GRABBERS_HALF);
    }

    private void setPosition(Position position) {
        switch (position) {
            case GRABBERS_OPEN:
                phynn.rightGrabber.setPosition(GRABBERS_OPEN);
                phynn.leftGrabber.setPosition(GRABBERS_OPEN);
                break;
            case GRABBERS_CLOSE:
                phynn.rightGrabber.setPosition(GRABBERS_CLOSED);
                phynn.leftGrabber.setPosition(GRABBERS_CLOSED);
                break;
            default:
                break;
        }
    }

    public void cyclePosition() {
        switch (x) {
            case 0:
                currentPosition += 1;
                setPosition(position[currentPosition]);
                break;
            case 1:
                currentPosition -= 1;
                setPosition(position[currentPosition]);
                break;
            default:
                break;
        }
    }

    private enum Position {
        GRABBERS_CLOSE, GRABBERS_OPEN
    }
}