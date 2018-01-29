package com.raheal.demo.monitor.core;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.raheal.demo.monitor.controller.ConsumerController;
import com.raheal.demo.monitor.controller.UiApplicationController;

@Component
public class ApplicationCore implements ApplicationRunner {
	
	@Autowired
	private ConsumerController consumerController;
	
	@Autowired
	private UiApplicationController uiApplicationController;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		uiApplicationController.show();
		consumerController.start();
	}
	
	
	@PreDestroy
	public void shutdownHook() throws Exception {
		consumerController.stop();
	}	

}
