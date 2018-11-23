package com.cb.file.service;

import java.io.InputStream;

import org.springframework.core.io.Resource;

import com.cb.file.entity.File;

public interface FileService {

	public File save(String fileName, InputStream inputStream);

	public Resource getFileAsResource(String fileId);

}
