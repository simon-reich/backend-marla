package com.marla.backend.text;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marla.backend.item.Item;
import com.marla.backend.item.ItemRepository;

@Service
public class TextService {
    private final TextRepository textRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public TextService(TextRepository textRepository, 
                       ItemRepository itemRepository) {
        this.textRepository = textRepository;
        this.itemRepository = itemRepository;
    }

    public List<Text> getTexts() {
        return textRepository.findAll();
    }

    public Text getText(Long id) {
        return textRepository.findById(id)
                             .orElseThrow(() -> new RuntimeException("Text not found"));
    }
    
    public void createText(String text, Long itemId) {
        Item item = itemRepository.findById(itemId)
                                  .orElseThrow(() -> new RuntimeException("Item not found"));
        Text newText = new Text(text, item);
        textRepository.save(newText);
    }

    public void deleteText(Long id) {
        Text text = textRepository.findById(id)
                                  .orElseThrow(() -> new RuntimeException("Text not found"));
        textRepository.delete(text);
    }

    public void updateText(Long id, String newText) {
        Text text = textRepository.findById(id)
                                  .orElseThrow(() -> new RuntimeException("Text not found"));
        text.setText(newText);
        textRepository.save(text);
    }
}
