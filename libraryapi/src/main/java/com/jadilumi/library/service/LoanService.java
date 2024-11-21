package com.jadilumi.library.service;

import com.jadilumi.library.domain.entities.book.Book;
import com.jadilumi.library.domain.entities.loan.Loan;
import com.jadilumi.library.domain.entities.loan.LoanMapper;
import com.jadilumi.library.domain.entities.loan.dto.LoanDTO;
import com.jadilumi.library.domain.entities.loan.dto.ResponseLoanListDTO;
import com.jadilumi.library.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanMapper loanMapper;
    private final BookService bookService;

    public Loan createLoan(LoanDTO receivedData) {

        if (receivedData.estimateLoanReturnDate().isBefore(LocalDate.now())) {
            throw new CustomException("Estimate loan return date can't be before loan start date.", HttpStatus.CONFLICT);
        }

        if (receivedData.loanReturnDate() != null &&
                receivedData.loanReturnDate().isBefore(LocalDate.now())) {
            throw new CustomException("Loan return date can't be before loan start date.", HttpStatus.CONFLICT);
        }

        Loan createdLoan = new Loan();
        Book queriedBook = bookService.getBookById(receivedData.book().getBookId());

        loanMapper.mapDTOToEntity(receivedData, createdLoan);

        createdLoan.setBook(queriedBook);
        createdLoan.setOriginalEstimateLoanReturnDate(receivedData.estimateLoanReturnDate());

        queriedBook.addLoan(createdLoan);

        bookService.saveBookFromOutside(queriedBook);

        return createdLoan;
    }

    public Page<ResponseLoanListDTO> listLoansByBookId(UUID bookId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        List<ResponseLoanListDTO> loans = bookService.getBookById(bookId).getLoans()
                .stream()
                .map(loan -> new ResponseLoanListDTO(
                        loan.getLoanId(),
                        loan.getLoanStartDate(),
                        loan.getEstimateLoanReturnDate(),
                        loan.getBook().getTitle(),
                        loan.getTotalLoanCost()
                ))
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), loans.size());
        List<ResponseLoanListDTO> paginatedLoans = loans.subList(start, end);

        return new PageImpl<>(paginatedLoans, pageable, loans.size());
    }

    public Loan getLoanById(UUID bookId, UUID loanId) {
        return bookService.getBookById(bookId).getLoans()
                .stream()
                .filter(loan -> loan.getLoanId().equals(loanId))
                .findFirst()
                .orElseThrow(() -> new CustomException("Loan not found!", HttpStatus.NOT_FOUND));
    }

    public Page<ResponseLoanListDTO> listAllLoans(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        List<ResponseLoanListDTO> loans = bookService.listAllLoansFromOutside()
                .stream()
                .map(loan -> new ResponseLoanListDTO(
                        loan.getLoanId(),
                        loan.getLoanStartDate(),
                        loan.getEstimateLoanReturnDate(),
                        loan.getBook().getTitle(),
                        loan.getTotalLoanCost()
                ))
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), loans.size());
        List<ResponseLoanListDTO> paginatedLoans = loans.subList(start, end);

        return new PageImpl<>(paginatedLoans, pageable, loans.size());
    }

    public void deleteLoan(UUID bookId, UUID loanId) {
        Book queriedBook = bookService.getBookById(bookId);

        Loan queriedLoan = queriedBook.getLoans()
                .stream()
                .filter(loan -> loan.getLoanId().equals(loanId))
                .findFirst()
                .orElseThrow(() -> new CustomException("Loan not found!", HttpStatus.NOT_FOUND));

        queriedBook.removeLoan(queriedLoan);

        bookService.saveBookFromOutside(queriedBook);
    }

    public Loan finishLoan(UUID bookId, UUID loanId) {
        Book queriedBook = bookService.getBookById(bookId);

        Loan queriedLoan = queriedBook.getLoans()
                .stream()
                .filter(loan -> loan.getLoanId().equals(loanId))
                .findFirst()
                .orElseThrow(() -> new CustomException("Loan not found!", HttpStatus.NOT_FOUND));

        queriedLoan.returnBook();

        bookService.saveBookFromOutside(queriedBook);

        return queriedLoan;
    }

    public Loan editLoan(LoanDTO receivedData, UUID loanId) {
        Book queriedBook = bookService.getBookById(receivedData.book().getBookId());

        Loan queriedLoan = queriedBook.getLoans()
                .stream()
                .filter(loan -> loan.getLoanId().equals(loanId))
                .findFirst()
                .orElseThrow(() -> new CustomException("Loan not found!", HttpStatus.NOT_FOUND));

        if (receivedData.estimateLoanReturnDate().isBefore(LocalDate.now())) {
            throw new CustomException("Estimate loan return date can't be before loan start date.", HttpStatus.CONFLICT);
        }

        if (receivedData.loanReturnDate() != null &&
                receivedData.loanReturnDate().isBefore(LocalDate.now())) {
            throw new CustomException("Loan return date can't be before loan start date.", HttpStatus.CONFLICT);
        }

        loanMapper.mapDTOToEntity(receivedData, queriedLoan);

        bookService.saveBookFromOutside(queriedBook);

        return queriedLoan;
    }

}
