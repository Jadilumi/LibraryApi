package com.jadilumi.library.service;

import com.jadilumi.library.domain.entities.books.Book;
import com.jadilumi.library.domain.entities.books.BookMapper;
import com.jadilumi.library.domain.entities.books.dto.BookDTO;
import com.jadilumi.library.domain.entities.books.dto.ResponseBookListDTO;
import com.jadilumi.library.domain.repository.BookRepository;
import com.jadilumi.library.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookService {

    private final UtilsService utilsService;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public Book createBook(BookDTO receivedData, MultipartFile file) throws IOException {
        Book createdBook = new Book();

        bookMapper.mapDTOToEntity(receivedData, createdBook);

        if (file != null) {
            this.utilsService.validateFile(file);

            createdBook.setCoverBookImage(file.getBytes());
        }

        return bookRepository.save(createdBook);
    }

    public Book editBook(UUID bookId, BookDTO receivedData, MultipartFile file) throws IOException {
        Book bookToEdit = bookRepository.findById(bookId).orElseThrow(() -> new CustomException("Book not found!", HttpStatus.NOT_FOUND));

        bookMapper.mapDTOToEntity(receivedData, bookToEdit);

        bookToEdit.setCoverBookImage(null);

        if (file != null) {
            this.utilsService.validateFile(file);
            bookToEdit.setCoverBookImage(file.getBytes());
        }
        return bookRepository.save(bookToEdit);
    }

    public Page<ResponseBookListDTO> listBooks(int page, int size) {
        return bookRepository.findAll(PageRequest.of(page, size)).map(book ->
                new ResponseBookListDTO(
                        book.getBookId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getGenre(),
                        book.getAvailableStock(),
                        book.getCoverBookImage()
                ));
    }

    public Book getBookById(UUID bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new CustomException("Book not found!", HttpStatus.NOT_FOUND));
    }

    public String deleteBookById(UUID bookId) {
        bookRepository.deleteById(bookId);
        return "Book deleted successfully";
    }
}
