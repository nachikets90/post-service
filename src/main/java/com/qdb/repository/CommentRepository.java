package com.qdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qdb.entity.Comment;
import com.qdb.entity.Post;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	 List<Comment> findBypostId(int postId);
}
