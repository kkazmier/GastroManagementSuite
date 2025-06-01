package pl.gastro.gastro_management_suite.service;

import pl.gastro.gastro_management_suite.model.MenuItem;

import java.util.List;

public interface MenuItemService {
    List<MenuItem> getAll();
    MenuItem create(MenuItem item);
    MenuItem update(Long id, MenuItem item);
    void delete(Long id);
    MenuItem getById(Long id);
}
