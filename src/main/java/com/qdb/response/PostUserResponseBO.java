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
public class PostUserResponseBO {

	private int postId;
	private String title;
	private String body;
	private int userId;
}
