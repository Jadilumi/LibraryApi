package com.jadilumi.library.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jadilumi.library.domain.entities.users.User;
import com.jadilumi.library.domain.entities.users.dto.UserDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

}
