package es.juandavidvega.repository.write;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.juandavidvega.entity.Team;
import es.juandavidvega.model.Task;
import es.juandavidvega.repository.crud.TaskRepository;

@Repository
public class TaskStorer {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskStorer(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void save(Task task) {
        es.juandavidvega.entity.Task entityTask = new es.juandavidvega.entity.Task();
        entityTask.setDueDate(task.getDueDate());
        entityTask.setId(task.getId());
        Team team = new Team();
        team.setId(task.getTeam().getId());
        team.setName(task.getTeam().getName());
        entityTask.setTeam(team);
        entityTask.setTitle(task.getTitle());
        taskRepository.save(entityTask);
    }
}
