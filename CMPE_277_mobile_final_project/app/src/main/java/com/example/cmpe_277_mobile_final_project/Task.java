package com.example.cmpe_277_mobile_final_project;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import java.util.Date;

@Entity(tableName = "tasks")
@TypeConverters(DateConverter.class)
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private Date dueDate;
    private int priority; // Example: 1 - High, 2 - Medium, 3 - Low
    private Date reminderTime;
    private boolean isCompleted;

    // Constructor
    public Task(String title, String description, Date dueDate, int priority, Date reminderTime, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.reminderTime = reminderTime;
        this.isCompleted = isCompleted;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }

    public Date getReminderTime() { return reminderTime; }
    public void setReminderTime(Date reminderTime) { this.reminderTime = reminderTime; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", priority=" + priority +
                ", reminderTime=" + reminderTime +
                ", isCompleted=" + isCompleted +
                '}';
    }
}

