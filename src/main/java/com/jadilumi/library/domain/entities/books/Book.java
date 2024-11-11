package com.jadilumi.library.domain.entities.books;

import com.jadilumi.library.domain.entities.books.enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private BigDecimal totalProfit;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    //todo lista com as trasações de aluguel (ID do leitor que pegou, data que pegou, data que tem que devolver e data que devolvou (quando devolver ele vai alterar a quantidade de estoque pelo método returnBook e quando emprestar vai diminui pelo método loanBook)

    public void loanBook() {
        this.availableStock = this.availableStock.subtract(BigDecimal.ONE);
    }

    public void returnBook() {
        this.availableStock = this.availableStock.add(BigDecimal.ONE);
    }

    @PostLoad
    public void runTransientMethods() {
        this.verifyAvailabilityStatus();
    }


    private void verifyAvailabilityStatus() {
        this.availabilityStatus = !availableStock.equals(BigDecimal.ZERO);
    }

    //todo método pra calcular o lucro total que o livro deu subtraindo o custo do livro da soma do total da lista de emprestimo.
}
