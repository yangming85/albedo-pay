package com.qingju.java.config;

import com.google.common.collect.Lists;
import com.qingju.java.common.pay.Constant;
import com.qingju.java.listener.RedisMessageListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * Created by lijie on 2017/5/15.
 *
 * @author 837158334@qq.com
 */
@Configuration
public class RedisConfigration {

    @Bean
    public RedisMessageListener redisMessageListener(RedisTemplate redisTemplate){
        return new RedisMessageListener(redisTemplate);
    }

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListener messageListener) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListener, Lists.newArrayList(
                new PatternTopic(Constant.REDIS_RECEVER_UPDATE_PAY_ARGS_PARAMS)
        ));
        return container;
    }

}
