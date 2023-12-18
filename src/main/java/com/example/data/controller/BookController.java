package com.example.data.controller;


import com.example.data.model.Book;
import com.example.data.service.BookService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/find/{id}")
    public Optional<Book> findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/insert")
    public ResponseEntity<Object> create(@RequestBody Book book) {
        return bookService.create(book);
    }

    @PutMapping
    public Book update(@RequestBody Book book) {
        return bookService.save(book);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @GetMapping("/find/{title}")
    public List<Book> findByTitle(@PathVariable String title) {
        return bookService.findByTitle(title);
    }

    @GetMapping("/find/date-after/{date}")
    public List<Book> findByPublishedDateAfter (
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return bookService.findByPublishedDateAfter(date);
    }

}