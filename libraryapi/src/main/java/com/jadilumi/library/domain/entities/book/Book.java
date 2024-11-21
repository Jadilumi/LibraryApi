package com.jadilumi.library.domain.entities.book;

import com.jadilumi.library.domain.entities.book.enums.Genre;
import com.jadilumi.library.domain.entities.loan.Loan;
import com.jadilumi.library.domain.entities.loan.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID bookId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    private String language;

    private String synopsis;

    private String edition;

    @Column(name = "price_per_day", nullable = false)
    private BigDecimal pricePerDay;

    @Column(name = "available_stock", nullable = false)
    private BigDecimal availableStock;

    @Column(name = "publish_year")
    private String publishYear;

    @Column(name = "purchase_cost", nullable = false)
    private BigDecimal purchaseCost;

    @Lob
    @Column(name = "cover_book_image")
    private byte[] coverBookImage;

    @Transient
    private Boolean availabilityStatus;

    @Transient
    private BigDecimal totalProfit = new BigDecimal("0");

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "book", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Loan> loans;


    public void loanBook() {
        this.availableStock = this.availableStock.subtract(BigDecimal.ONE);
    }

    public void returnBookToStock() {
        this.availableStock = this.availableStock.add(BigDecimal.ONE);
    }

    public void addLoan(Loan loan) {
        this.loans.add(loan);
        this.loanBook();
    }

    public void removeLoan(Loan loan) {
        this.loans.remove(loan);
        if (!loan.getLoanStatus().equals(LoanStatus.FINISHED)) {
            this.returnBookToStock();
        }
    }

    @PostLoad
    public void runTransientMethods() {
        this.verifyAvailabilityStatus();
        this.calculateBookTotalProfit();
    }


    private void verifyAvailabilityStatus() {
        this.availabilityStatus = !availableStock.equals(BigDecimal.ZERO);
    }


    private void calculateBookTotalProfit() {
        for (Loan loan : loans) {
            this.totalProfit = this.totalProfit.add(loan.getTotalLoanCost());
        }
    }
}
