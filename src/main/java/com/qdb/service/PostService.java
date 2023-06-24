package com.qdb.service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.qdb.dto.CommentDTO;
import com.qdb.dto.FileDTO;
import com.qdb.dto.FilePostDTO;
import com.qdb.dto.PostRequest;
import com.qdb.dto.UserDTO;
import com.qdb.entity.Comment;
import com.qdb.entity.FileContent;
import com.qdb.entity.Post;
import com.qdb.entity.User;
import com.qdb.exception.ServerDownException;
import com.qdb.repository.CommentRepository;
import com.qdb.repository.FileContentRepository;
import com.qdb.repository.PostRepository;
import com.qdb.repository.UserRepository;
import com.qdb.response.CommentResponse;
import com.qdb.response.PostCommentResponse;
import com.qdb.response.PostResponse;
import com.qdb.response.PostResponseBO;
import com.qdb.response.PostUserResponse;
import com.qdb.response.PostUserResponseBO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private FileContentRepository fileContentRepo;

	@Autowired
	private CommentRepository comRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${postAPIURL}")
	private String postAPIURL;

	@Value("${fetchFileURL}")
	private String fetchFileURL;

	public PostResponse createPost(PostRequest dto) throws ServerDownException {

		// fetch Documents
		Optional<FileContent> fileOptional = fileContentRepo.findByfileName(dto.getFileName());
		if (fileOptional.isPresent()) {

			FileContent content = fileOptional.get();
			// log.info("1 st loop Fetched from document Service->"+content);
			Post post = postObject(dto);
			post.setFile(content);
			postRepository.save(post);
			PostResponse response = postPostResponseObject(post);
			
			return response;
		} else {
			/// log.info("2nd loop Fetched from document Service->");
			String fileURL = fetchFileURL + dto.getFileName();
			ResponseEntity<FileDTO> fileDtoResponse = restTemplate.getForEntity(fileURL, FileDTO.class);
			if (fileDtoResponse.getStatusCode() == HttpStatus.FOUND) {
				FileDTO respFile = fileDtoResponse.getBody();
				// log.info("Fetched from document Service->"+respFile);

				FileContent content = FileContent.builder().id(respFile.getId()).posts(null)
						.fileName(respFile.getName()).url(respFile.getUrl()).build();

				Post post = Post.builder().title(dto.getTitle()).fileName(dto.getFileName()).body(dto.getBody())
						.file(content).userId(dto.getUserId()).build();

				content.setPosts(Arrays.asList(post));
				fileContentRepo.save(content);
				return returPostResponse(post);
			} else {
				Post post = postObject(dto);
				postRepository.save(post);
				return returPostResponse(post);
			}

		}

	}
	private PostResponse returPostResponse(Post post) {
		PostResponse response = PostResponse.builder().post_id(post.getId()).title(post.getTitle())
				.post_id(post.getId()).body(post.getBody()).userId(post.getUserId())
				.fileName(post.getFileName()).build();
		return response;
	}
	

	public PostResponse getPostByID(int postId) throws ServerDownException {

		Post post = postRepository.findById(postId).get();
		if (Objects.nonNull(post)) {
			PostResponse response = postPostResponseObject(post);
			return response;
		} else
			return null;

	}

	public String deltePostByID(int postId) throws ServerDownException {

		Post post = postRepository.findById(postId).get();
		String msg = null;
		if (Objects.nonNull(post)) {
			postRepository.deleteById(postId);
			msg = "Post with ID deleted " + postId;
			return msg;
		} else
			return msg = "Did not find any post with id " + postId;

	}

	public User createUser(UserDTO dto) throws ServerDownException {

		User user = User.builder().email(dto.getEmail()).build();
		userRepo.save(user);
		return user;
	}

	public CommentResponse createComment(CommentDTO dto) throws ServerDownException {

		Comment comment = Comment.builder().comment_message(dto.getComment_message()).postId(dto.getPostID())
				.userId(dto.getUserId()).build();
		comRepository.save(comment);
		return getcommentResponse(comment);
	}

	public PostUserResponse showPost(String fileName) throws ServerDownException {

		List<Post> list = postRepository.findByfileName(fileName);
		List<PostUserResponseBO> boList = list.stream().map(post -> getPostUserResponseBO(post))
				.collect(Collectors.toList());
		PostUserResponse response = new PostUserResponse();
		response.setPostList(boList);
		response.setFileName(fileName);
		// response.setUrlFile(fileName);

		return response;

	}

	private PostUserResponseBO getPostUserResponseBO(Post post) {
		return PostUserResponseBO.builder().postId(post.getId()).title(post.getTitle()).body(post.getBody())
				.userId(post.getUserId()).build();
	}

	public PostCommentResponse showAllPosts(int postId) throws ServerDownException {

		List<Comment> list = comRepository.findBypostId(postId);
		List<PostResponseBO> listBo = list.stream().map(comment -> getPostResponseBO(comment))
				.collect(Collectors.toList());
		Post post = postRepository.findById(postId).get();

		// PostCommentResponse response=getPostCommentResponse(list);
		PostCommentResponse response = new PostCommentResponse();
		response.setPostId(postId);
		response.setPostTitle(post.getTitle());
		response.setCommentList(listBo);
		response.setPostBody(post.getBody());
		response.setFileName(post.getFileName());

		return response;

	}

	private PostResponseBO getPostResponseBO(Comment comment) {

		return PostResponseBO.builder().comment_message(comment.getComment_message()).commentID(comment.getId())
				.userId(comment.getUserId()).build();
	}

	private Post postObject(PostRequest dto) {
		Post post = Post.builder().title(dto.getTitle()).fileName(dto.getFileName()).body(dto.getBody())

				.userId(dto.getUserId()).build();
		return post;
	}

	private PostResponse postPostResponseObject(Post post) {
		PostResponse response = PostResponse.builder().post_id(post.getId()).title(post.getTitle())
				.post_id(post.getId()).body(post.getBody()).userId(post.getUserId()).fileName(post.getFileName())
				.build();
		return response;
	}

	private CommentResponse getcommentResponse(Comment comment) {
		CommentResponse response = CommentResponse.builder().commentID(comment.getId())
				.comment_message(comment.getComment_message()).userId(comment.getUserId()).postID(comment.getPostId())
				.build();
		return response;
	}

}
