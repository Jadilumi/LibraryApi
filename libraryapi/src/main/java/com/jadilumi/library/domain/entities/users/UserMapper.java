package com.jadilumi.library.domain.entities.users;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jadilumi.library.domain.entities.users.dto.UserDTO;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Mapper(componentModel = "Spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	UserDTO toDTO(User updatedUser);

	User toEntity(UserDTO userDTO);
}
