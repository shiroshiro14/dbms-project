package com.example.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Stock {
    @Id
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private boolean inStock;

    @Getter
    @Setter
    private LocalDate lastUpdate;
}
