package com.xlyd.demo.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.MessagingMessageListenerAdapter;
import org.springframework.stereotype.Component;

import com.xlyd.demo.config.ActiveMQConfig;

@Component
public class MQMessageHandler2 extends MessagingMessageListenerAdapter {

	@Override
	@JmsListener(containerFactory = "jmsListenerContainerQueue", destination = ActiveMQConfig.QUEUE)
	public void onMessage(Message jmsMessage, Session session) throws JMSException {
		
		super.onMessage(jmsMessage, session);
	}
	

}
