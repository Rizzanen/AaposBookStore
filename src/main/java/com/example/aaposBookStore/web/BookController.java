package com.example.aaposBookStore.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.security.access.prepost.PreAuthorize;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.boot.CommandLineRunner;
import com.example.aaposBookStore.domain.BookRepository;
import com.example.aaposBookStore.domain.CategoryRepository;
import com.example.aaposBookStore.domain.Book;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository crepository;

    @RequestMapping("/login")
	public String login() {
		return "login";
	}
   
    //shows as thymeleaf template
    @RequestMapping(value = "/booklist", method = RequestMethod.GET)
    public String listBooks(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    //shows as JSON using RESTful service
    @RequestMapping(value="/allBooks", method = RequestMethod.GET)
    public @ResponseBody List <Book> bookListRest(){
        return(List<Book>) repository.findAll();
    }

     //shows as JSON using RESTful service
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id")Long bookId){
        return repository.findById(bookId);
    }



    @RequestMapping(value = "/booklist", method = RequestMethod.POST)
    public String listBooksPost(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

   @RequestMapping(value = "/addbook", method = RequestMethod.GET)
    public String addBook(Model model) {
        model.addAttribute("newBook", new Book());
        model.addAttribute("categorys", crepository.findAll());
        return "addbook";
    }

     @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBook(Book book) {
       repository.save(book);
        return "redirect:booklist";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    repository.deleteById(bookId);
        
    return "redirect:../booklist";
    }

  @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long bookId,Model model) {
        Optional<Book> optionalBook = repository.findById(bookId);
        Book book = optionalBook.get();
        model.addAttribute("categorys", crepository.findAll());
        model.addAttribute("editedBook",book);
        
        repository.deleteById(bookId);
        return "editbook";
    }

     @RequestMapping(value = "/saveEdit", method = RequestMethod.POST)
    public String saveBookEdit(@ModelAttribute("editedBook") Book editedBook) {
       repository.save(editedBook);
        return "redirect:booklist";
    }
}
