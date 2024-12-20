package com.jadilumi.library.domain.entities.users.dto;

import java.util.UUID;

import com.jadilumi.library.domain.entities.users.UserRole;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private UUID id;
	private String name;
	private String address;
	private String email;
	private String password;	
	private String phone;
	private UserRole role;
}