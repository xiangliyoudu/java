package com.xlyd.jms.helloworld;

import config.ActiveMQConfig;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;

@Component
// 启用jms注解，使@JMSListener生效
@EnableJms
public class MyMessageHandler implements MessageListener {
	// 注册方法到监听器
	@JmsListener(containerFactory="jmsListenerContainerqueue", destination= ActiveMQConfig.QUEUE)
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		System.out.println("another message enqueued ...");
	}



}
