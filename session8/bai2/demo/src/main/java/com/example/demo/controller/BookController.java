package com.example.demo.controller;

import com.example.demo.dto.BookCreateDTO;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@ModelAttribute BookCreateDTO bookCreateDTO) {
        try {
            Book createdBook = bookService.createBook(bookCreateDTO);
            return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @org.springframework.web.bind.annotation.PatchMapping("/update/{id}")
    public ResponseEntity<Book> updateBookStock(
            @org.springframework.web.bind.annotation.PathVariable Long id,
            @jakarta.validation.Valid @org.springframework.web.bind.annotation.RequestBody com.example.demo.dto.BookUpdateStockDTO dto) {
        Book updatedBook = bookService.updateBook(id, dto);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }
}
