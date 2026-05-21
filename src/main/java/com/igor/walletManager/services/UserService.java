package com.igor.walletManager.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.igor.walletManager.dtos.users.UserCreateDTO;
import com.igor.walletManager.dtos.users.UserUpdateDTO;
import com.igor.walletManager.dtos.users.UserResponseDTO;
import com.igor.walletManager.entity.User;
import com.igor.walletManager.mappers.UserMapper;
import com.igor.walletManager.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository repository;
	private final UserMapper mapper;

	public UserResponseDTO findById(Long id) {
		User user = findEntityById(id);
		return mapper.toDTO(user);
	}

	public List<UserResponseDTO> findAll() {
		return repository.findAll().stream().map(mapper::toDTO).toList();
	}

	public UserResponseDTO create(UserCreateDTO dto) {
		User user = mapper.toEntity(dto);
		User userCreated = repository.save(user);
		return mapper.toDTO(userCreated);
	}

	public UserResponseDTO update(Long id, UserUpdateDTO dto) {
		User user = findEntityById(id);

		if (!user.getEmail().equals(dto.email())) {

			if (repository.existsByEmail(dto.email())) {
				throw new RuntimeException("Email Já Cadastrado no Sistema");
			}
			user.setEmail(dto.email());
		}

		user.setNome(dto.nome());
		
		User userUpdated = repository.save(user);
		return mapper.toDTO(userUpdated);
	}
	
	public void toggleUserDeleted(Long id) {
		User user = findEntityById(id);
		
		if(user.getDeletedAt() == null) {
			user.setDeletedAt(LocalDateTime.now());
			repository.save(user);
			return;
		}
			user.setDeletedAt(null);
			repository.save(user);
		
		
	}

	private User findEntityById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("\"Usuario com Id:\"+id+\"Não Encontrado\""));
	}

}
 