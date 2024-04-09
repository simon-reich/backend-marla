package com.marla.backend.text;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextService {
    private final TextRepository textRepository;

    @Autowired
    public TextService(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    public List<Text> getTexts() {
        return textRepository.findAll();
    }

    public Text getText(Long id) {
        Optional<Text> text = textRepository.findById(id); 
        if (!text.isPresent()) {
            throw new IllegalStateException("text not in database");
        }
        return text.get();
    }
    
    public void createText(Text text) {
        textRepository.save(text);
    }

    public void deleteText(Long id) {
        Optional<Text> text = textRepository.findById(id);
        if(text.isEmpty()) {
            throw new IllegalStateException("text not in database");
        }
        textRepository.deleteById(id);
    }

    public void updateText(Long id, String newText) {
        Optional<Text> text = textRepository.findById(id);
        if(text.isEmpty()) {
            throw new IllegalStateException("text not in database");
        }
        text.get().setText(newText);
    }
}

