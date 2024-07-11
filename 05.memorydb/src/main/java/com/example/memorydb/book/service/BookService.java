package com.example.memorydb.book.service;

import com.example.memorydb.book.db.entity.BookEntity;
import com.example.memorydb.book.db.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // create, update
    public BookEntity save(BookEntity book) {
        return bookRepository.save(book);
    }

    // all
    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    // delete
    public void delete(Long id) {
        bookRepository.delete(id);
    }

    // find one
    public BookEntity findOne(Long id) {
        return bookRepository.findById(id).get();
    }

    // find Category
    public List<BookEntity> findCategoryAndAmount(String category, BigDecimal score) {
        return bookRepository.findCategoryAndAmount(category, score);
    }
}
