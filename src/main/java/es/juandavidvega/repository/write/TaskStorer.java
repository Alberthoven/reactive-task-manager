package es.juandavidvega.repository.write;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;

import es.juandavidvega.entity.Team;
import es.juandavidvega.model.Task;
import es.juandavidvega.repository.crud.TaskRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class TaskStorer {

    private final TaskRepository taskRepository;
    private final ReactiveMongoOperations operations;

    @Autowired
    public TaskStorer(TaskRepository taskRepository, ReactiveMongoOperations operations) {
        this.taskRepository = taskRepository;
        this.operations = operations;
    }

    public Mono<Task> save(Task task) {
        es.juandavidvega.entity.Task entityTask = toEntity(task);
        operations.collectionExists(es.juandavidvega.entity.Task.class)
                .flatMap(exist -> exist ? Mono.just(true) : operations.createCollection(es.juandavidvega.entity.Task.class, new CollectionOptions(1024 * 1024, 1000, true)))
                .then()
                .block();
        return taskRepository.save(entityTask).map(this::toModel);
    }

    private es.juandavidvega.entity.Task toEntity(Task task) {
        es.juandavidvega.entity.Task entityTask = new es.juandavidvega.entity.Task();
        entityTask.setDueDate(task.getDueDate());
        entityTask.setId(UUID.randomUUID().toString());
        Team team = toTeamModel(task.getTeam());
        entityTask.setTeam(team);
        entityTask.setTitle(task.getTitle());
        return entityTask;
    }

    private Team toTeamModel(es.juandavidvega.model.Team modelTeam) {
        Team team = new Team();
        team.setId(modelTeam.getId());
        team.setName(modelTeam.getName());
        return team;
    }

    private Task toModel(es.juandavidvega.entity.Task entity) {
        es.juandavidvega.model.Team team = new es.juandavidvega.model.Team(entity.getTeam().getId(), entity.getTeam().getName());
        return new Task(entity.getId(), team, entity.getDueDate(), entity.getTitle());
    }
}
