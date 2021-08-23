package com.ivy.rabbitmq.one;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Producer {
    /**
     * 队列名称
     */
    public static final String QUEUE_NAME = "hello";

    /**
     * 发送消息
     *
     * @param args
     */
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //工厂IP  连接RabbitMQ队列
        factory.setHost("localhost");
        //用户名
        factory.setUsername("admin");
        //密码
        factory.setPassword("123");

        //创建连接
        Connection connection = factory.newConnection();
        //通过连接获取信道
        Channel channel = connection.createChannel();
        /**
         * 生成一个队列
         * param:
         *      1.队列名称
         *      2.队列里面的消息是否持久化，默认情况下消息存储在内存中
         *      3.该队列是否只供一个消费者进行消费，是否进行消息的共享。true多个，false单个
         *      4.是否自动删除，最后一个消费者端开启连接后，该队列是否删除。true自动删除，false不自动删除
         *      5.其他参数
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        /**
         * 发送消息
         *  param:
         *      1、交换机
         *      2、路由的key值，本次队列的名称
         *      3、其他参数信息
         *      4、发送消息的消息体
         */
        String message = "hello world";
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("消息发送完毕");
    }

}
