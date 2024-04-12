package com.marla.backend.text;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.marla.backend.item.Item;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Text {
    @Id
    @SequenceGenerator(
        name = "text_sequence",
        sequenceName = "text_sequence",
        allocationSize = 1
    )
    @GeneratedValue (
        strategy = GenerationType.SEQUENCE,
        generator = "text_sequence"
    )
    private Long id;
    private String text;
    private LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    @JsonBackReference
    private Item item;


    @PrePersist
    public void prePersist() {
        this.date = LocalDate.now();
    }


    public Text() {}

    public Text(String text, Item item) {
        this.text = text;
        this.item = item;
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }


    public Long getId() {
        return id;
    }


    public LocalDate getDate() {
        return date;
    }


    public Item getItem() {
        return item;
    }   
}
