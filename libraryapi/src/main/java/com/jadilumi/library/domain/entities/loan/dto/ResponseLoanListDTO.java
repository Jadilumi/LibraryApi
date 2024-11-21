package com.jadilumi.library.domain.entities.loan.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ResponseLoanListDTO(
        UUID loanId,
        LocalDateTime loanStartDate,
        LocalDate estimateLoanReturnDate,
        String bookTitle,
        BigDecimal totalLoanCost
) {
}
