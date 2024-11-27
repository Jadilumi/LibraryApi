package com.jadilumi.library.domain.repository;

import com.jadilumi.library.domain.entities.loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoanRepository extends JpaRepository<Loan, UUID> {
}
