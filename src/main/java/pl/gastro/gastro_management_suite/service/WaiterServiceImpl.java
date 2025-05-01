package pl.gastro.gastro_management_suite.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gastro.gastro_management_suite.model.Waiter;
import pl.gastro.gastro_management_suite.repository.WaiterRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class WaiterServiceImpl implements WaiterService{
    private final WaiterRepository waiterRepository;

    public List<Waiter> all() {
        return waiterRepository.findAll();
    }

    @Override
    public List<Waiter> allWaiters() {
        return waiterRepository.findAll();
    }

    @Override
    public Waiter newWaiter() {
        return waiterRepository.save(new Waiter());
    }
}
