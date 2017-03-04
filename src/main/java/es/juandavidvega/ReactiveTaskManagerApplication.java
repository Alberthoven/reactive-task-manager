package es.juandavidvega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import lombok.RequiredArgsConstructor;

@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
@EnableReactiveMongoRepositories
@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
@RequiredArgsConstructor
public class ReactiveTaskManagerApplication extends AbstractReactiveMongoConfiguration {

	private final MongoProperties mongoProperties;

	public static void main(String[] args) {
		SpringApplication.run(ReactiveTaskManagerApplication.class, args);
	}

	@Bean
	public LoggingEventListener mongoEventListener() {
		return new LoggingEventListener();
	}

	@Override
	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create("mongodb://172.17.0.2:27017");
	}

	@Override
	protected String getDatabaseName() {
		return "reactivedb";
	}
}