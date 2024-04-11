package com.marla.backend.text;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/text")
public class TextController {
    private final TextService textService;

    @Autowired
    public TextController(TextService textService) {
        this.textService = textService;
    }

    @GetMapping
    public List<Text> getTexts() {
        return textService.getTexts();
    }

    @GetMapping(path = "{id}")
    public Text getText(@PathVariable("id") Long id) {
        return textService.getText(id);
    }

    @PostMapping(path = "{itemId}")
    public void createText(@RequestBody Text text, @PathVariable("itemId") Long itemId) {
        textService.createText(text.getText(), itemId);
    }
    
    @DeleteMapping(path = "{id}")
    public void deleteText(@PathVariable("id") Long id) {
        textService.deleteText(id);
    }

    @PutMapping(path = "{id}")
    public void updateText(
            @PathVariable("id") Long id,
            @RequestParam String text) {
        textService.updateText(id, text);
    }
}

