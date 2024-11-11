package com.jadilumi.library.domain.entities.books.dto;

import com.jadilumi.library.domain.entities.books.enums.Genre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record BookDTO(

        @NotBlank(message = "O titulo não pode ser nulo!")
        String title,
        @NotBlank(message = "O autor não pode ser nulo!")
        String author,
        @NotBlank(message = "A editora não pode ser nulo!")
        String publisher,
        @NotNull
        Genre genre,
        String language,
        String synopsis,
        String edition,

        @NotNull(message = "O preço por dia de aluguel não pode ser nulo.")
        @PositiveOrZero(message = "O preço por dia de aluguel deve ser maior ou igual a 0.")
        BigDecimal pricePerDay,

        @NotNull(message = "O estoque disponivel não pode ser nulo.")
        @PositiveOrZero(message = "O estoque disponivel deve ser maior ou igual a 0.")
        BigDecimal availableStock,
        String publishYear,

        @NotNull(message = "o preço de aquisição não pode ser nulo.")
        @PositiveOrZero(message = "O preço de aquisição deve ser maior ou igual a 0.")
        BigDecimal purchaseCost,
        byte[] coverBookImage) {
}
