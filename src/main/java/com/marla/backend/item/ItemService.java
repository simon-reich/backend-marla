package com.marla.backend.item;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marla.backend.text.Text;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item getItem(Long id) {
        Optional<Item> item = itemRepository.findById(id); 
        if (!item.isPresent()) {
            throw new IllegalStateException("item not in database");
        }
        return item.get();
    }
    
    public void createItem(Item item) {
        Optional<Item> itemByPath = itemRepository.findItemByPath(item.getPath());
        if(itemByPath.isPresent()) {
            throw new IllegalStateException("item with path " + item.getPath() + " already in database");
        }
        itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isEmpty()) {
            throw new IllegalStateException("item not in database");
        }
        itemRepository.deleteById(id);
    }

    public void updateItem(Long id, Text text) {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isEmpty()) {
            throw new IllegalStateException("item not in database");
        }
        List<Text> texts = item.get().getTexts();
        texts.add(text);
        item.get().setTexts(texts);
    }
}
