package com.mico.project;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mico.project.domain.User;
import com.mico.project.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestUserService {
	
	@Autowired
	UserServiceImpl userService;
	
	@Test
	public void getEmail_test() {
		String email = "degan8535@gmail.com";
		
		User user = userService.findByEmail(email);
		assertThat(user.getEmail(), is(email));
		System.out.println(user.getEmail());
	}
}
