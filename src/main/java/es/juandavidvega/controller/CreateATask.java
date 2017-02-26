package es.juandavidvega.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import es.juandavidvega.dto.input.NewTask;
import es.juandavidvega.model.Task;
import es.juandavidvega.model.Team;
import es.juandavidvega.repository.ReactiveTaskRepository;
import es.juandavidvega.repository.TeamRepository;

@RestController
public class CreateATask {

    private final ReactiveTaskRepository taskRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public CreateATask(ReactiveTaskRepository taskRepository, TeamRepository teamRepository) {
        this.taskRepository = taskRepository;
        this.teamRepository = teamRepository;
    }

    @RequestMapping(path = "create-a-task", method = RequestMethod.POST)
    @ResponseBody
    public Task create(@RequestBody NewTask newTask) {
        Team team = teamRepository.finByName(newTask.getTeamName());
        LocalDateTime dueDate = LocalDate.parse(newTask.getDueDate(), DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
        Task task = new Task(team, dueDate, newTask.getTitle());
        taskRepository.save(task);
        return task;
    }

}
