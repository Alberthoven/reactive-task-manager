package es.juandavidvega.repository.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.juandavidvega.model.Task;
import es.juandavidvega.model.Team;
import es.juandavidvega.repository.crud.TaskRepository;
import reactor.core.publisher.Flux;

@Repository
public class TaskRetriever {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskRetriever(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Flux<Task> findAll() {
        return taskRepository.findAll().map(this::map);
    }

    private Task map(es.juandavidvega.entity.Task entity) {
        Team team = new Team(entity.getTeam().getId(), entity.getTeam().getName());
        return new Task(entity.getId(), team, entity.getDueDate(), entity.getTitle());
    }
}

