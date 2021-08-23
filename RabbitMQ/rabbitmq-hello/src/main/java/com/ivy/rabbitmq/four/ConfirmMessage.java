package com.ivy.rabbitmq.four;

import com.ivy.rabbitmq.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;

import java.io.IOException;
import java.util.UUID;

public class ConfirmMessage {

    //批量发消息的个数
    public static final int MESSAGE_COUNT = 1000;

    /**
     * 开启发布确认 channel.confirmSelect()
     * channel.waitForConfirms() 单个确认（同步的）
     * 批量确认发布（出现问题时，确认不到对应消息）
     * 异步确认发布
     */
    public static void main(String[] args) {

    }

    /**
     * 单个确认
     */
    public static void publishMessageIndividually() throws IOException, InterruptedException {
        Channel channel = RabbitMqUtils.getChannel();
        //队列的声明
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, false, false, false, null);
        //开启发布确认
        channel.confirmSelect();
        //开始时间
        long starttime = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = "" + i;
            channel.basicPublish("", queueName, null, message.getBytes());
            //单个消息进行发布确认
            boolean flag = channel.waitForConfirms();
            if (true) {
                System.out.println("消息发送成功");
            }
        }
        //结束时间
        System.out.println("发布" + MESSAGE_COUNT + "个单独发布确认消息，耗时" + (System.currentTimeMillis() - starttime) + "ms");
    }

    /**
     * 批量确认发布
     */
    public static void publishMessageBatch() throws IOException, InterruptedException {
        Channel channel = RabbitMqUtils.getChannel();
        //队列的声明
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, false, false, false, null);
        //开启发布确认
        channel.confirmSelect();
        //批量确认消息大小
        int batchSize = 100;
        //开始时间
        long starttime = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = "" + i;
            channel.basicPublish("", queueName, null, message.getBytes());
            if (i % batchSize == 0) {
                channel.waitForConfirms();
            }
        }
        //发布确认
        channel.waitForConfirms();
        //结束时间
        System.out.println("发布" + MESSAGE_COUNT + "个批量发布确认消息，耗时" + (System.currentTimeMillis() - starttime) + "ms");
    }

    /**
     * 异步确认发布
     */
    public static void publishMessageAsync() throws IOException, InterruptedException {
        Channel channel = RabbitMqUtils.getChannel();
        //队列的声明
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, false, false, false, null);
        //开启发布确认
        channel.confirmSelect();
        /**
         * 消息确认成功 回调函数
         */
        ConfirmCallback ackCallback = (deliveryTag, multiple) -> {
            System.out.println("确认的消息:" + deliveryTag);
        };
        /**
         * 消息确认失败 回调函数
         *  params:
         *      1.消息的标记
         *      2.是否为批量确认
         */
        ConfirmCallback nackCallback = (deliveryTag, multiple) -> {
            System.out.println("未确认的消息:" + deliveryTag);
        };
        /**
         * 准备消息的监听器，监听哪些消息失败，哪些消息成功 (异步)
         * 单参 监听成功消息
         * 多参 监听成功，失败消息
         */
        channel.addConfirmListener(ackCallback, nackCallback);
        //开始时间
        long starttime = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = "" + i;
            channel.basicPublish("", queueName, null, message.getBytes());
        }

        //结束时间
        System.out.println("发布" + MESSAGE_COUNT + "个异步发布确认消息，耗时" + (System.currentTimeMillis() - starttime) + "ms");
    }
}
