package org.firstinspires.ftc.TeamCodeRelicRecovery.commands;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Command to wait for a specified amount of time
 */
public class Wait {

    private ElapsedTime timer = new ElapsedTime();

    public void waitMilliseconds(int time) {
        timer.reset();
        while(timer.milliseconds() <= time){
        }
    }

    public void waitSeconds(double time) {
        timer.reset();
        while(timer.seconds() <= time){
        }
    }
}
