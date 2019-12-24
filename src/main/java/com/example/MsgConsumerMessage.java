package com.example;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
//@RocketMQMessageListener(topic = "TopicTest1", consumerGroup = "${rocketmq.producer.group}")
public class MsgConsumerMessage implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {

    @Override
    public void onMessage(MessageExt message) {
        System.out.println("receive messageExt:" + message);
        System.out.println("comsume times: " + message.getReconsumeTimes());
//        message.setKeys("bbb");

//        try {
//            TimeUnit.MINUTES.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if (2 <= message.getReconsumeTimes()) {
            System.err.println("error in cosumer");
        }
//        throw new RuntimeException("aaa");
    }

    @Override
    public void prepareStart(final DefaultMQPushConsumer consumer) {
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        try {
            consumer.subscribe("TopicTest1", MessageSelector.byTag("222"));
            consumer.setMaxReconsumeTimes(2);
        } catch (MQClientException e) {
            e.printStackTrace();
        }
//        consumer.setConsumeTimestamp(UtilAll.timeMillisToHumanString3(System.currentTimeMillis()));
    }


}
