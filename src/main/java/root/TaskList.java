package root;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import tasks.*;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public void addTask(Task task) {
        tasks.add(task);
    }
    public void deleteTask(int index) {
        tasks.remove(index);
    }
    public int size() {
        return tasks.size();
    }
    public Task get(int index) {
        return tasks.get(index);
    }
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public List<Task> getTasksOnDate(LocalDate date) {
        return tasks.stream()
            .filter(task -> task instanceof Deadline && ((Deadline) task).getBy().toLocalDate().equals(date))
            .collect(Collectors.toList());
    }

    public void markTask(int index) {
        tasks.get(index).markAsDone();
    }

    public void unmarkTask(int index) {
        tasks.get(index).markAsNotDone();
    }

    public boolean checkForClash(LocalDateTime start, LocalDateTime end) {
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getBy().isAfter(start) && deadline.getBy().isBefore(end)) {
                    return true; 
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                LocalDateTime eventStart = event.getStart();
                LocalDateTime eventEnd = event.getEnd();
                if ((start.isBefore(eventEnd) && start.isAfter(eventStart)) || 
                    (end.isAfter(eventStart) && end.isBefore(eventEnd)) || 
                    (start.isEqual(eventStart) || end.isEqual(eventEnd))) {
                    return true;
                }
            }
        }
        return false; 
    }
}
