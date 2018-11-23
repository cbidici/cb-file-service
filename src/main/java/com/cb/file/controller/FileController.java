package com.cb.file.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cb.file.entity.File;
import com.cb.file.exception.FileNotFoundException;
import com.cb.file.exception.FileWriteException;
import com.cb.file.service.FileService;

@RestController
@RequestMapping("/api/files")
public class FileController {

	@Autowired
	private FileService fileService;

	@PostMapping
	public File fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		return fileService.save(file.getOriginalFilename(), file.getInputStream());
	}

	@GetMapping("/download/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {
		Resource res = fileService.getFileAsResource(fileId);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + res.getFilename() + "\"")
				.body(res);
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(FileWriteException.class)
	public void fileWriteException() {

	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(FileNotFoundException.class)
	public void fileNotFoundException() {

	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IOException.class)
	public void fileReadException() {

	}

}
