package com.jadilumi.library.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jadilumi.library.domain.entities.users.User;
import com.jadilumi.library.domain.entities.users.UserMapper;
import com.jadilumi.library.domain.entities.users.dto.UserDTO;
import com.jadilumi.library.domain.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	private final UserMapper userMapper = UserMapper.INSTANCE;

	// Criar um novo usuário
	public UserDTO createUser(UserDTO userDTO) {
		User user = userMapper.toEntity(userDTO);
		User savedUser = userRepository.save(user);
		return userMapper.toDTO(savedUser);
	}

	// Obter um usuário por ID
	public UserDTO getUserById(UUID userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		return userMapper.toDTO(user);
	}

	// Obter todos os usuários
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
	}

	// Atualizar um usuário
	public UserDTO updateUser(UUID userId, UserDTO userDTO) {
		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

		existingUser.setName(userDTO.getName());
		existingUser.setAddress(userDTO.getAddress());
		existingUser.setEmail(userDTO.getEmail());
		existingUser.setPhone(userDTO.getPhone());

		User updatedUser = userRepository.save(existingUser);
		return userMapper.toDTO(updatedUser);
	}

	// Deletar um usuário
	public void deleteUser(UUID userId) {
		userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		userRepository.deleteById(userId);
	}
}