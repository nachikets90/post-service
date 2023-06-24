package com.qdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qdb.dto.CommentDTO;
import com.qdb.dto.PostRequest;
import com.qdb.dto.UserDTO;
import com.qdb.entity.User;
import com.qdb.response.CommentResponse;
import com.qdb.response.PostCommentResponse;
import com.qdb.response.PostResponse;
import com.qdb.response.PostUserResponse;
import com.qdb.service.PostService;

@RestController
@RequestMapping("/api/v1/posts/")
public class PostController {

	@Autowired
	private PostService service;

	@PostMapping("/post")
	public ResponseEntity<PostResponse> addPost(@RequestBody PostRequest postDTO) throws Exception {

		PostResponse post = service.createPost(postDTO);
		return new ResponseEntity<PostResponse>(post, HttpStatus.CREATED);

	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<PostResponse> getPostByID(@PathVariable int postId) throws Exception {

		PostResponse response = service.getPostByID(postId);
		return new ResponseEntity<PostResponse>(response, HttpStatus.CREATED);

	}

	@DeleteMapping("/post/{postId}")
	public ResponseEntity<String> deletPostByID(@PathVariable int postId) throws Exception {

		String response = service.deltePostByID(postId);
		return new ResponseEntity<String>(response, HttpStatus.OK);

	}

	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) throws Exception {

		User user = service.createUser(userDTO);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);

	}

	@GetMapping("/file/{fileName}")
	public ResponseEntity<PostUserResponse> showAllPosts(@PathVariable String fileName) throws Exception {

		PostUserResponse obj = service.showPost(fileName);
		return new ResponseEntity<PostUserResponse>(obj, HttpStatus.OK);

	}

	@PostMapping("/comment")
	public ResponseEntity<CommentResponse> createComment(@RequestBody CommentDTO commDto) throws Exception {

		CommentResponse response = service.createComment(commDto);
		return new ResponseEntity<CommentResponse>(response, HttpStatus.CREATED);

	}

	@GetMapping("/comment/{postId}")
	public ResponseEntity<PostCommentResponse> getAllPosts(@PathVariable int postId) throws Exception {

		PostCommentResponse response = service.showAllPosts(postId);
		return new ResponseEntity<PostCommentResponse>(response, HttpStatus.CREATED);

	}

}
