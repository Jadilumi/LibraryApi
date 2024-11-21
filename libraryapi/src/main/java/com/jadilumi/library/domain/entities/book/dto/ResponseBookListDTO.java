package com.jadilumi.library.domain.entities.book.dto;

import com.jadilumi.library.domain.entities.book.enums.Genre;

import java.math.BigDecimal;
import java.util.UUID;

public record ResponseBookListDTO(
        UUID bookId,
        String title,
        String author,
        Genre genre,
        BigDecimal availableStock,
        byte[] coverBookImage
) {
}
