package com.example.aaposBookStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.aaposBookStore.domain.AppUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.aaposBookStore.domain.Book;
import com.example.aaposBookStore.domain.BookRepository;
import com.example.aaposBookStore.domain.Category;
import com.example.aaposBookStore.domain.CategoryRepository;
import com.example.aaposBookStore.domain.AppUserRepository;
import java.util.Arrays;

@SpringBootApplication
public class AaposBookStoreApplication {

	private static final Logger log = LoggerFactory.getLogger(AaposBookStoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AaposBookStoreApplication.class, args);
	}


	 @Bean
    public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository,
	AppUserRepository urepository) {
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

			// Create users with BCrypt encoded password (user/user, admin/admin)
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			AppUser user2 = new AppUser("admin", "$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", "ADMIN");
			urepository.saveAll(Arrays.asList(user1, user2));
        };
    }
}
