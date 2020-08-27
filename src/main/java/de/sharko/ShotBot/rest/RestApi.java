package de.sharko.ShotBot.rest;

import com.pi4j.io.gpio.RCMPin;
import com.pi4j.io.gpio.RaspiPin;
import de.sharko.ShotBot.RaspiApi.RaspiController;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestApi {

    private RaspiController raspiController;

    public RestApi() {
        try{
            this.raspiController = new RaspiController(RCMPin.GPIO_12);
        } catch (UnsatisfiedLinkError e) {
            System.err.println(e);
        }


    }

    @PostMapping("/api/state")
    @ResponseBody
    public void setRaspiIOPinLevel(@RequestParam("state") String state) {
        switch (state) {
            case "ON":
                raspiController.activateRelay();
                System.out.println("Pin state HIGH");
                break;
            case "OFF":
                raspiController.deactivateRelay();
                System.out.println("Pin state LOW");
                break;
            default:
                break;
        }
    }

    @PostMapping("/api/delay")
    @ResponseBody
    public void turnOnForTime(@RequestParam("time") String time) {
        try {
            raspiController.openRelayForTime(Long.parseLong(time));
        } catch ( NumberFormatException e) {
            System.err.println(e);
        }
    }


}
