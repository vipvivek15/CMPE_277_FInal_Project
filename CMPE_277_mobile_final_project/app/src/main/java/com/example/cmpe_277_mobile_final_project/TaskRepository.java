package com.example.cmpe_277_mobile_final_project;
import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRepository {
    private final TaskDao taskDao;
    private final LiveData<List<Task>> allTasks;
    private final ExecutorService executorService;

    // Constructor
    public TaskRepository(Application application) {
        TaskDatabase db = TaskDatabase.getDatabase(application);
        taskDao = db.taskDao();
        allTasks = taskDao.getAllTasks();
        executorService = Executors.newFixedThreadPool(2); // Executor for background operations
    }

    // Get all tasks (LiveData)
    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    // Insert a new task
    public void insert(Task task) {
        executorService.execute(() -> taskDao.insertTask(task));
    }

    // Update an existing task
    public void update(Task task) {
        executorService.execute(() -> taskDao.updateTask(task));
    }

    // Delete a specific task
    public void delete(Task task) {
        executorService.execute(() -> taskDao.deleteTask(task));
    }

    // Additional helper method to get tasks by priority
    public LiveData<List<Task>> getTasksByPriority(int priorityLevel) {
        return taskDao.getTasksByPriority(priorityLevel);
    }
}
