package com.jadilumi.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jadilumi.library.domain.entities.book.Book;
import com.jadilumi.library.domain.entities.book.dto.BookDTO;
import com.jadilumi.library.domain.entities.book.dto.ResponseBookListDTO;
import com.jadilumi.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;


    @PostMapping
    public ResponseEntity<Book> createBook(
            @RequestPart(value = "data") String data,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        BookDTO receivedData = objectMapper.readValue(data, BookDTO.class);

        return new ResponseEntity<>(bookService.createBook(receivedData, file), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ResponseBookListDTO>> listBooks(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return new ResponseEntity<>(bookService.listBooks(title, page, size), HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> listBooks(@PathVariable(name = "bookId") UUID bookId) {
        return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> editBook(
            @PathVariable(name = "bookId") UUID bookId,
            @RequestPart(value = "data") String data,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        BookDTO receivedData = objectMapper.readValue(data, BookDTO.class);

        return new ResponseEntity<>(bookService.editBook(bookId, receivedData, file), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> editBook(
            @PathVariable(name = "bookId") UUID bookId
    ) {
        return new ResponseEntity<>(bookService.deleteBookById(bookId), HttpStatus.GONE);
    }
}

