package com.example.data.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

import static jakarta.persistence.CascadeType.ALL;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    private BigDecimal price = BigDecimal.valueOf(0);

    @CreationTimestamp
    private LocalDate publishDate;

    @ManyToOne
    private Author author;

    private boolean inStock = true;

}