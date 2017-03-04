package es.juandavidvega.repository.read;

import org.springframework.stereotype.Repository;

import es.juandavidvega.model.Team;
import es.juandavidvega.repository.crud.TeamRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class TeamRetriever {

    private final TeamRepository repository;

    public TeamRetriever(TeamRepository repository) {
        this.repository = repository;
    }

    public Mono<Team> finByName (String name) {
        Flux<Team> team = repository.findAll().filter(t -> t.getName().equals(name)).map(et -> new Team(et.getId(), et.getName()));
        return Mono.from(team);

    }
}
