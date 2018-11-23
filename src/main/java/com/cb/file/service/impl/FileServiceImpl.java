package com.cb.file.service.impl;

import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.cb.file.entity.File;
import com.cb.file.exception.FileNotFoundException;
import com.cb.file.repository.FileRepository;
import com.cb.file.service.FileService;
import com.cb.file.service.StorageService;

@Service
public class FileServiceImpl implements FileService {

	@Value("${cb.file.geturl}")
	private String geturl;

	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private StorageService storageService;

	private File findByFileId(String fileId) {
		Optional<File> fileOpt = fileRepository.findByFileId(fileId);
		if (!fileOpt.isPresent()) {
			throw new FileNotFoundException("File Id not found [fileId:" + fileId + "]");
		}
		return fileOpt.get();
	}

	@Override
	public File save(String fileName, InputStream inputStream) {

		String fileId = UUID.randomUUID().toString();
		String filePath = storageService.store(fileName, fileId, inputStream);

		File file = new File();
		file.setFileId(fileId);
		file.setName(fileName);
		file.setPath(filePath);
		file.setUrl(geturl + fileId);
		return fileRepository.save(file);
	}

	@Override
	public Resource getFileAsResource(String fileId) {
		File file = findByFileId(fileId);
		Resource result = null;
		result = storageService.loadAsResource(file.getPath());
		return result;
	}

}
