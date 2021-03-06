package es.juandavidvega.model;

import java.time.LocalDateTime;

public class Task {

    private final String id;
    private final Team team;
    private final LocalDateTime dueDate;
    private final String title;

    public Task(String id, Team team, LocalDateTime dueDate, String title) {
        this.id = id;
        this.team = team;
        this.dueDate = dueDate;
        this.title = title;
    }

    public Team getTeam() {
        return team;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }
}
