package Chad.TaskList;

import Chad.Parser.ChadDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList getTaskbyDeadline(String inputDate)
    {
        //deadline should be checked correct date type. and extracted day if needed.
        //check deadlineDate before pass as parameter
        //if incorrect deadlineDate, should call exception(user wrong input not program error)
        String inputDateToCheck = ChadDate.parseDate(inputDate);
        TaskList tasksByDate = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
    
            if (task instanceof Deadline) {
                // If the task is a Deadline, check its due date
                Deadline deadlineTask = (Deadline) task;
                String deadlineDuedate = ((Deadline) task).getDuedate();
                //the dueDate here is of 03 NOV 2024 format, cant be parsed...
                //better pase again and compare string
                boolean sameDay = inputDateToCheck.equals(deadlineDuedate);
                if (sameDay) {
                    tasksByDate.addTask(deadlineTask); // Add it to the list of tasks if dates match
                }
            } else if (task instanceof Event) {
                // If the task is an Event, check its date range
                Event eventTask = (Event) task;
                String eventEnddate = ((Event) task).getEnddate();
                boolean sameDay = inputDateToCheck.equals(eventEnddate);
                if (sameDay) {
                    tasksByDate.addTask(eventTask); // Add it to the list of tasks if the end date matches
                }
            }
        }
    
        return tasksByDate; // Return the filtered TaskList
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
    //its easier to delete by id
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task index: " + (index + 1));
        }
        tasks.remove(index); // Remove the task from the tasks list
    }

    public void markTask(int index) {
        Task task = tasks.get(index);
        task.setTask();
    }
    public void unmarkTask(int index) {
        Task task = tasks.get(index);
        task.unSetTask();
    }
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }
    public Task getTaskById(Integer taskId)
    {
        return tasks.get(taskId);
    }

    public Integer getNoOfTask()
    {
        return tasks.size();
    }

    // convert a list to string for storage?
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "The list is empty.";
        }
    
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i).toString()).append(System.lineSeparator());
        }
        return sb.toString().trim(); // Convert StringBuilder to String and trim the trailing newline
    }
}