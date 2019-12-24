package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private MsgProducer msgProducer;

	@Test
	public void testSend() throws InterruptedException {
		msgProducer.send(null);
		TimeUnit.SECONDS.sleep(10);
	}

}
