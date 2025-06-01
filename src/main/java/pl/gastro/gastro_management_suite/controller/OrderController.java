package pl.gastro.gastro_management_suite.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.gastro.gastro_management_suite.model.RestaurantOrder;
import pl.gastro.gastro_management_suite.service.OrderServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderService;

    @GetMapping
    public List<RestaurantOrder> getAllOrders() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public RestaurantOrder getById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @PostMapping
    public RestaurantOrder create(@RequestBody List<Long> itemIds) {
        return orderService.createOrder(itemIds);
    }

    @PutMapping("/{orderId}/add/{itemId}")
    public RestaurantOrder addItem(@PathVariable Long orderId, @PathVariable Long itemId) {
        return orderService.addItem(orderId, itemId);
    }

    @PutMapping("/{orderId}/remove/{itemId}")
    public RestaurantOrder removeItem(@PathVariable Long orderId, @PathVariable Long itemId) {
        return orderService.removeItem(orderId, itemId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
