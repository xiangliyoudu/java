package com.xlyd.demo.jms;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.xlyd.demo.config.ActiveMQConfig;
import com.xlyd.demo.util.LogUtils;

@Component
public class ActiveMQMessageHandler implements MessageListener {

	private final Logger log = LogUtils.getLog(ActiveMQMessageHandler.class);

	@Override
	@JmsListener(containerFactory = "jmsListenerContainerQueue", destination = ActiveMQConfig.QUEUE)
	public void onMessage(Message message) {

		try {
			log.info(">>> new msg dequeue: " + message);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
