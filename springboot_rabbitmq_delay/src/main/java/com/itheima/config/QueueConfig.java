package com.itheima.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    /** 短信接收队列 */
    public static final String QUEUE_MESSAGE = "queue.message";

    /** 交换机 */
    public static final String DLX_EXCHANGE = "dlx.exchange";

    /** 短信发送队列 延迟缓冲（收消息） */
    public static final String QUEUE_MESSAGE_DELAY = "queue.message.delay";

    //------------------------------接收消息队列---------------------------------
    /**
     * 短信接收队列
     * @return
     */
    @Bean
    public Queue messageQueue() {
        return new Queue(QUEUE_MESSAGE, true);
    }

    //--------------------------发送消息队列----------------------------
    /**
     * 短信发送队列
     * @return
     */
    @Bean
    public Queue delayMessageQueue() {
        //durable(死信队列)
        return QueueBuilder.durable(QUEUE_MESSAGE_DELAY)
                // 消息超时进入死信队列，绑定死信队列交换机
                .withArgument("x-dead-letter-exchange", DLX_EXCHANGE)
                // 绑定指定转发的routing-key
                .withArgument("x-dead-letter-routing-key", QUEUE_MESSAGE)
                .build();
    }

    //---------------绑定交换机与队列----------------
    /***
     * 创建交换机
     * @return
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(DLX_EXCHANGE);
    }
    /***
     * 交换机与队列绑定
     * @param messageQueue
     * @param directExchange
     * @return
     */
    @Bean
    public Binding basicBinding(Queue messageQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(messageQueue)
                .to(directExchange)
                .with(QUEUE_MESSAGE);
    }
}
