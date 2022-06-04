package com.practice.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.blog.payloads.ApiResponse;
import com.practice.blog.payloads.UserDto;
import com.practice.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	// POST - Create User
	@PostMapping("")
	private ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createdUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createdUserDto, HttpStatus.CREATED);
	}

	// GET - Read User
	@GetMapping("{userId}")
	private ResponseEntity<UserDto> readUser(@PathVariable Integer userId) {
		return ResponseEntity.ok(this.userService.getUser(userId));
	}

	// GET - Read Users
	@GetMapping("")
	private ResponseEntity<List<UserDto>> readUser() {
		return ResponseEntity.ok(this.userService.getUsers());
	}

	// PUT - Update User
	@PutMapping("{userId}")
	private ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
		UserDto updatedUserDto = this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUserDto);
	}

	// DELETE - Delete User
	@DeleteMapping("{userId}")
	private ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK	);
	}
}
