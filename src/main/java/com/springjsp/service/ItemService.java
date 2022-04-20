package com.springjsp.service;

import com.springjsp.model.Item;
import com.springjsp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAllByJQl();
    }

    public Item getItemById(Long id) {
        return itemRepository.findItemByJQl(id);
    }
}
