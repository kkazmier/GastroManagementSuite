package pl.gastro.gastro_management_suite.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gastro.gastro_management_suite.model.MenuItem;
import pl.gastro.gastro_management_suite.model.OrderStatus;
import pl.gastro.gastro_management_suite.model.RestaurantOrder;
import pl.gastro.gastro_management_suite.repository.MenuItemRepository;
import pl.gastro.gastro_management_suite.repository.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;

    public RestaurantOrder createOrder(List<Long> itemIds) {
        List<MenuItem> items = menuItemRepository.findAllById(itemIds);
        RestaurantOrder order = new RestaurantOrder();
        order.setItems(items);
        order.recalculateTotal();
        order.setStatus(OrderStatus.NEW);
        return orderRepository.save(order);
    }

    public List<RestaurantOrder> getAll() {
        return orderRepository.findAll();
    }

    public RestaurantOrder getById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public RestaurantOrder addItem(Long orderId, Long itemId) {
        RestaurantOrder order = orderRepository.findById(orderId).orElseThrow();
        MenuItem item = menuItemRepository.findById(itemId).orElseThrow();
        order.getItems().add(item);
        order.recalculateTotal();
        return orderRepository.save(order);
    }

    public RestaurantOrder removeItem(Long orderId, Long itemId) {
        RestaurantOrder order = orderRepository.findById(orderId).orElseThrow();
        order.getItems().removeIf(i -> i.getId().equals(itemId));
        order.recalculateTotal();
        return orderRepository.save(order);
    }
}
