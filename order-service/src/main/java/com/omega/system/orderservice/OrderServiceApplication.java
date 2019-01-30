package com.omega.system.orderservice;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@EnableJms
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.omega.system.orderservice")
public class OrderServiceApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(OrderServiceApplication.class, args);
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

	}

	@Bean
	public Sampler defaultSampler(){
		return Sampler.ALWAYS_SAMPLE;
	}

}

