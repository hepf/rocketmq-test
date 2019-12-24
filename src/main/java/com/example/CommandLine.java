package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CommandLine implements CommandLineRunner {
    @Autowired
    private MsgProducer msgProducer;
    @Override
    public void run(String... args) throws Exception {
        while(true) {
            System.out.println("send message");
            msgProducer.send("");
            TimeUnit.SECONDS.sleep(10);
        }
    }
}
