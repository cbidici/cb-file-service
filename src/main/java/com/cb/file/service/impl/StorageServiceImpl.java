package com.cb.file.service.impl;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.cb.file.exception.FileWriteException;
import com.cb.file.service.StorageService;

@Service
public class StorageServiceImpl implements StorageService {

	@Value("${cb.file.storagepath}")
	private String storagePath;

	@Override
	public String store(String fileName, String directory, InputStream inputStream) {

		String dirPath = new StringBuilder().append(storagePath).append(File.separator).append(directory).toString();
		String filePath = new StringBuilder().append(dirPath).append(File.separator).append(fileName).toString();

		File file = new File(filePath);
		try {
			Files.createDirectories(new File(dirPath).toPath());
			Files.copy(inputStream, file.toPath());
		} catch (Exception e) {
			throw new FileWriteException(e);
		}
		return filePath;
	}

	@Override
	public Resource loadAsResource(String path) {
		return new FileSystemResource(new java.io.File(path));
	}

}
