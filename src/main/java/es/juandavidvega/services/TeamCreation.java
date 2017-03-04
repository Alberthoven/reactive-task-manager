package es.juandavidvega.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.juandavidvega.dto.input.NewTeam;
import es.juandavidvega.model.Team;
import es.juandavidvega.repository.write.TeamStorer;
import reactor.core.publisher.Mono;

@Service
public class TeamCreation {

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
