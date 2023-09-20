package com.example.aaposBookStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.aaposBookStore.domain.Book;
import com.example.aaposBookStore.domain.BookRepository;
import com.example.aaposBookStore.domain.Category;
import com.example.aaposBookStore.domain.CategoryRepository;

@SpringBootApplication
public class AaposBookStoreApplication {

	private static final Logger log = LoggerFactory.getLogger(AaposBookStoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AaposBookStoreApplication.class, args);
	}


	 @Bean
    public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository) {
        return(args) -> {
			log.info("save a couple of books");
			crepository.save(new Category("Fantasy"));
			crepository.save(new Category("Science"));
			crepository.save(new Category("Scifi"));

			
            brepository.save(new Book("A Farewell to Arms", "Ernest Hemingway" , 1929, "1232323-04", 12.95,crepository.findByName("Fantasy").get(0)));
            brepository.save(new Book("Animal Farm", "George Orwell" , 1945, "2212343-05", 10.95,crepository.findByName("Science").get(0)));
			brepository.save(new Book("Star wars", "Matti Meikäläinen" , 1975, "22165789-05", 99.95,crepository.findByName("Scifi").get(0)));

            
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
        };
    }
}
