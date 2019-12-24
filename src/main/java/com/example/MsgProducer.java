package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Component
public class MsgProducer {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void send(String... args) {
        String topic = "TopicTest1";
        String topic2 = "TopicTest2";

        for (int i = 0; i < 10; i++) {
            rocketMQTemplate.convertAndSend(topic, "Hello World" + i);
        }
//        rocketMQTemplate.asyncSend(topic, MessageBuilder.withPayload("I'm from spring message").setHeader(RocketMQHeaders.TAGS, "TAGA").setHeader(RocketMQHeaders.KEYS, "key").build(), new SendCallback() {
//            @Override
//            public void onSuccess(SendResult sendResult) {
//                System.out.println("send success");
//            }
//
//            @Override
//            public void onException(Throwable throwable) {
//
//            }
//        });
//        rocketMQTemplate.setMessageQueueSelector((list, message, o) -> { return null; });
        rocketMQTemplate.send(topic+":222", MessageBuilder.withPayload("tt1").setHeader(RocketMQHeaders.KEYS, "key").build());
        rocketMQTemplate.convertAndSend(topic2, new OrderPaidEvent("T_001", new BigDecimal("88.00")));
        rocketMQTemplate.sendMessageInTransaction("transaction", "TopicTx", MessageBuilder.withPayload("Transaction Message").build(), null);
    }
}
