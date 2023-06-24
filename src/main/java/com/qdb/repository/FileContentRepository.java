package com.qdb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qdb.entity.FileContent;

@Repository
public interface FileContentRepository extends JpaRepository<FileContent, String>{

	Optional<FileContent> findByfileName(String fileName);
}
