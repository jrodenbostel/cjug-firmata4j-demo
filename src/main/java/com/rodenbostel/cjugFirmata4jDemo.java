package com.rodenbostel;

import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.firmata.FirmataDevice;

import java.io.IOException;

import static org.firmata4j.Pin.Mode.OUTPUT;

/**
 * Created by justin on 6/24/15.
 */
public class CjugFirmata4jDemo {
    static IODevice device = new FirmataDevice("/dev/tty.usbmodem1451");

    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            setup();
            loop();
        }
        finally {
            device.stop();
        }
    }

    private static final void setup() throws IOException, InterruptedException {
            device.start(); // initiate communication to the device
            device.ensureInitializationIsDone(); // wait for initialization is done
            System.out.println("Device is ready");
    }

    private static final void loop() throws IOException, InterruptedException {
        while(true) {
            Pin pin = device.getPin(13);
            pin.setMode(OUTPUT);

            for(int i = 0; i < 10; i++) {
                pin.setValue(1);
                Thread.sleep(500);
                pin.setValue(0);
                Thread.sleep(500);
            }
        }
    }
}
