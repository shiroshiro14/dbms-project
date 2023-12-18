package com.example.data.service;

import com.example.data.model.Book;
import com.example.data.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> findByPublishedDateAfter(LocalDate date) {
        return bookRepository.findByPublishedDateAfter(date);
    }

    @Transactional
    public ResponseEntity<Object> create(Book book) {
        Optional<Book> temp = bookRepository.findById(Long.valueOf(book.getId()));

        if (temp == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            bookRepository.save(book);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
