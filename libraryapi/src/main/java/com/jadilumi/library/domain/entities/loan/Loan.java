package com.jadilumi.library.domain.entities.loan;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jadilumi.library.domain.entities.book.Book;
import com.jadilumi.library.domain.entities.client.Client;
import com.jadilumi.library.domain.entities.loan.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID loanId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    @JsonIgnore
    private Book book;

    @CreationTimestamp
    @Column(name = "loan_start_date", nullable = false)
    private LocalDateTime loanStartDate;

    @Column(name = "estimate_loan_return_date", nullable = false)
    private LocalDate estimateLoanReturnDate;

    @Column(name = "original_estimate_loan_return_date", nullable = false)
    private LocalDate originalEstimateLoanReturnDate;

    @Column(name = "loan_return_date")
    private LocalDate loanReturnDate;

    @Column(name = "interest_rate", nullable = false)
    private BigDecimal interestRatePerDay;

    @Column(name = "loan_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus = LoanStatus.IN_PROGRESS;

    @Transient
    private BigDecimal totalLoanCost;

    @Transient
    private Integer loanDays;

    @PostLoad
    private void runTransientMethods() {
        calculateLoanDays();
        calculateTotalLoanCost();
        updateLoanStatus();
    }

    private void calculateLoanDays() {
        this.loanDays = (int) ChronoUnit.DAYS.between(loanStartDate.toLocalDate(), LocalDate.now());

        if (this.loanDays.equals(0)) {
            this.loanDays = 1;
        }
    }

    private void calculateTotalLoanCost() {
        BigDecimal pricePerDay = book.getPricePerDay();
        BigDecimal baseCost = pricePerDay.multiply(BigDecimal.valueOf(loanDays));
        if (LocalDate.now().isAfter(estimateLoanReturnDate)) {
            long overdueDays = ChronoUnit.DAYS.between(estimateLoanReturnDate, LocalDate.now());
            BigDecimal overdueInterest = interestRatePerDay.multiply(BigDecimal.valueOf(overdueDays));
            this.totalLoanCost = baseCost.add(baseCost.multiply(overdueInterest));
        } else {
            this.totalLoanCost = baseCost;
        }
    }

    private void updateLoanStatus() {
        if (this.loanReturnDate != null) {
            this.loanStatus = LoanStatus.FINISHED;
        } else if (LocalDate.now().isAfter(this.estimateLoanReturnDate) && this.loanReturnDate == null) {
            this.loanStatus = LoanStatus.LATE;
        } else if (this.hasEstimateReturnDateChanged()) {
            this.loanStatus = LoanStatus.POSTPONED;
        }
    }

    private Boolean hasEstimateReturnDateChanged() {
        return !estimateLoanReturnDate.equals(originalEstimateLoanReturnDate);
    }

    public void returnBook() {
        this.loanReturnDate = LocalDate.now();
        this.book.returnBookToStock();
    }


}
