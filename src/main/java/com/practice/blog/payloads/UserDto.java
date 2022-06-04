package com.practice.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDto {

	private int id;

	@NotEmpty
	@Size(min = 4, message  = "Name must be minimum of 4 characters !!")
	private String name;

	@NotEmpty
	@Size(min = 6, message = "Password must be minimum of  6 characters !!")
	@Size(max = 12, message = "Password must be maximum of 15 characters !!")
	private String password;

	@Email(message = "Email address is not valid !!")
	@NotEmpty
	private String email;

	private String about;
}
