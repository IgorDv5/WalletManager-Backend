package com.igor.walletManager.mappers;

import org.mapstruct.Mapper;

import com.igor.walletManager.dtos.users.UserCreateDTO;
import com.igor.walletManager.dtos.users.UserResponseDTO;
import com.igor.walletManager.dtos.users.UserUpdateDTO;
import com.igor.walletManager.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserResponseDTO toDTO(User user);
	
	User toEntity(UserCreateDTO dto);

	User toEntity(UserUpdateDTO dto);
}
