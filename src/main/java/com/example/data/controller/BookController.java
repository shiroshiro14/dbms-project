package com.example.data.controller;


import com.example.data.model.Author;
import com.example.data.model.Book;
import com.example.data.pojos.BookNameSearch;
import com.example.data.pojos.BookRequest;
import com.example.data.pojos.BookUpdateRequest;
import com.example.data.service.AuthorService;
import com.example.data.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/find/{id}")
    public Optional<Book> findById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping("/search")
    public List<Book> findByTitle(@RequestBody BookNameSearch bookNameSearch) {
        return bookService.findByTitle(bookNameSearch.title);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addBook(@RequestBody BookRequest bookRequest) {
        return bookService.addBook(bookRequest);
    }

    @GetMapping("/available")
    public List<Book> findAvailableBooks() {
        return bookService.findAvailableBooks();
    }

    @PostMapping("/delete")
    ResponseEntity<Object> deleteBookByTitle (@RequestBody BookRequest bookRequest){
        return bookService.deleteByTitle(bookRequest.title, bookRequest.author_name);
    }

    @PostMapping("find/composite")
    public List<Book> searchWithTitleAndAuthor(@RequestBody BookRequest bookRequest) {
        Author author = authorService.findByName(bookRequest.author_name);
        return bookService.doubleCondiSearch(bookRequest.title, author.getId());
    }

    @PostMapping("/update")
    public Book updateBook(@RequestBody BookUpdateRequest bookUpdateRequest) {
        int id = Integer.parseInt(bookUpdateRequest.id);
        BigDecimal price = new BigDecimal(bookUpdateRequest.price);
        LocalDate publishDate = LocalDate.parse(bookUpdateRequest.publishDate);
        Author author = authorService.findByName(bookUpdateRequest.author);
        Boolean inStock = Boolean.valueOf(bookUpdateRequest.inStock);

        Book book = new Book(
                id,
                bookUpdateRequest.title,
                price,
                publishDate,
                author,
                inStock);

        return bookService.save(book);
    }

    @PostMapping("/bookcount")
    public List<Book> countBookOnAuthor(@RequestBody int id) {
        return bookService.bookCountOnAuthor(id);
    }
}