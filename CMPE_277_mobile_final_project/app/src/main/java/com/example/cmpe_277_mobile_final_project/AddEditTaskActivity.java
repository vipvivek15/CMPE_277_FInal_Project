package com.example.cmpe_277_mobile_final_project;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import java.util.Calendar;

public class AddEditTaskActivity extends AppCompatActivity {
    public static final String EXTRA_TASK_ID = "task_id";

    private EditText editTextTitle, editTextDescription;
    private Spinner prioritySpinner;
    private Button dueDateButton;
    private TaskViewModel taskViewModel;
    private Calendar dueDateCalendar;
    private int taskId = -1; // Default to -1 for new tasks

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        prioritySpinner = findViewById(R.id.prioritySpinner);
        dueDateButton = findViewById(R.id.dueDateButton);
        Button saveButton = findViewById(R.id.saveButton);

        // Initialize ViewModel
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        // Initialize Calendar for due date
        dueDateCalendar = Calendar.getInstance();

        // Check if we're editing an existing task or creating a new one
        if (getIntent().hasExtra(EXTRA_TASK_ID)) {
            setTitle("Edit Task");
            taskId = getIntent().getIntExtra(EXTRA_TASK_ID, -1);
            loadTaskData(taskId);
        } else {
            setTitle("Add New Task");
        }

        // Set up the Date Picker dialog for the due date button
        dueDateButton.setOnClickListener(view -> showDatePickerDialog());

        // Save button to save or update the task
        saveButton.setOnClickListener(view -> saveTask());
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    dueDateCalendar.set(Calendar.YEAR, year);
                    dueDateCalendar.set(Calendar.MONTH, month);
                    dueDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateDueDateButton();
                },
                dueDateCalendar.get(Calendar.YEAR),
                dueDateCalendar.get(Calendar.MONTH),
                dueDateCalendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void updateDueDateButton() {
        String date = dueDateCalendar.get(Calendar.DAY_OF_MONTH) + "/" +
                (dueDateCalendar.get(Calendar.MONTH) + 1) + "/" +
                dueDateCalendar.get(Calendar.YEAR);
        dueDateButton.setText(date);
    }

    private void loadTaskData(int taskId) {
        taskViewModel.getAllTasks().observe(this, tasks -> {
            for (Task task : tasks) {
                if (task.getId() == taskId) {
                    editTextTitle.setText(task.getTitle());
                    editTextDescription.setText(task.getDescription());
                    prioritySpinner.setSelection(task.getPriority());
                    dueDateCalendar.setTime(task.getDueDate());
                    updateDueDateButton();
                    break;
                }
            }
        });
    }

    private void saveTask() {
        String title = editTextTitle.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        int priority = prioritySpinner.getSelectedItemPosition();

        if (title.isEmpty()) {
            Toast.makeText(this, "Title cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Task task = new Task(title, description, dueDateCalendar.getTime(), priority, dueDateCalendar.getTime(), false);
        if (taskId != -1) {
            task.setId(taskId);
            taskViewModel.update(task);
            Toast.makeText(this, "Task updated", Toast.LENGTH_SHORT).show();
        } else {
            taskViewModel.insert(task);
            Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
