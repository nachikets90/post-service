package com.qdb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_post")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String title;

	private String body;
	private int userId;

	private String fileName;

	@ManyToOne
	@JoinColumn(name = "document_id")
	private FileContent file;
}
