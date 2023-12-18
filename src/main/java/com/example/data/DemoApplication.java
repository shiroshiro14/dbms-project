package com.example.data;

import com.example.data.model.Book;
import com.example.data.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	BookRepository bookRepository;

	@Bean
	@ConditionalOnProperty(prefix = "app", name = "db.init.enabled", havingValue = "true")
	public CommandLineRunner demoCommandLineRunner() {
		return args -> {
			System.out.println("Running......");
		};
	}
}
