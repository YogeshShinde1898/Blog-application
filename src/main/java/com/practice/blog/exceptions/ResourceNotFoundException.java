package com.practice.blog.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String resourceField;
	private long fieldValue;

	public ResourceNotFoundException(String resourceName, String resourceField, long fieldValue) {
		super(String.format("%s not found with %s : %s", resourceName, resourceField, fieldValue));
		this.resourceName = resourceName;
		this.resourceField = resourceField;
		this.fieldValue = fieldValue;
	}

}
