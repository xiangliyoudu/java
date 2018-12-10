package config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

@Configuration
//@ImportResource("classpath:activeMQ-config.xml")
@ComponentScan(basePackages={"com.xlyd.jms.helloworld"})
public class ActiveMQConfig {
	public static final String QUEUE = "myqueue";
	public static final String TOPIC = "mytopic";
	public static final String BROKERURL = "tcp://localhost:61616";


	@Bean // activemq连接工厂
	public ActiveMQConnectionFactory aMQCFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(BROKERURL);
		return factory;
	}
	
	@Bean // queue目的地
	public ActiveMQQueue queue() {
		ActiveMQQueue que = new ActiveMQQueue();
		que.setPhysicalName(QUEUE);
		return que;
	}
	
	@Bean // topic目的地
	public ActiveMQTopic topic() {
		ActiveMQTopic top = new ActiveMQTopic();
		top.setPhysicalName(TOPIC);
		return top;
	}
	
//	@Bean
	public MappingJackson2MessageConverter mappingJackson2MessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		return converter;
	}
	
	@Bean // jmstemplate，注入connectionfactory
	public JmsTemplate template(ActiveMQConnectionFactory factory, 
							    ActiveMQQueue queue) {
		JmsTemplate temp = new JmsTemplate();
		temp.setConnectionFactory(factory);
		// set message converter
//		temp.setMessageConverter(messageConverter);
		temp.setDefaultDestination(queue);
		return temp;
	}
	
	
	@Bean // jms监听容器-queue
	public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory factory) {
		DefaultJmsListenerContainerFactory container = new DefaultJmsListenerContainerFactory();
		container.setConnectionFactory(factory);
		// 监听topic
		container.setPubSubDomain(true);
		return container;
	}
	
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerqueue(ActiveMQConnectionFactory factory) {
		DefaultJmsListenerContainerFactory container = new DefaultJmsListenerContainerFactory();
		container.setConnectionFactory(factory);
		return container;
	}
	
	
}






