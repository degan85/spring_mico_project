package com.mico.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private UserDetailsService userDetailsService;
  
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
      return new BCryptPasswordEncoder();
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception{
    
    http
      .authorizeRequests()
        // �ش� url�� ����Ѵ�. 
      	.antMatchers("/home/**","/resources/**","/loginError","/registration").permitAll()
        // admin ������ ��� admin ������ �ִ� ����ڿ��Ը� ��� 
      	.antMatchers("/admin/**").hasAuthority("ADMIN")
      	// user ������ ��� user ������ �ִ� ����ڿ��Ը� ���
        .antMatchers("/user/**").hasAuthority("USER")
        .anyRequest().authenticated()
        .and()
      .formLogin()
        .loginPage("/login")
        .successHandler(new CustomAuthenticationSuccess()) // �α��� ���� �ڵ鷯 
        .failureHandler(new CustomAuthenticationFailure()) // �α��� ���� �ڵ鷯 
        .permitAll()
        .and()
      .logout()
        .permitAll()
        .and()
       .exceptionHandling().accessDeniedPage("/403"); // ������ ������� �ش� url�� �̵�
  }
  
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
  }  
}
