package com.mico.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {
//	@Bean 
//	public JedisConnectionFactory connectionFactory() { 
//	
//		return new JedisConnectionFactory(); 
//	}
 
}

