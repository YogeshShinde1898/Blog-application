package com.practice.blog.payloads;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PostDto {

	private int id;

	@NotEmpty
	@Size(min = 4, message = "Title must be minimum of 4 characters !!")
	private String title;

	@NotBlank
	@Size(min = 10, message = "Content must be minimum of 10 characters !!")
	private String content;

	private String imageName;

	private Date createdDate;

	private CategoryDto category;

	private UserDto user;
}
