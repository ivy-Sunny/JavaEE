package com.ivy.rabbitmq.four;

import com.ivy.rabbitmq.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class ConfirmMessage {

    //批量发消息的个数
    public static final int MESSAGE_COUNT = 1000;

    /**
     * 开启发布确认 channel.confirmSelect()
     * channel.waitForConfirms() 单个确认（同步的）
     * 批量确认发布（出现问题时，确认不到对应消息）
     * 异步确认发布
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        //ConfirmMessage.publishMessageIndividually();//发布1000个单独发布确认消息，耗时1920ms
        //ConfirmMessage.publishMessageBatch();//发布1000个批量发布确认消息，耗时132ms
        ConfirmMessage.publishMessageAsync();//发布1000个异步发布确认消息，耗时53ms
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

        /**
         * 线程安全有序的一个哈希表 适用于高并发的情况下
         *  1、将序号与消息进行关联
         *  2、通过序号批量删除
         *  3、支持高并发（多线程）
         */
        ConcurrentSkipListMap<Long, String> outstandingConfirms = new ConcurrentSkipListMap<>();

        /**
         * 消息确认成功 回调函数
         */
        ConfirmCallback ackCallback = (deliveryTag, multiple) -> {
            System.out.println("确认的消息:" + deliveryTag);
            /**
             * ------------
             * 2、删除确认成功发布的消息
             *
             * 批量/单个
             */
            if (multiple) {
                ConcurrentNavigableMap<Long, String> confirmed
                        = outstandingConfirms.headMap(deliveryTag);
                confirmed.clear();
            } else {
                outstandingConfirms.remove(deliveryTag);
            }
        };

        /**
         * 消息确认失败 回调函数
         *  params:
         *      1.消息的标记
         *      2.是否为批量确认
         */
        ConfirmCallback nackCallback = (deliveryTag, multiple) -> {
            String message = outstandingConfirms.get(deliveryTag);
            System.out.println("未确认的消息:" + message + " 标记是：" + deliveryTag);
        };

        //开启发布确认
        channel.confirmSelect();

        //开始时间
        long starttime = System.currentTimeMillis();

        /**
         * 准备消息的监听器，监听哪些消息失败，哪些消息成功 (异步)
         * 单参 监听成功消息
         * 多参 监听成功，失败消息
         */
        channel.addConfirmListener(ackCallback, nackCallback);
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = "" + i;
            channel.basicPublish("", queueName, null, message.getBytes());
            /**
             * ----------
             * 1、记录下所有发送的消息
             */
            outstandingConfirms.put(channel.getNextPublishSeqNo(), message);
        }

        //结束时间
        System.out.println("发布" + MESSAGE_COUNT + "个异步发布确认消息，耗时" + (System.currentTimeMillis() - starttime) + "ms");
    }
}
