package com.example.memorydb.book.db.repository;

import com.example.memorydb.book.db.entity.BookEntity;
import com.example.memorydb.db.SimpleDataRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookRepository extends SimpleDataRepository<BookEntity, Long> {

    public List<BookEntity> findCategoryAndAmount(String category, BigDecimal amount) {
        return this.findAll().stream()
                .filter(i -> {
                    boolean matchesCategory = (category == null || i.getCategory().equals(category));
                    boolean matchesAmount = (amount == null || i.getAmount().compareTo(amount) >= 0);
                    return matchesCategory && matchesAmount;
                })
                .collect(Collectors.toList());
    }
}
