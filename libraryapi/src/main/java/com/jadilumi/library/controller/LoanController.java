package com.jadilumi.library.controller;

import com.jadilumi.library.domain.entities.loan.Loan;
import com.jadilumi.library.domain.entities.loan.dto.LoanDTO;
import com.jadilumi.library.domain.entities.loan.dto.ResponseLoanListDTO;
import com.jadilumi.library.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;


    @PostMapping
    public ResponseEntity<Loan> createLoan(
            @RequestBody @Valid LoanDTO receivedData
    ) {
        return new ResponseEntity<>(loanService.createLoan(receivedData), HttpStatus.CREATED);
    }

    @GetMapping("/view/{bookId}")
    public ResponseEntity<Page<ResponseLoanListDTO>> listLoansByBookId(
            @PathVariable UUID bookId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size

    ) {
        return new ResponseEntity<>(loanService.listLoansByBookId(bookId, page, size), HttpStatus.OK);
    }

    @GetMapping("/view/all")
    public ResponseEntity<Page<ResponseLoanListDTO>> listAllLoans(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size

    ) {
        return new ResponseEntity<>(loanService.listAllLoans(page, size), HttpStatus.OK);
    }

    @GetMapping("/view/{bookId}/{loanId}")
    public ResponseEntity<Loan> getLoanById(
            @PathVariable UUID bookId,
            @PathVariable UUID loanId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size

    ) {
        return new ResponseEntity<>(loanService.getLoanById(bookId, loanId), HttpStatus.OK);
    }

    @PutMapping("/edit/{loanId}")
    public ResponseEntity<Loan> editLoan(
            @RequestBody @Valid LoanDTO receivedData,
            @PathVariable UUID loanId
    ) {
        return new ResponseEntity<>(this.loanService.editLoan(receivedData, loanId), HttpStatus.OK);
    }

    @PutMapping("/edit/{bookId}/{loanId}")
    public ResponseEntity<Loan> finishLoan(
            @PathVariable UUID bookId,
            @PathVariable UUID loanId
    ) {
        return new ResponseEntity<>(this.loanService.finishLoan(bookId, loanId), HttpStatus.OK);
    }

    @DeleteMapping("/del/{bookId}/{loanId}")
    public ResponseEntity<String> deleteLoan(
            @PathVariable UUID bookId,
            @PathVariable UUID loanId
    ) {
        this.loanService.deleteLoan(bookId, loanId);
        return new ResponseEntity<>("Loan deleted successfully", HttpStatus.GONE);
    }

}
