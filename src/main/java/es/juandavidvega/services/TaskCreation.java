package es.juandavidvega.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.juandavidvega.dto.input.NewTask;
import es.juandavidvega.dto.output.OperationStatus;
import es.juandavidvega.model.Task;
import es.juandavidvega.model.Team;
import es.juandavidvega.repository.read.TeamRetriever;
import es.juandavidvega.repository.write.TaskStorer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import sun.plugin2.message.ShowStatusMessage;

@Service
public class TaskCreation {

    private final  static Function<? super FluxSink<OperationStatus>, Consumer<Throwable>> errorHandler = fluxSink -> error -> {
        fluxSink.next(OperationStatus.ERROR);
        fluxSink.error(error);
        fluxSink.complete();
    };

    private final  static  Function<? super FluxSink<OperationStatus>, Consumer<Task>> successHandler = fluxSink -> error -> {
        fluxSink.next(OperationStatus.SUCCESS);
        fluxSink.complete();
    };


    private final TaskStorer taskStorer;
    private final TeamRetriever teamRetriever;

    @Autowired
    public TaskCreation(TaskStorer taskStorer, TeamRetriever teamRetriever) {
        this.taskStorer = taskStorer;
        this.teamRetriever = teamRetriever;
    }

    public Flux<OperationStatus> create (NewTask newTask) {
        Mono<Team> teamMono = teamRetriever.finByName(newTask.getTeamName());
        LocalDateTime dueDate = LocalDate.parse(newTask.getDueDate(), DateTimeFormatter.ISO_LOCAL_DATE).atStartOfDay();
        Consumer<? super FluxSink<OperationStatus>> statusEmitter = e -> {
            e.next(OperationStatus.START);
            teamMono.subscribe(t -> {
                Mono<Task> saved = taskStorer.save(new Task("", t, dueDate, newTask.getTitle()));
                saved.subscribe(successHandler.apply(e), errorHandler.apply(e));
            });
        };
        return Flux.create(statusEmitter);
    }



}
