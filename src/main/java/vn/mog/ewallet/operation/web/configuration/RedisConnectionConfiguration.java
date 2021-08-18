package vn.mog.ewallet.operation.web.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConnectionConfiguration {

  @Value("${spring.redis.host}")
  private String redisHost;

  @Value("${spring.redis.port}")
  private Integer redisPort;

  @SuppressWarnings("deprecation")
  @Bean
  public JedisConnectionFactory jedisConnectionFactory() {
    JedisConnectionFactory connection = new JedisConnectionFactory();
    connection.setHostName(redisHost);
    connection.setPort(redisPort);
    return connection;
  }

  @Bean
  public RedisTemplate<Object, Object> redisTemplate() {
    RedisTemplate<Object, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory());
    return template;
  }
}
