package com.example.aaposBookStore;

import com.example.aaposBookStore.web.BookController;
import com.example.aaposBookStore.domain.AppUser;
import com.example.aaposBookStore.domain.AppUserRepository;
import com.example.aaposBookStore.domain.Book;
import com.example.aaposBookStore.domain.BookRepository;
import com.example.aaposBookStore.domain.Category;
import com.example.aaposBookStore.domain.CategoryRepository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;



@DataJpaTest
public class AaposBookStoreJPATests {
    @Autowired
    private BookRepository repository;
    @Autowired
    private CategoryRepository crepository;
    @Autowired
    private AppUserRepository arepository;

    @Test
    public void createNewBookTest() {
        Book book = new Book("Sipuli", "Seppo Taalasmaa", 2023L, "123123123-123", 12.95, crepository.findByName("Scifi").get(0));
        repository.save(book);
        assertThat(book.getId()).isNotNull();
    }

     @Test
    public void createNewCategoryTest() {
        Category category = new Category("Horror");
        crepository.save(category);
        assertThat(crepository.findByName("Horror")).isNotNull();
    }

     @Test
    public void createNewAppUserTest() {
        AppUser testUser = new AppUser("Taneli", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
        arepository.save(testUser);
        assertThat(arepository.findByUsername("Taneli")).isNotNull();
    }

    @Test
    public void deleteBookTest() {
        repository.deleteById(0L);
        boolean bookExists = repository.existsById(0L);
        assertThat(bookExists).isFalse();
    }

     @Test
    public void deleteCategoryTest() {
        Category category = new Category("Horror");
        crepository.save(category);
        crepository.deleteById(3L);
        boolean categoryExists = crepository.existsById(3L);
        assertThat(categoryExists).isFalse();
    }

     @Test
    public void deleteAppUserTest() {
        AppUser testUser = new AppUser("Taneli", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
        arepository.save(testUser);
        boolean userExists = arepository.existsById(2L);
        //Check that the adding was succesfull
        assertThat(userExists).isTrue();
        //then delete and see if the delete was succesfull
        arepository.deleteById(2L);
        assertThat(userExists).isFalse();
    }

    @Test
    public void searchBookByTitleTest(){
         Book book = new Book("Sipuli", "Seppo Taalasmaa", 2023L, "123123123-123", 12.95, crepository.findByName("Scifi").get(0));
        repository.save(book);
       List <Book> findBook = repository.findByTitle("Sipuli");
       assertThat(findBook.size()).isEqualTo(1);
    }

    @Test
    public void searchCategoryTest() {
        Category category = new Category("Horror");
        
        crepository.save(category);
        
       List <Category> findCategory = crepository.findByName("Horror");
        assertThat(findCategory.size()).isEqualTo(1);
    }

     @Test
    public void searchAppUserTest() {
         AppUser testUser = new AppUser("Taneli", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
        arepository.save(testUser);
        
       AppUser findAppUser = arepository.findByUsername("Taneli");
        assertThat(findAppUser).isNotNull();;
    }
    
}
