package com.practice.blog.services;

import java.util.List;

import com.practice.blog.payloads.PostDto;
import com.practice.blog.payloads.PostResponse;

public interface PostService {

	// CREATE
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// READ SINGLE
	PostDto getPost(Integer postId);

	// READ ALL
	PostResponse getPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	// UPDATE
	PostDto updatePost(PostDto postDto, Integer postId);

	// DELETE
	void deletePost(Integer postId);

	// Get all posts by Category
	List<PostDto> getPostsByCategory(Integer categoryId);

	// Get all posts by User
	List<PostDto> getPostsByUser(Integer userId);

	// Search post
	List<PostDto> searchPosts(String keywords);
}
