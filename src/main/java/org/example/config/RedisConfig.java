//package org.example.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//
///**
// * First, using the Jedis client, we’re defining a connectionFactory.
// * <p>
// * Then we defined a RedisTemplate using the jedisConnectionFactory. This can be used for querying
// * data with a custom repository
// */
//
///**
// * to check if redis is working ">> redis-cli ping"
// */
//@Configuration
//public class RedisConfig {
//
//  @Bean
//  public JedisConnectionFactory jedisConnectionFactory() {
//    return new JedisConnectionFactory();
//  }
//
//  @Bean
//  public RedisTemplate<Long, Object> redisTemplate() {
//    RedisTemplate<Long, Object> template = new RedisTemplate<>();
//    template.setConnectionFactory(jedisConnectionFactory());
//    return template;
//  }
//
//}
