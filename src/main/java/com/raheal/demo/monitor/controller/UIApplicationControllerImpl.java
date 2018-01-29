package com.raheal.demo.monitor.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import com.raheal.demo.monitor.ui.UiApplication;

@Component
public class UIApplicationControllerImpl implements UiApplicationController {

	private UiApplication uiApplication;
	
	private ExecutorService executorService;
	
	@Override
	public void show() throws Exception {
		uiApplication = new UiApplication();
		
		executorService = Executors.newFixedThreadPool(1);
		executorService.execute(uiApplication);
	}

	@PreDestroy
	public void stop() {
		executorService.shutdown();
	}
	
}
