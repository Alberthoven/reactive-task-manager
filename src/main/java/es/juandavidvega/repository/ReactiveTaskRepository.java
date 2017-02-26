package es.juandavidvega.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.stereotype.Repository;

import es.juandavidvega.model.Task;

@Repository
public class ReactiveTaskRepository {

    private static List<Task> inMemoryNewTaskDB = new ArrayList<Task>();
    private static List<Consumer<Task>> activeConsumers = new ArrayList<>();


    public List<Task> loadAll() {
        return inMemoryNewTaskDB;
    }

    public void save(Task newTask){
        inMemoryNewTaskDB.add(newTask);
        activeConsumers.forEach(c -> c.accept(newTask));
    }

    public void onNew (Consumer<Task> taskConsumer) {
        activeConsumers.add(taskConsumer);
    }

}
