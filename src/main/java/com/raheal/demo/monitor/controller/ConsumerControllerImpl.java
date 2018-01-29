package com.raheal.demo.monitor.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("consumerController")
public class ConsumerControllerImpl implements ConsumerController {

	private static final Logger logger = LoggerFactory.getLogger(ConsumerControllerImpl.class);
	
	private Consumer consumer;
	
	private ExecutorService executorService;
	
	@PostConstruct
	public void initialize() {
		consumer = new Consumer();
	}
	
	@PreDestroy
	public void destroy() throws Exception {
		stop();
	}
	
	@Override
	public void start() throws Exception {
		
		executorService = Executors.newFixedThreadPool(1);
		executorService.execute(consumer);
		
	}

	@Override
	public void stop() throws Exception {
		logger.info("Stopping the consumer controller");
		consumer.stopConsumer();
		executorService.shutdown();
	}

}
