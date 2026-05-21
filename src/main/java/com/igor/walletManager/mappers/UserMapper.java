package com.igor.walletManager.mappers;

import org.mapstruct.Mapper;

import com.igor.walletManager.dtos.users.UserCreateDTO;
import com.igor.walletManager.dtos.users.UserResponseDTO;
import com.igor.walletManager.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	User toEntity(UserCreateDTO dto);
	
	UserResponseDTO toDTO(User user);

}
