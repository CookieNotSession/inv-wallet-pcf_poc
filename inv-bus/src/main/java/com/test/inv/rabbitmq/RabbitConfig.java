package com.test.inv.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    public static final String DEFAULT_QUEUE = "inv-queue";
    public static final String MANUAL_QUEUE = "inv-manual-queue";

    
    @Bean
    public Queue defaultQueue() {
        // 第一个是 QUEUE 的名字,第二个是消息是否需要持久化处理
        return new Queue(DEFAULT_QUEUE, true);
    }
    
    @Bean
    public Queue manualQueue() {
        // 第一个是 QUEUE 的名字,第二个是消息是否需要持久化处理
        return new Queue(MANUAL_QUEUE, true);
    }

}
