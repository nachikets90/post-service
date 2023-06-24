package com.qdb.dto;

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
public class FilePostDTO {

	private int postId;
	private String title;
	private String body;
	private int userId;
	private String fileName;
	// private String URL;

}
