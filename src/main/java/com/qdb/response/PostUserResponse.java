package com.qdb.response;

import java.util.List;

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
public class PostUserResponse {

	private String fileName;
	//private String urlFile;
	private List<PostUserResponseBO> postList;
}
