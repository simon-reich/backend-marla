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
        return itemRepository.findById(id)
                             .orElseThrow(() -> new RuntimeException("Item not found"));
    }
    
    public void createItem(Item item) {
        Optional<Item> itemByPath = itemRepository.findItemByPath(item.getPath());
        if(itemByPath.isPresent()) {
            throw new RuntimeException("Item with path " + item.getPath() + " already in database");
        }
        itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id)
                                  .orElseThrow(() -> new RuntimeException("Item not found"));
        itemRepository.delete(item);;
    }

    public void updateItem(Long id, Text text) {
        Item item = itemRepository.findById(id)
                                  .orElseThrow(() -> new RuntimeException("Item not found"));
        List<Text> texts = item.getTexts();
        texts.add(text);
        item.setTexts(texts);
    }
}
