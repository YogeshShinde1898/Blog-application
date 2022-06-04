package com.practice.blog.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "title", length = 50, nullable = false)
	private String title;

	@Column(name = "content", length = 10000, nullable = true)
	private String content;

	@Column(name = "image_name", length = 50, nullable = true)
	private String imageName;

	@Column(name= "created_date")
	private Date createdDate;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Category category;
}
