package com.example.data.service;

import com.example.data.model.Author;
import com.example.data.model.Book;
import com.example.data.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAll() {return authorRepository.findAll();};

    public Author findByName(String name) {
        return authorRepository.findByName(name);
    }
}
