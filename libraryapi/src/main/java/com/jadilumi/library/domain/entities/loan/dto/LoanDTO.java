package com.jadilumi.library.domain.entities.loan.dto;

import com.jadilumi.library.domain.entities.book.Book;
import com.jadilumi.library.domain.entities.client.Client;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LoanDTO(

        @NotNull
        Book book,

        @NotNull
        Client client,

        @NotNull
        LocalDate estimateLoanReturnDate,
        LocalDate loanReturnDate,

        @NotNull
        BigDecimal interestRatePerDay
) {
}
