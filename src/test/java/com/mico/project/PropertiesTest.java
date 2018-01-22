package com.mico.project;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mico.project.storage.FileSystemStorageService;
import com.mico.project.storage.StorageProperties;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesTest {
	
	@Autowired
	private StorageProperties sp;
	
	@Autowired
	FileSystemStorageService fs;
	
	@Test
	public void filePathPropertiesTest() {
		assertThat(sp.getLocation(), is("D:\\temp\\spring")); 
	}
	
//	@Test
//	public void filePathPropertiesTest2() {
//		assertThat(fs.getPathTest(), is("D:\\temp\\spring")); 
//	}
}
