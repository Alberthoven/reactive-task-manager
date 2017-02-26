package es.juandavidvega.model;

import java.time.LocalDateTime;

public class Task {
    private final Team team;
    private final LocalDateTime dueDate;
    private final String title;

    public Task(Team team, LocalDateTime dueDate, String title) {
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
}
