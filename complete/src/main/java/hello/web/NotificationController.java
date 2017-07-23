package hello.web;

import hello.service.WebsocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotificationController {

    @Autowired
    WebsocketService websocketService;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public ResponseEntity mock(@RequestParam(value = "msg", required = false, defaultValue = "default") String msg) {
        websocketService.send(msg);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
