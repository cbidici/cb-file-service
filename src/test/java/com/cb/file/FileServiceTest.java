package com.cb.file;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.BeforeClass;
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

import com.cb.file.entity.File;
import com.cb.file.exception.FileNotFoundException;
import com.cb.file.service.FileService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {

	@Autowired
	private FileService fileService;

	private Resource testFile = new ClassPathResource("test.txt");
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@BeforeClass
	public static void beforeClass() {

	}

	@Before
	public void before() {

	}

	@Test
	public void shouldSaveFile() throws IOException {
		fileService.save("test.txt", testFile.getInputStream());
	}

	@Test
	public void shouldGetFile() throws IOException {
		InputStream isSave = testFile.getInputStream();
		InputStream isCompare = testFile.getInputStream();
		File file = fileService.save("test.txt", isSave);
		Resource resourceResult = fileService.getFileAsResource(file.getFileId());
		assertThat(IOUtil.readLines(resourceResult.getInputStream()), equalTo(IOUtil.readLines(isCompare)));
	}

	@Test
	public void shoudlThrowFileNotFound() {
		thrown.expect(FileNotFoundException.class);
		thrown.expectMessage("File Id not found [fileId:UNKNOWN]");
		fileService.getFileAsResource("UNKNOWN");
	}
}
