package com.mico.project.service;

import com.mico.project.domain.User;

public interface UserService {
  void saveUser(User user,String[] roles);
  User findByUsername(String username);
}
