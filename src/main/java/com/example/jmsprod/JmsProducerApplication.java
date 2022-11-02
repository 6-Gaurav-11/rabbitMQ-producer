package com.example.jmsprod;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.jmsprod.config.JMSConfiguration;

@SpringBootApplication
public class JmsProducerApplication implements CommandLineRunner{

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	
	public static void main(String[] args) {
		SpringApplication.run(JmsProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Sending message...");
		rabbitTemplate.convertAndSend(JMSConfiguration.topicExchangeName, "message.routing.key","Hello from RabbitMQ JMS !!!");
		System.out.println("Message sent succesfully...");
		
	}

}
