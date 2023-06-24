package com.qdb.response;

import com.qdb.entity.FileContent;

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
public class PostResponse {
private int post_id;
	
	private String title;
	
	private String body;
	private int userId;
	private String fileName;
	
}
