package com.itheima.test;

import com.itheima.config.QueueConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMQTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /***
     * 发送消息
     */
    @Test
    public void sendMessage() throws InterruptedException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("发送当前时间:" + dateFormat.format(new Date()));
        Map<String, String> message = new HashMap<>();
        message.put("name", "szitheima");
        rabbitTemplate.convertAndSend(QueueConfig.QUEUE_MESSAGE_DELAY, message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //延时10发送消息
                message.getMessageProperties().setExpiration("10000");
                return message;
            }
        });
        //让主线程等待控制台输入，主要是不想让监听器也结束
        System.in.read();
    }
}
