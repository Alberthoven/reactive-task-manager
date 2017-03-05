package es.juandavidvega.repository.crud;

import org.springframework.data.mongodb.repository.InfiniteStream;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import es.juandavidvega.entity.Task;
import reactor.core.publisher.Flux;

public interface TaskRepository extends ReactiveMongoRepository<Task, String> {

    @InfiniteStream
    public Flux<Task> findWithTailableCursorBy();
}
