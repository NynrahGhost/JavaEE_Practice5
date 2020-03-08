package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Controller
public class IndexController {

	List<Book> books = new ArrayList<>();
	
    @RequestMapping({ "/", "" })
    public String index() {
        return "index";
    }
    
    @RequestMapping(value = "/books-list", method = RequestMethod.GET)
    public String booksList(Model model) {
        model.addAttribute("books", books);
        return "index";
    }
    
    @RequestMapping(value = "/book-form", method = RequestMethod.GET)
    public String bookForm(Model model) {
        return "bookForm";
    }

    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
    public String addNewBook(
    		@ModelAttribute(name = "title") String title,
    		@ModelAttribute(name = "isbn") String isbn,
    		@ModelAttribute(name = "author") String author
    ) {
    	books.add(new Book(title, isbn, author));
        return "redirect:/books-list";
    }

    @Getter
    @Setter
    @AllArgsConstructor
    class Book {
		private String title;
        private String isbn;
        private String author;
    }

}