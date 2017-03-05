package es.juandavidvega.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Task {

    @Id
    private String id;
    private Team team;
    private LocalDateTime dueDate;
    private String title;


    public void setId(String id) {
        this.id = id;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public void setTitle(String title) {
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
