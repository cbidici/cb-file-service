package com.cb.file.service;

import java.io.InputStream;

import org.springframework.core.io.Resource;

public interface StorageService {

	public String store(String fileName, String directory, InputStream inputStream);

	public Resource loadAsResource(String path);

}
