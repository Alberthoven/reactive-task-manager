package es.juandavidvega.services;

import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.juandavidvega.dto.input.NewTeam;
import es.juandavidvega.dto.output.OperationStatus;
import es.juandavidvega.model.Team;
import es.juandavidvega.repository.write.TeamStorer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

@Service
public class TeamCreation {

    private final  static  Function<? super FluxSink<OperationStatus>, Consumer<Throwable>> errorHandler = fluxSink ->  error -> {
        fluxSink.next(OperationStatus.ERROR);
        fluxSink.error(error);
        fluxSink.complete();
    };

    private final  static  Function<? super FluxSink<OperationStatus>, Consumer<Team>> successHandler = fluxSink ->  error -> {
        fluxSink.next(OperationStatus.SUCCESS);
        fluxSink.complete();
    };

    private final TeamStorer storer;

    @Autowired
    public TeamCreation(TeamStorer teamStorer) {
        this.storer = teamStorer;
    }

    public void newTeam (NewTeam newTeam){
        Mono<Team> saved = storer.save(newTeam);
        saved.subscribe();
    }

}
