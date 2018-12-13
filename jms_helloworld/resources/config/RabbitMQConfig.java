package config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"com.xlyd.jms.helloworld"})
public class RabbitMQConfig {

	/**
	 * 配置connection-factory，指定连接rabbit server参数
	 * @return
	 */
	@Bean
	public RabbitConnectionFactoryBean cf() {
		RabbitConnectionFactoryBean factory = new RabbitConnectionFactoryBean();
		factory.setHost("localhost");
		factory.setPort(5672);
		factory.setUsername("guest");
		factory.setPassword("guest");
		return factory;
	}

	/**
	 * 定义queue
	 * @return
	 */
	@Bean
	public Queue queue() {
		Queue que = new Queue("testqueue");
		return que;
	}

	/**
	 * 通过指定下面的admin信息，当前producer中的exchange和queue会在rabbitmq服务器上自动生成
	 * @param ConnectionFactory, DirectExchange, Queue
	 * @return
	 * @throws Exception
	 */
	@Bean
	public RabbitAdmin rabbitAdmin(ConnectionFactory factory,
			DirectExchange exchange, Queue queue, Binding binding)
			throws Exception {
		RabbitAdmin ra = new RabbitAdmin(factory);
		// 交换机和队列绑定
		ra.declareExchange(exchange);
		ra.declareQueue(queue);
		ra.declareBinding(binding);
		return ra;
	}

	// 创建direct类型的交换机
	@Bean
	public DirectExchange directExchange() {
		DirectExchange direct = new DirectExchange("testdirect");
		return direct;
	}

	// 创建topic类型的交换机
	@Bean
	public TopicExchange topicExchange() {
		TopicExchange topicExchange = new TopicExchange("testtopic");
		return topicExchange;
	}

	// 创建queue与交换机之间的绑定
	@Bean
	public Binding binding(Queue queue, Exchange exchange) {
		Binding bind = BindingBuilder.bind(queue).to(exchange).with("test1")
					   .noargs();
		return bind;
	}
	
	// 设置监听容器
	@Bean(name="smlContainer")
	public SimpleMessageListenerContainer container(
					ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer cont = 
				new SimpleMessageListenerContainer(connectionFactory);
		return cont;
	}

}












