package com.practice.blog.services;

import java.util.List;

import com.practice.blog.payloads.UserDto;

public interface UserService {
	
	// CREATE
	UserDto createUser(UserDto userDto);
	
	// READ SINGLE
	UserDto getUser(Integer userId);
	
	// READ ALL
	List<UserDto> getUsers();

	// UPDATE
	UserDto updateUser(UserDto userDto, Integer userId);

	// DELETE
	void deleteUser(Integer userId);
}
