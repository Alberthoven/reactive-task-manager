package es.juandavidvega.repository.write;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.juandavidvega.entity.Team;
import es.juandavidvega.model.Task;
import es.juandavidvega.repository.crud.TaskRepository;
import reactor.core.publisher.Mono;

@Repository
public class TaskStorer {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskStorer(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Mono<Task> save(Task task) {
        es.juandavidvega.entity.Task entityTask = new es.juandavidvega.entity.Task();
        entityTask.setDueDate(task.getDueDate());
        entityTask.setId(UUID.randomUUID().toString());
        Team team = new Team();
        team.setId(task.getTeam().getId());
        team.setName(task.getTeam().getName());
        entityTask.setTeam(team);
        entityTask.setTitle(task.getTitle());
        return taskRepository.save(entityTask).map(this::map);
    }

    private Task map(es.juandavidvega.entity.Task entity) {
        es.juandavidvega.model.Team team = new es.juandavidvega.model.Team(entity.getTeam().getId(), entity.getTeam().getName());
        return new Task(entity.getId(), team, entity.getDueDate(), entity.getTitle());
    }
}
