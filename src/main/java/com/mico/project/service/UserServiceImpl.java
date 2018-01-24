package com.mico.project.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mico.project.domain.Role;
import com.mico.project.domain.User;
import com.mico.project.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	@Autowired
    private UserRepository userRepository;
  
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override  
    public void saveUser(User user,String[] roles){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		Set<Role> rolesSet = new HashSet<Role>();
		for(String role:roles){
			rolesSet.add(new Role(role));
		}
		user.setRoles(rolesSet);
		userRepository.save(user);
    }
    
    @Override
    public User findByUsername(String username) {
    	return userRepository.findByUsername(username);
    }
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
  
}
