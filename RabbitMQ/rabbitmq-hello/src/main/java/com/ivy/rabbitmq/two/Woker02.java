package com.ivy.rabbitmq.two;

import com.ivy.rabbitmq.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import java.io.IOException;

/**
 * 一个工作线程（消费者）
 */
public class Woker02 {

    //队列名称
    public static final String QUEUE_NAME = "hello";

    /**
     * 接收消息
     */
    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMqUtils.getChannel();
        System.out.println("W2等待接收消息……");
        channel.basicConsume(
                QUEUE_NAME, true,
                new DeliverCallback() {
                    @Override
                    public void handle(String consumerTag, Delivery message) throws IOException {
                        System.out.println("接收到的消息：" + new String(message.getBody()));
                    }
                },
                new CancelCallback() {
                    @Override
                    public void handle(String consumerTag) throws IOException {
                        System.out.println(consumerTag + "消息消费被中断");
                    }
                }
        );
    }
}
