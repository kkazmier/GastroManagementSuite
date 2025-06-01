package pl.gastro.gastro_management_suite.service;

import pl.gastro.gastro_management_suite.model.RestaurantOrder;

import java.util.List;

public interface OrderService {
    public RestaurantOrder createOrder(List<Long> itemIds);
}
