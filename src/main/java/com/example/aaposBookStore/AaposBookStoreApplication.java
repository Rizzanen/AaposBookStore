package com.example.aaposBookStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.aaposBookStore.domain.Book;
import com.example.aaposBookStore.domain.BookRepository;

@SpringBootApplication
public class AaposBookStoreApplication {

	private static final Logger log = LoggerFactory.getLogger(AaposBookStoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AaposBookStoreApplication.class, args);
	}


	 @Bean
    public CommandLineRunner bookDemo(BookRepository repository) {
        return(args) -> {
			log.info("save a couple of books");
            repository.save(new Book("Ketunkusema", "Kettu Kusi" , 2023, 123456789, 12.95));
            repository.save(new Book("Ketunloikka", "Kettu Hyppy" , 2023, 123456799, 10.95));

            
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
        };
    }
}