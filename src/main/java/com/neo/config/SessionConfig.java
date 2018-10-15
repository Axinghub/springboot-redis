package com.neo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
/*
*分布式系统中，sessiong共享有很多的解决方案，其中托管到缓存中应该是最常用的方案之一
* 弄了好几天，老是报错-------java.lang.IllegalStateException: Error processing condition on org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration.propertySourcesPlaceholderConfigurer
* 是这个配置的问题
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.9.5</version>
</dependency>
* 最后改为
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
* */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 5)
public class SessionConfig {
	
}