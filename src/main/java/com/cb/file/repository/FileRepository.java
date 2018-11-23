package com.cb.file.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cb.file.entity.File;

public interface FileRepository extends JpaRepository<File, String> {

	public Optional<File> findByFileId(String fileId);

}
