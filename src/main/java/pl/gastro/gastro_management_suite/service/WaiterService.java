package pl.gastro.gastro_management_suite.service;

import pl.gastro.gastro_management_suite.model.Waiter;

import java.util.List;

public interface WaiterService {
    List<Waiter> allWaiters();
    Waiter newWaiter();
}
