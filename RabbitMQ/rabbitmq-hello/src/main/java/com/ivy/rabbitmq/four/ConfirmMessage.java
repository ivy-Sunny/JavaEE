package com.ivy.rabbitmq.four;

import com.ivy.rabbitmq.RabbitMqUtils;
import com.rabbitmq.client.Channel;

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
        System.out.println("发布" + MESSAGE_COUNT + "个单独确认消息，耗时" + (System.currentTimeMillis() - starttime) + "ms");
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
            if (i%batchSize == 0){
                channel.waitForConfirms();
            }
        }
        //发布确认
        channel.waitForConfirms();
        //结束时间
        System.out.println("发布" + MESSAGE_COUNT + "个批量确认消息，耗时" + (System.currentTimeMillis() - starttime) + "ms");
    }
}
