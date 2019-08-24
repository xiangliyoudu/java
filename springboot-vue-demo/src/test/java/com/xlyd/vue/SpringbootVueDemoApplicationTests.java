package com.xlyd.vue;

import java.io.File;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootVueDemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	
	
	@Test
	public void testClassPathResource() throws Exception {
		String jsonFileName = "users.json";
		ClassPathResource classPathResource = new ClassPathResource(jsonFileName);
		File f = classPathResource.getFile();
		System.out.println(f.getAbsolutePath());
        InputStream inputStream = classPathResource.getInputStream();
        byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
        String jsonString = new String(bytes);
        System.out.println(jsonString);
	}
	

}













