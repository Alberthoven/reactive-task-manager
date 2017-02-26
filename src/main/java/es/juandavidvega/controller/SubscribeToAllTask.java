package es.juandavidvega.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import es.juandavidvega.model.Task;
import es.juandavidvega.repository.ReactiveTaskRepository;
import reactor.core.publisher.Flux;

@RestController
public class SubscribeToAllTask {

    private final ReactiveTaskRepository reactiveTaskRepository;

    @Autowired
    public SubscribeToAllTask(ReactiveTaskRepository reactiveTaskRepository) {
        this.reactiveTaskRepository = reactiveTaskRepository;
    }

    @GetMapping(path = "all-task.flux", produces = "text/event-stream")
    public Flux<Task> all () {
        Flux<Task> existingTask = Flux.fromIterable(reactiveTaskRepository.loadAll());
        Flux<Task> newTaskEmitter = Flux.create(emitter -> reactiveTaskRepository.onNew(emitter::next));
        return Flux.mergeSequential(existingTask, newTaskEmitter);
    }

}
