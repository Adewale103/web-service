package com.payu.web_service.controller;

import com.payu.web_service.dto.BookDto;
import com.payu.web_service.dto.BookRequest;
import com.payu.web_service.dto.PagedResponse;
import com.payu.web_service.service.WebService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.payu.web_service.utils.Constants.DEFAULT_PAGE;
import static com.payu.web_service.utils.Constants.DEFAULT_SIZE;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
@Slf4j
public class WebController {
    private final WebService bookService;


    @GetMapping
    public String listBooks(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        try {
        PagedResponse response = bookService.getAllBooks(page, size);
        model.addAttribute("editing", false);
        model.addAttribute("books", response.getBooks());
        model.addAttribute("currentPage", response.getPageNumber());
        model.addAttribute("totalPages", response.getTotalPages());
        } catch (Exception ex) {
            model.addAttribute("error", "Failed to fetch books: " + ex.getMessage());
        }
        return "books";
    }

    @PostMapping("")
    public String addBook(@ModelAttribute BookRequest bookRequest, Model model) {
        try {
            bookService.createBook(bookRequest);
            return "redirect:/books";
        } catch (Exception ex) {
            model.addAttribute("error", "Failed to add book: " + ex.getMessage());
            PagedResponse response = bookService.getAllBooks(DEFAULT_PAGE, DEFAULT_SIZE);
            model.addAttribute("editing", false);
            model.addAttribute("books", response.getBooks());
            model.addAttribute("currentPage", response.getPageNumber());
            model.addAttribute("totalPages", response.getTotalPages());
            return "books";
        }
    }

    @GetMapping("/fetch")
    public String fetchBook(@RequestParam String bookId, Model model) {
        try {
            BookDto book = bookService.getBookById(bookId);
            model.addAttribute("editBook", book);
            model.addAttribute("editing", true);
            model.addAttribute("books", bookService.getAllBooks(DEFAULT_PAGE, DEFAULT_SIZE).getBooks());
        } catch (Exception ex) {
            model.addAttribute("error", "Failed to fetch book details: " + ex.getMessage());
        }
        return "books";
    }

    @PostMapping("/update")
    public String updateBook(@RequestParam String bookId, @ModelAttribute BookRequest bookRequest, Model model) {
        try {
            bookService.updateBook(bookId, bookRequest);
            return "redirect:/books";
        } catch (Exception ex) {
            model.addAttribute("error", "Failed to update book: " + ex.getMessage());
            model.addAttribute("books", bookService.getAllBooks(DEFAULT_PAGE, DEFAULT_SIZE).getBooks());
            model.addAttribute("editing", false);
            return "books";
        }
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam String bookId) {
        bookService.deleteBook(bookId);
        return "redirect:/books";
    }
}
