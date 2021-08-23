package com.ivy.rabbitmq.two;

import com.ivy.rabbitmq.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.io.IOException;

public class Task01 {
    //队列名称
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMqUtils.getChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        for (int i = 0; i < 20; i++) {
            String message = "demo---: " + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("发送：" + i + "完成");
        }
    }
}
