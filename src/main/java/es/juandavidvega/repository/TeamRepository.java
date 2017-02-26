package es.juandavidvega.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.stereotype.Repository;

import es.juandavidvega.model.Team;

@Repository
public class TeamRepository {
    private static List<Team> inMemoryNewTeamDB = new ArrayList<>();
    private static List<Consumer<Team>> activeConsumers = new ArrayList<>();


    public List<Team> loadAll() {
        return inMemoryNewTeamDB;
    }

    public void save(Team newTeam){
        inMemoryNewTeamDB.add(newTeam);
        activeConsumers.forEach(c -> c.accept(newTeam));
    }

    public void onNew (Consumer<Team> taskConsumer) {
        activeConsumers.add(taskConsumer);
    }

    public Team finByName(String teamName) {
        return inMemoryNewTeamDB.stream()
                .filter(t -> t.getName().equals(teamName))
                .findFirst()
                .orElse(null);
    }
}
