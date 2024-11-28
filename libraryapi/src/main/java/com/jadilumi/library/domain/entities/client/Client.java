package com.jadilumi.library.domain.entities.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jadilumi.library.domain.entities.loan.Loan;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID clientId;

    @Column(nullable = false, unique = true)
    private String document;

    private String name;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnoreProperties("client")
    List<Loan> loans = new ArrayList<>();
}
