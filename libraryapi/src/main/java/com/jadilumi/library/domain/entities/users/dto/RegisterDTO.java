package com.jadilumi.library.domain.entities.users.dto;

import com.jadilumi.library.domain.entities.users.UserRole;

public record RegisterDTO(String email, String password, UserRole role) {

}
