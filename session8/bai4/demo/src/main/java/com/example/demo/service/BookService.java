package com.example.demo.service;

import com.example.demo.dto.BookCreateDTO;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final Path root = Paths.get("uploads");

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        init();
    }

    public void init() {
        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public Book createBook(BookCreateDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setStock(dto.getStock());

        MultipartFile file = dto.getCoverImage();
        if (file != null && !file.isEmpty()) {
            try {
                String originalFilename = file.getOriginalFilename();
                String extension = "";
                if (originalFilename != null && originalFilename.contains(".")) {
                    extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                }
                String newFileName = UUID.randomUUID().toString() + extension;
                Files.copy(file.getInputStream(), this.root.resolve(newFileName));
                book.setCoverUrl(newFileName);
            } catch (Exception e) {
                throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
            }
        }

        return bookRepository.save(book);
    }

    public Book updateBook(Long id, com.example.demo.dto.BookUpdateStockDTO dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new com.example.demo.exception.ResourceNotFoundException("Book not found with id: " + id));
        book.setStock(dto.getStock());
        return bookRepository.save(book);
    }
}
