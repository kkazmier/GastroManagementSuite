package pl.gastro.gastro_management_suite.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.gastro.gastro_management_suite.model.MenuItem;
import pl.gastro.gastro_management_suite.repository.MenuItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;

    @Override
    public List<MenuItem> getAll() {
        return menuItemRepository.findAll();
    }

    @Override
    public MenuItem create(MenuItem item) {
        return menuItemRepository.save(item);
    }

    @Override
    public MenuItem update(Long id, MenuItem updated) {
        MenuItem existing = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItem not found with id " + id));
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());
        return menuItemRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        menuItemRepository.deleteById(id);
    }

    @Override
    public MenuItem getById(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuItem not found with id " + id));
    }
}
