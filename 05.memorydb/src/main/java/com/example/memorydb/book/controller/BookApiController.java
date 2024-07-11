package com.example.memorydb.book.controller;

import com.example.memorydb.book.db.entity.BookEntity;
import com.example.memorydb.book.service.BookService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookApiController {

    private final BookService bookService;

    @PostMapping
    public BookEntity save(@RequestBody BookEntity bookEntity) {
        return bookService.save(bookEntity);
    }

    @GetMapping("/all")
    public List<BookEntity> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/id/{id}")
    public BookEntity findOne(@PathVariable Long id) {
        return bookService.findOne(id);
    }

    @DeleteMapping("/id/{id}")
    public void delete(@PathVariable Long id){
        bookService.delete(id);
    }

    @GetMapping("/category")
    public List<BookEntity> findCategoryAndAmount(@RequestParam(required = false) String category,
                                                  @RequestParam(required = false) BigDecimal amount) {
        return bookService.findCategoryAndAmount(category, amount);
    }


}
