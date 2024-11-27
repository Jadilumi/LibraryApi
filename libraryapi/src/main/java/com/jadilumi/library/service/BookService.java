package com.jadilumi.library.service;

import com.jadilumi.library.domain.entities.book.Book;
import com.jadilumi.library.domain.entities.book.BookMapper;
import com.jadilumi.library.domain.entities.book.dto.BookDTO;
import com.jadilumi.library.domain.entities.book.dto.ResponseBookListDTO;
import com.jadilumi.library.domain.entities.loan.Loan;
import com.jadilumi.library.domain.repository.BookRepository;
import com.jadilumi.library.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public Page<ResponseBookListDTO> listBooks(String title, int page, int size) {
        return bookRepository.buscarPorParametros(title, PageRequest.of(page, size)).map(book ->
                new ResponseBookListDTO(
                        book.getBookId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getGenre(),
                        book.getAvailableStock(),
                        book.getCoverBookImage()
                ));
    }

    public List<Loan> listAllLoansFromOutside() {
        return bookRepository.findAll()
                .stream()
                .flatMap(book -> book.getLoans().stream())
                .collect(Collectors.toList());
    }

    public Book getBookById(UUID bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new CustomException("Book not found!", HttpStatus.NOT_FOUND));
    }

    public String deleteBookById(UUID bookId) {
        bookRepository.deleteById(bookId);
        return "Book deleted successfully";
    }

    public void saveBookFromOutside(Book book) {
        bookRepository.save(book);
    }
}
