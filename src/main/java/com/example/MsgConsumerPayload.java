package com.example;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
//@RocketMQMessageListener(topic = "TopicTest2", consumerGroup = "my-group2")
public class MsgConsumerPayload implements RocketMQListener<OrderPaidEvent> {

    @Override
    public void onMessage(OrderPaidEvent orderPaidEvent) {
        System.out.println(orderPaidEvent.getOrderId());
    }
}
