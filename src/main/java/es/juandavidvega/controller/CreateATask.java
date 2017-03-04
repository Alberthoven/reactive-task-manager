package es.juandavidvega.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import es.juandavidvega.dto.input.NewTask;
import es.juandavidvega.dto.output.OperationStatus;
import es.juandavidvega.model.Task;
import es.juandavidvega.model.Team;
import es.juandavidvega.repository.write.TaskStorer;
import es.juandavidvega.repository.read.TeamRetriever;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

@RestController
public class CreateATask {

    private final TaskStorer taskStorer;
    private final TeamRetriever teamRetriever;

    @Autowired
    public CreateATask(TaskStorer taskStorer, TeamRetriever teamRetriever) {
        this.taskStorer = taskStorer;
        this.teamRetriever = teamRetriever;
    }

    @RequestMapping(path = "create-a-task", method = RequestMethod.POST)
    @ResponseBody
    public Flux<OperationStatus> create(@RequestBody NewTask newTask) {
        Mono<Team> teamMono = teamRetriever.finByName(newTask.getTeamName());
        LocalDateTime dueDate = LocalDate.parse(newTask.getDueDate(), DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
        Consumer<? super FluxSink<OperationStatus>> statusEmitter = e -> {
            e.next(OperationStatus.START);
            teamMono.subscribe(t -> {
                taskStorer.save(new Task(UUID.randomUUID().toString(), t, dueDate, newTask.getTitle()));
                e.next(OperationStatus.SUCCESS);
                e.complete();
            });
        };
        return Flux.create(statusEmitter);
    }

}
