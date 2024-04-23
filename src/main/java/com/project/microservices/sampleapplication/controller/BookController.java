package com.project.microservices.sampleapplication.controller;

import com.project.microservices.sampleapplication.exception.BookNotFoundException;
import com.project.microservices.sampleapplication.exception.InvalidBookException;
import com.project.microservices.sampleapplication.model.Book;
import com.project.microservices.sampleapplication.service.BookService;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/books")
public class BookController {
    private static final Logger LOGGER = LogManager.getLogger(BookController.class);

    @Autowired
    private BookService bookService;
    @GetMapping
    public ResponseEntity<Map<String, List<Book>>> getAllBooks() {

        List<Book> books = bookService.getAllBooks();
        LOGGER.debug("Retreiving books {}",  books.toString());
        Map<String, List<Book>> responseMap = new HashMap<>();
        responseMap.put("books", books);
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Book>> getBookById(@PathVariable Long id) {
        Map<String, Book> book = new HashMap<>();
        Optional<Book> optionalBook = bookService.getBookById(id);
        if (optionalBook.isPresent()) {
            book.put("book", optionalBook.get());
            LOGGER.debug("Book by id : {} is {}", id, book);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            LOGGER.error("Book with id {} not found",id);
            throw new BookNotFoundException(id);
        }
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        if(book.getAuthor() == null || book.getTitle() == null){
            String message = "Book author or title cannot be null";
            throw new InvalidBookException(message);
        }
        Book createdBook = bookService.createOrUpdateBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Optional<Book> optionalBook = bookService.getBookById(id);
        if (optionalBook.isPresent()) {
            book.setId(id);
            if(book.getAuthor() == null || book.getTitle() == null){
                String message = "Book author or title cannot be null";
                throw new InvalidBookException(message);
            }
            Book updatedBook = bookService.createOrUpdateBook(book);
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
            throw new BookNotFoundException(id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBook(@PathVariable Long id) {
        Optional<Book> optionalBook = bookService.getBookById(id);
        Map<String, String> messageMap = new HashMap<>();
        if (optionalBook.isPresent()) {
            bookService.deleteBook(id);
            String message = "Book with id: " + id + " deleted successfully";
            messageMap.put("message", message);
            return new ResponseEntity<>(messageMap, HttpStatus.OK);
        } else {
            throw new BookNotFoundException(id);
        }
    }
}
