package es.juandavidvega.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.juandavidvega.dto.input.NewTeam;
import es.juandavidvega.model.Team;
import es.juandavidvega.repository.TeamRepository;

@RestController
public class CreateATeam {


    private final TeamRepository repository;

    @Autowired
    public CreateATeam(TeamRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(path = "create-a-team", method = RequestMethod.POST)
    public Team create (@RequestBody NewTeam newTeam) {
        Random r = new Random();
        Team team = new Team(r.nextInt(), newTeam.getName());
        repository.save(team);
        return team;
    }

}
