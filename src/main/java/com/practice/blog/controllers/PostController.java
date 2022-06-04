package com.practice.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.practice.blog.config.AppConstants;
import com.practice.blog.payloads.ApiResponse;
import com.practice.blog.payloads.PostDto;
import com.practice.blog.payloads.PostResponse;
import com.practice.blog.services.FileService;
import com.practice.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	// POST - Create Post
	@PostMapping("user/{userId}/category/{categoryId}/posts")
	private ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto createdPostDto = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPostDto, HttpStatus.CREATED);
	}

	// GET - Read Post
	@GetMapping("posts/{postId}")
	private ResponseEntity<PostDto> readPost(@PathVariable Integer postId) {
		return ResponseEntity.ok(this.postService.getPost(postId));
	}

	// GET - Read All Posts
	@GetMapping("posts")
	private ResponseEntity<PostResponse> readPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIRECTION, required = false) String sortDir) {
		return ResponseEntity.ok(this.postService.getPosts(pageNumber, pageSize, sortBy, sortDir));
	}

	// GET - Post By User
	@GetMapping("/user/{userId}/posts")
	private ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
		List<PostDto> posts = this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// GET - Post By Category
	@GetMapping("/category/{categoryId}/posts")
	private ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
		List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// SEARCH - Post By Title
	@GetMapping("posts/search/{keywords}")
	private ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords) {
		List<PostDto> postDtos = this.postService.searchPosts(keywords);
		return ResponseEntity.ok(postDtos);
	}

	// PUT - Update Post
	@PutMapping("posts/{postId}")
	private ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatedPostDto = this.postService.updatePost(postDto, postId);
		return ResponseEntity.ok(updatedPostDto);
	}

	// DELETE - Delete Post
	@DeleteMapping("posts/{postId}")
	private ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully", true), HttpStatus.OK);
	}

	// Image Upload
	@PostMapping("posts/image/upload/{postId}")
	private ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException {
		PostDto postDto = this.postService.getPost(postId);
		String fileName = this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		return ResponseEntity.ok(this.postService.updatePost(postDto, postId));
	}

	// Image Download
	@GetMapping(value = "posts/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	private void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
