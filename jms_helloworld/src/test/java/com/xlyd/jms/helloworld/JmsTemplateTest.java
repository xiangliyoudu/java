package com.xlyd.jms.helloworld;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.ActiveMQConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ActiveMQConfig.class)
public class JmsTemplateTest {

	@Autowired
	JmsOperations jo;
	
	@Autowired
	ActiveMQConnectionFactory factory;
	
	@Test
	public void testSend() {
		final String msg = "this is msg for test";
		jo.convertAndSend(msg);
		/*jo.send(new MessageCreator() {
			
			public Message createMessage(Session arg0) throws JMSException {
				// TODO Auto-generated method stub
				return arg0.createTextMessage(msg);
			}
		});*/
	}
	
	@Test
	public void testConn() throws JMSException {
		Connection conn = factory.createConnection();
		String connid = conn.getClientID();
		System.out.println(connid);
	}
	
	
	@Test
	public void testReceive() throws JMSException {
		Message msg = jo.receive();
//		msg = (Message) jo.receiveAndConvert();
		System.out.println(msg);
	}
}









