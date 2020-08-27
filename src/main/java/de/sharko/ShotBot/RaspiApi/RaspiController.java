package de.sharko.ShotBot.RaspiApi;


import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;

public class RaspiController {

    private GpioController controller;
    private GpioPinDigitalOutput relay;

    public RaspiController(Pin relayPin) {
        this.controller = GpioFactory.getInstance();
        this.relay = controller.provisionDigitalOutputPin(RaspiPin.GPIO_12, "relay", PinState.HIGH);

        System.out.println("initalize controller");
    }

    public void activateRelay() {
        relay.setState(PinState.LOW);

    }

    public void deactivateRelay() {
        relay.setState(PinState.HIGH);
    }

    public void openRelayForTime(long ms) {
        relay.setState(PinState.HIGH);
        relay.setState(PinState.LOW);
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        relay.setState(PinState.HIGH);
    }

}
