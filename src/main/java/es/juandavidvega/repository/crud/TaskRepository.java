package es.juandavidvega.repository.crud;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import es.juandavidvega.entity.Task;

public interface TaskRepository extends ReactiveMongoRepository<Task, String> {

}
