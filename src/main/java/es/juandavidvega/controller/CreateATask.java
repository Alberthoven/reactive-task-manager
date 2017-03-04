package es.juandavidvega.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import es.juandavidvega.dto.input.NewTask;
import es.juandavidvega.dto.output.OperationStatus;
import es.juandavidvega.services.TaskCreation;
import reactor.core.publisher.Flux;

@RestController
public class CreateATask {

    private final TaskCreation taskCreation;

    @Autowired
    public CreateATask(TaskCreation taskCreation) {
        this.taskCreation = taskCreation;
    }

    @RequestMapping(path = "create-a-task", method = RequestMethod.POST)
    @ResponseBody
    public Flux<OperationStatus> create(@RequestBody NewTask newTask) {
        return taskCreation.create(newTask);
    }

}
