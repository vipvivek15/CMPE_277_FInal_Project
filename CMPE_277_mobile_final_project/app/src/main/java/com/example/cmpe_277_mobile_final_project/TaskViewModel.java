package com.example.cmpe_277_mobile_final_project;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private final TaskRepository repository;
    private final LiveData<List<Task>> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();
    }

    // Expose the list of all tasks to the UI
    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    // Wrapper method to insert a task
    public void insert(Task task) {
        repository.insert(task);
    }

    // Wrapper method to update a task
    public void update(Task task) {
        repository.update(task);
    }

    // Wrapper method to delete a task
    public void delete(Task task) {
        repository.delete(task);
    }

    // Additional method to get tasks by priority
    public LiveData<List<Task>> getTasksByPriority(int priorityLevel) {
        return repository.getTasksByPriority(priorityLevel);
    }
}
