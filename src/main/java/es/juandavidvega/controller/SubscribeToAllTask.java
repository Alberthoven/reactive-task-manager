package es.juandavidvega.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import es.juandavidvega.model.Task;
import es.juandavidvega.repository.read.TaskRetriever;
import reactor.core.publisher.Flux;

@RestController
public class SubscribeToAllTask {

    private final TaskRetriever taskRetriever;

    @Autowired
    public SubscribeToAllTask(TaskRetriever taskRetriever) {
        this.taskRetriever = taskRetriever;
    }

    @GetMapping(path = "all-task.flux", produces = "text/event-stream")
    public Flux<Task> all () {
        return taskRetriever.findAll();
    }

}
