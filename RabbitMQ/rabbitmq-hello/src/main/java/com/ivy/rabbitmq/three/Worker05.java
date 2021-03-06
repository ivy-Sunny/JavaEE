package com.ivy.rabbitmq.three;

import com.ivy.rabbitmq.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import java.io.IOException;

public class Worker05 {

    //队列名称
    public static final String TASK_QUEUE_NAME = "ack_queue";

    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMqUtils.getChannel();
        System.out.println("W5等待接收消息处理时间较长");
        boolean autoAck = false;
        channel.basicConsume(TASK_QUEUE_NAME, autoAck, new DeliverCallback() {
                    @Override
                    public void handle(String consumerTag, Delivery message) throws IOException {
                        RabbitMqUtils.sleep(30);
                        System.out.println("W5成功了" + new String(message.getBody()));
                        /**
                         * 手动应答
                         * params
                         *    1、消息的标记
                         *    2、是否批量应答
                         */
                        channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                    }
                },
                new CancelCallback() {
                    @Override
                    public void handle(String consumerTag) throws IOException {
                        System.out.println("W5失败了:" + consumerTag);
                    }
                }
        );
    }
}
