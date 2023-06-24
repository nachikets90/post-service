package com.qdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qdb.entity.Post;
import com.qdb.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
