package es.juandavidvega.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import es.juandavidvega.repository.crud.TaskRepository;

@EnableReactiveMongoRepositories(basePackageClasses = TaskRepository.class)
@Configuration
public class MongoDBConfiguration extends AbstractReactiveMongoConfiguration {

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
