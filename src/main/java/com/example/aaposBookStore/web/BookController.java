package com.example.aaposBookStore.web;

import java.util.ArrayList;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.boot.CommandLineRunner;
import com.example.aaposBookStore.domain.BookRepository;
import com.example.aaposBookStore.domain.Book;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;
   
    @RequestMapping(value = "/listBooks", method = RequestMethod.GET)
    public String books(Model model) {
        model.addAttribute("books", repository.findAll());
        return "listBooks";
    }

    @RequestMapping(value = "/listBooks", method = RequestMethod.POST)
    public String booksPost(Model model) {
        model.addAttribute("books", repository.findAll());
        return "listBooks";
    }

   
}
