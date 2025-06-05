package pl.gastro.gastro_management_suite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gastro.gastro_management_suite.model.Address;
import pl.gastro.gastro_management_suite.model.Delivery;
import pl.gastro.gastro_management_suite.model.OrderStatus;
import pl.gastro.gastro_management_suite.repository.AddressRepository;
import pl.gastro.gastro_management_suite.repository.DeliveryRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final AddressRepository addressRepository;
    private final LocationIqService locationIqService;

    public Delivery createDelivery(Address address) {
        locationIqService.fillCoordinates(address);
        addressRepository.save(address);

        Delivery delivery = new Delivery();
        delivery.setAddress(address);
        delivery.setStatus(OrderStatus.NEW);
        delivery.setTotalAmount(BigDecimal.ZERO);
        return deliveryRepository.save(delivery);
    }

    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    public Optional<Delivery> findById(Long id) {
        return deliveryRepository.findById(id);
    }

}
