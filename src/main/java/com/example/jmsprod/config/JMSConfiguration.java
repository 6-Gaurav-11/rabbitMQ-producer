package com.example.jmsprod.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class JMSConfiguration {
	
	public static final String topicExchangeName = "message_queue_exchange";
	public static final String queueName = "message_queue";

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}
	
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}
	
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("message.#");
	}
	
	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory ccf = new CachingConnectionFactory("localhost");
		ccf.setPort(5672);
		ccf.setUsername("guest");
		ccf.setPassword("guest");
		return ccf;
	}
	
}
