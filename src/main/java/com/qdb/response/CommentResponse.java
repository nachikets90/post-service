package com.qdb.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CommentResponse {
    private int commentID;
	private int userId;
	private String comment_message;
	private int postID;
}
