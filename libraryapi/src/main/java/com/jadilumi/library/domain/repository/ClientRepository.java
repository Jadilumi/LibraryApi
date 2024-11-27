package com.jadilumi.library.domain.repository;

import com.jadilumi.library.domain.entities.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}
