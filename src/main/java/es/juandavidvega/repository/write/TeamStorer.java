package es.juandavidvega.repository.write;

import java.util.Random;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.juandavidvega.dto.input.NewTeam;
import es.juandavidvega.entity.Team;
import es.juandavidvega.repository.crud.TeamRepository;
import reactor.core.publisher.Mono;

@Repository
public class TeamStorer {

    private final TeamRepository repository;

    public TeamStorer(TeamRepository repository) {
        this.repository = repository;
    }

    public Mono<es.juandavidvega.model.Team> save (NewTeam newTeam) {
        Random r = new Random();
        es.juandavidvega.entity.Team entity = new es.juandavidvega.entity.Team();
        entity.setId(r.nextInt());
        entity.setName(newTeam.getName());
        return repository.save(entity).map(e -> new es.juandavidvega.model.Team(e.getId(), e.getName()));
    }
}
