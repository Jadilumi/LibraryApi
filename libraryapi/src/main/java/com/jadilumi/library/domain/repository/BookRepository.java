package com.jadilumi.library.domain.repository;

import com.jadilumi.library.domain.entities.book.Book;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID>, JpaSpecificationExecutor<Book> {

    default Page<Book> buscarPorParametros(String title, Pageable pageable) {
        Specification<Book> specification = (Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            if (title == null || title.isEmpty()) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
        };

        return findAll(specification, pageable);
    }
}
