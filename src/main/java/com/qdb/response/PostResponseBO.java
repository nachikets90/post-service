package com.qdb.response;

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
public class PostResponseBO {
	private String comment_message;
	
	private int commentID;
	private int userId;

}
