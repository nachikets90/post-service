package com.qdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qdb.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

	List<Post> findByfileName(String fileName);
}
