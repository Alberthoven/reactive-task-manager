package es.juandavidvega.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.juandavidvega.dto.input.NewTeam;
import es.juandavidvega.dto.output.OperationStatus;
import es.juandavidvega.entity.Team;
import es.juandavidvega.repository.write.TeamStorer;
import es.juandavidvega.services.TeamCreation;

@RestController
public class CreateATeam {


    private final TeamCreation creation;

    @Autowired
    public CreateATeam(TeamCreation creation) {
        this.creation = creation;
    }

    @RequestMapping(path = "create-a-team", method = RequestMethod.POST)
    public OperationStatus create (@RequestBody NewTeam newTeam) {
        creation.newTeam(newTeam);
        return OperationStatus.SUCCESS;
    }

}
