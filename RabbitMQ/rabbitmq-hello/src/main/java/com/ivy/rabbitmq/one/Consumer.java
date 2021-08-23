package com.ivy.rabbitmq.one;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 */
public class Consumer {
    /**
     * 队列名称
     */
    public static final String QUEUE_NAME = "hello";

    /**
     * 接收消息
     *
     * @param args
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("admin");
        factory.setPassword("123");
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        /**
         * 声明 接收消息回调
         */
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            String content = new String(message.getBody());
            System.out.println(content);
        };
        /**
         * 声明 取消消息回调
         */
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println("消息消费被中断");
        };

        /**
         * 消费者消费消息
         *      1、消费的队列
         *      2、消费成功之后是否要自动应答，true自动应答，false手动应答
         *      3、消费者成功消费的回调
         *      4、消费者取消消费的回调
         */
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }
}
