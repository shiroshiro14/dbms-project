package com.example.data.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private BigDecimal price;

    @Getter
    @Setter
    @CreationTimestamp
    private LocalDate publishDate;

    @Getter
    @Setter
    private int author;
}