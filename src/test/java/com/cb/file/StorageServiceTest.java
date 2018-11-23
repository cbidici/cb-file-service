package com.cb.file;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.internal.util.io.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import com.cb.file.exception.FileNotFoundException;
import com.cb.file.exception.FileWriteException;
import com.cb.file.service.StorageService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StorageServiceTest {

	@Autowired
	private StorageService storageService;

	private Resource testFile = new ClassPathResource("test.txt");
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void shouldStoreFile() throws IOException {
		String path = storageService.store("test.txt", "test", testFile.getInputStream());
		File f = new File(path);
		f.delete();
	}
	
	@Test
	public void shouldLoadAsSource() throws IOException {
		InputStream isStore = testFile.getInputStream();
		InputStream isCompare = testFile.getInputStream();
		String path = storageService.store("test.txt", "test", isStore);
		Resource resourceResult = storageService.loadAsResource(path);
		assertThat(IOUtil.readLines(resourceResult.getInputStream()), equalTo(IOUtil.readLines(isCompare)));
		File f = new File(path);
		f.delete();
	}
	
	@Test
	public void shouldThrowFileWrite() throws IOException {
		thrown.expect(FileWriteException.class);
		storageService.store("test.txt", " ", testFile.getInputStream());
	}
}
