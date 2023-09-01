package com.example.aaposBookStore.web;

import java.util.ArrayList;

import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class BookController {
   
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String testi(Model model) {
        return "index";
    }
}
