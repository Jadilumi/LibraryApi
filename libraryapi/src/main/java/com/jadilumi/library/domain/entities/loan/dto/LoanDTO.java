package com.jadilumi.library.domain.entities.loan.dto;

import com.jadilumi.library.domain.entities.book.Book;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record LoanDTO(

        @NotNull
        Book book,

        @NotNull
        LocalDate estimateLoanReturnDate,
        LocalDate loanReturnDate,

        @NotNull
        BigDecimal interestRatePerDay
) {
}
