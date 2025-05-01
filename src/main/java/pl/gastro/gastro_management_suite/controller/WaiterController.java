package pl.gastro.gastro_management_suite.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gastro.gastro_management_suite.model.Waiter;
import pl.gastro.gastro_management_suite.service.WaiterServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
public class WaiterController {
    private final WaiterServiceImpl waiterService;

    @GetMapping("/waiters")
    public List<Waiter> allWaiters() {
        return waiterService.allWaiters();
    }
}
