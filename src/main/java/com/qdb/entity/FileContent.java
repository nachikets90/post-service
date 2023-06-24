package com.qdb.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "t_file")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FileContent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String fileName;
	private String url;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Post> posts;
}
