package com.xlyd.jms.helloworld;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import config.ActiveMQConfig;

@Component
// 启用jms注解，使@JMSListener生效
@EnableJms
public class MyMessageHandler implements MessageListener {
	// 注册方法到监听器
	@JmsListener(containerFactory = "jmsListenerContainerQueue",  destination = ActiveMQConfig.QUEUE)
	public void onMessage(Message message) {

		try {
			System.out.println("another message enqueued ...");
			System.out.println(message.getJMSMessageID());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
