package com.example.microservice2.api;

import com.example.microservice2.domain.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @Value("${book.name}")
    private String bookName;

    @GetMapping("/{bookId}")
    public Book getBook(@PathVariable String bookId) {
        return new Book(bookId, bookName);
    }
}
