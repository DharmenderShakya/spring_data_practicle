package com.spring_data_jdbc_practicle.controlller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring_data_jdbc_practicle.entity.Book;
import com.spring_data_jdbc_practicle.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        service.addBook(book);
        return ResponseEntity.status(201).body("Book added successfully");
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(service.getAllBooks());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getBook(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Book not found");
        }
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Book book) {
        book.setId(id);
        service.updateBook(book);
        return ResponseEntity.ok("Book updated successfully");
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully");
    }
}
