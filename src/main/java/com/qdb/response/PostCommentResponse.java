package com.qdb.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
public class PostCommentResponse {
	private int postId;
	private String fileName;
	private String postTitle;
	private String postBody;
	
	private List<PostResponseBO> commentList;


}
