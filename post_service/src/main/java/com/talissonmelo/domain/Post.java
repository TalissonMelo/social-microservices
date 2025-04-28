package com.talissonmelo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
public class Post {

    @Id
    private UUID id;
    private String title;
    private String body;
    private String author;
    private Integer wordCount;
    private BigDecimal calculatedValue;

    private Post(String title, String body, String author) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.body = body;
        this.author = author;
    }

    public static Post created(String title, String body, String author) {
        return new Post(title, body, author);
    }

    public Post update(Integer wordCount, BigDecimal calculatedValue) {
        this.wordCount = wordCount;
        this.calculatedValue = calculatedValue;
        return this;
    }
}
