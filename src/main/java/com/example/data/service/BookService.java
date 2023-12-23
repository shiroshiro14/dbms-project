package com.example.data.service;

import com.example.data.model.Author;
import com.example.data.model.Book;
import com.example.data.pojos.BookRequest;
import com.example.data.repository.AuthorRepository;
import com.example.data.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void deleteById(String title) {
        List<Book> deletingBook = bookRepository.findByTitle(title);
        bookRepository.deleteById((long) deletingBook.get(0).getId());
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Transactional
    public ResponseEntity<Object> create(Book book) {
        List<Book> temp = bookRepository.findByTitle(book.getTitle());

        if (temp == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            bookRepository.save(book);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    public ResponseEntity<Object> addBook(BookRequest bookRequest) {
        Author requestAuthor = authorRepository.findByName(bookRequest.author_name);
        if (requestAuthor == null) {
            Author newAuthor = new Author(bookRequest.author_name);
            authorRepository.save(newAuthor);
        } else {
            Book newBook = new Book();
            newBook.setTitle(bookRequest.title);
            newBook.setAuthor(requestAuthor);
            bookRepository.save(newBook);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public List<Book> findAvailableBooks() {
        return bookRepository.findByInStock(true);
    }

    public ResponseEntity<Object> deleteByTitle(String title, String author_name) {
        List<Book> deleting = bookRepository.findByTitle(title);
        for (Book book : deleting) {
            if (book.getAuthor().getName().equals(author_name)) {
                bookRepository.deleteById((long) book.getId());
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<Book> doubleCondiSearch(String title, int id) {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b JOIN Author a ON b.author = a WHERE a.id = :AuthorId AND b.title = :BookTitle", Book.class);

        return query.setParameter("BookTitle", title).setParameter("AuthorId",id).getResultList();
    }
    public List<Book> bookCountOnAuthor(int id) {

        TypedQuery<Book> query = entityManager.createQuery("SELECT COUNT(*) FROM Book b JOIN Author a ON b.author = a WHERE b.author.id = :AuthorId", Book.class);

        return query.setParameter("AuthorId", id).getResultList();
    }
}
