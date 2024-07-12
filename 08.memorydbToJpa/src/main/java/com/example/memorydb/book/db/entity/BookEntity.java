package com.example.memorydb.book.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "book")
public class BookEntity{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;
    private String category;
    private BigDecimal amount;
}
