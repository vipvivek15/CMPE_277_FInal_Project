package com.example.cmpe_277_mobile_final_project;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insertTask(Task task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Query("SELECT * FROM tasks ORDER BY dueDate ASC, priority ASC")
    LiveData<List<Task>> getAllTasks();

    // Additional query method for getting tasks by priority
    @Query("SELECT * FROM tasks WHERE priority = :priorityLevel ORDER BY dueDate ASC")
    LiveData<List<Task>> getTasksByPriority(int priorityLevel);
}
