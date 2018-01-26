package com.mico.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mico.project.domain.Role;
import com.mico.project.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public User findByUsername(String username);
	public User findByEmail(String email);
	public User findById(Long id);
	public List<Role> findRolesByUsername(String username);
}
