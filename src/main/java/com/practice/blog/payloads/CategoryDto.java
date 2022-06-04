package com.practice.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class CategoryDto {

	private int id;

	@NotEmpty
	@Size(min = 4, message = "Title must be minimum of 4 characters !!")
	private String title;

	@NotBlank
	@Size(min = 10, message = "Description must be minimum of 10 characters !!")
	private String description;
}
