package org.jointheleague.iaroc;

import android.os.SystemClock;

import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;

import org.jointheleague.erik.irobot.IRobotCreateAdapter;
import org.jointheleague.erik.irobot.IRobotCreateInterface;
import org.jointheleague.iaroc.sensors.UltraSonicSensors;

public class Brain extends IRobotCreateAdapter {
    private final Dashboard dashboard;
    public UltraSonicSensors sonar;

    public Brain(IOIO ioio, IRobotCreateInterface create, Dashboard dashboard)
            throws ConnectionLostException {
        super(create);
        sonar = new UltraSonicSensors(ioio);
        this.dashboard = dashboard;
    }

    /* This method is executed when the robot first starts up. */
    public void initialize() throws ConnectionLostException {
        dashboard.log("Hello! I'm a Clever Robot!");
        //what would you like me to do, Clever Human?


    }

    /* This method is called repeatedly. */
    public void loop() throws ConnectionLostException {
        readSensors(SENSORS_BUMPS_AND_WHEEL_DROPS);
        driveDirect(250, 500);
        if (isBumpRight()) {
            driveDirect(400, -400);
            SystemClock.sleep(300);
        }
        if (isBumpLeft()) {
            driveDirect(300, 200);
            SystemClock.sleep(300);
        }
        if (isBumpRight() && isBumpLeft()) {
            driveDirect(-400, -400);
            SystemClock.sleep(300);
        }
    }
}