package com.qdb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qdb.dto.CommentDTO;
import com.qdb.dto.PostRequest;
import com.qdb.exception.ServerDownException;
import com.qdb.response.CommentResponse;
import com.qdb.response.PostResponse;
import com.qdb.service.PostService;

@SpringBootTest
class PostServiceApplicationTests {

	@Autowired
	private PostService service;
	
	private static int  postID;
	
	@Test
	void testAddPost() throws ServerDownException {
		PostRequest postDTO=createDuummyPost();
		PostResponse post = service.createPost(postDTO);
		postID=(post.getPost_id());
	}
	@Test
	void testgetPostByID( ) throws ServerDownException {
		
		PostResponse response =service.getPostByID(postID);
		
	}
	
	@Test
	void testDeletPostByID( ) throws ServerDownException {
		
		service.deltePostByID(postID);;
		
	}
	@Test
	void testCreateComment() throws ServerDownException {
		CommentDTO commentDTO=createDuummyComment();
		CommentResponse post = service.createComment(commentDTO);
	}

	private PostRequest createDuummyPost() {
		return PostRequest.builder().userId(1).title("Dummy Title").body("Dummy body").fileName("Adhar.pdf").build();
	}
	public int getPostID() {
		return postID;
	}
	public void setPostID(int postID) {
		this.postID = postID;
	}
	private CommentDTO createDuummyComment() {
		return CommentDTO.builder().userId(1).postID(1).comment_message("FirstComment").build();
	}

}
