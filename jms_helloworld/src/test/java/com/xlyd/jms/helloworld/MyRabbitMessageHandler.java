package com.xlyd.jms.helloworld;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
// 启用rabbit，是@RabbitListener生效
@EnableRabbit
public class MyRabbitMessageHandler implements MessageListener {

	@RabbitListener(containerFactory="smlContainer", queues={"queue"})
	public void onMessage(Message message) {
		System.out.println("mesage enqueue to rabbitmq");
	}

}
