package com.example.data.repository;

import com.example.data.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);
    
    @Query("SELECT b from Book b WHERE b.publishDate > :date")
    List<Book> findByPublishedDateAfter(@Param("date") LocalDate date);

}
