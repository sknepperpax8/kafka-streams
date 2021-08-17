package com.pax8pro.streamProcessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.cloud.stream.binder.kafka.streams.KafkaStreamsRegistry;
import org.springframework.cloud.stream.binder.kafka.streams.properties.KafkaStreamsBinderConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StreamProcessorApplication {
	public static void main(String[] args) {
		SpringApplication.run(StreamProcessorApplication.class, args);
	}

	@Bean
	InteractiveQueryService interactiveQueryService(KafkaStreamsRegistry kafkaStreamsRegistry,
													KafkaStreamsBinderConfigurationProperties binderConfigurationProperties) {
		return new InteractiveQueryService(kafkaStreamsRegistry, binderConfigurationProperties);
	}
}
