package com.raheal.demo.monitor.controller;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raheal.demo.monitor.ui.UiApplication;

public class Consumer implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
	
	private KafkaConsumer<String, String> consumer;
	
	
	public Consumer() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "consumer-tutorial");
		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());
		consumer = new KafkaConsumer<>(props);
	}
	
	@Override
	public void run() {
		try {
			
			logger.info("Running the consumer process");
			
			consumer.subscribe(Arrays.asList("eventstopic"));

			String[] recordArray = null;
			
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
				for (ConsumerRecord<String, String> record : records) {
					System.out.println("RECORD> Partition=" + record.partition() + ", Offset=" + record.offset()
							+ ", value=" + record.value());
				
				recordArray = record.value().split(";");
				
				if (recordArray.length == 5) {
					UiApplication.defaultTableModel.addRow(recordArray);
				}
				else {
					logger.warn("Cannot process the record as it only has {} fields", recordArray.length);
				}
					
				}
			}
		} catch (WakeupException e) {
			logger.error(e.getMessage(), e);
		} finally {
			consumer.close();
		}	
	}
	
	public void stopConsumer() {
		consumer.close();
	}

}
