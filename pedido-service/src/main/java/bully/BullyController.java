package bully;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bully")
public class BullyController {

    @Autowired
    private final BullyService bullyService;

    public BullyController(BullyService bullyService) {
        this.bullyService = bullyService;
    }

    @PostMapping("/start")
    public String startElection() {

        bullyService.startElection();
        return "Eleição iniciada";
    }

    @PostMapping("/election")
    public void receiveElection(@RequestBody Integer senderId) {

        bullyService.receiveElection(senderId);
    }

    @PostMapping("/coordinator")
    public void receiveCoordinator(@RequestBody Integer coordinatorId) {

        bullyService.receiveCoordinator(coordinatorId);
    }

    @PostMapping("/ok")
    public void ok(@RequestBody Integer senderId) {

        System.out.println("Recebeu OK de " + senderId);
    }

    @GetMapping("/ping")
    public String ping() {
        return "alive";
    }
}