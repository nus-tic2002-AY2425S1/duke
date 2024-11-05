package Chad.TaskList;

import java.util.ArrayList;

import Chad.Exception.ChadException;
import Chad.Parser.ChadDate;

/**
 * The TaskList class manages a collection of Task objects.
 * It provides methods to add, delete, and retrieve tasks,
 * as well as to filter tasks based on deadlines or event dates.
 */
public class TaskList {
    private ArrayList<Task> tasks; // A list to store task objects

    /**
     * Constructs a new empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>(); // Initializes the tasks list
    }

    /**
     * Retrieves all tasks that have the specified deadline date.
     *
     * @param inputDate The date to filter tasks by.
     * @return A TaskList containing tasks that match the given deadline.
     * @throws ChadException If the input date format is invalid.
     */
    public TaskList getTaskbyDeadline(String inputDate) throws ChadException {
        // Parse and validate the input date
        String parsedInputDate = validateDate(inputDate);

        TaskList tasksByDate = new TaskList(); // Create a new TaskList for tasks matching the date
        for (Task task : tasks) {
            addMatchingTaskbyDate(task, parsedInputDate, tasksByDate); // Add matching tasks to the new list
        }

        return tasksByDate; // Return the filtered TaskList
    }
    public TaskList findTaskbyIdx(String desptionIdx) throws ChadException {
        // check null input ... should be done before call
        TaskList tasksByIdx = new TaskList(); // Create a new TaskList for tasks matching the date
        for (Task task : tasks) {
            addMatchingTaskbyIdx(task, desptionIdx, tasksByIdx); // Add matching tasks to the new list
        }

        return tasksByIdx; // Return the filtered TaskList
    }

    /**
     * Validates the input date and parses it into a LocalDate.
     *
     * @param inputDate The date string to validate and parse.
     * @return The parsed date as a String in the required format.
     * @throws ChadException If the date format is invalid.
     */
    private String validateDate(String inputDate) throws ChadException {
        String parsedDate = ChadDate.parseDate(inputDate); // Attempt to parse the date
        if (parsedDate == null) {
            throw new ChadException("Invalid input date provided."); // Check if parsing failed
        }
        return parsedDate; // Return the valid parsed date
    }

    /**
     * Adds a task to the filtered list based on its type (Deadline or Event).
     *
     * @param task The task to check and potentially add.
     * @param inputDate The date to match against.
     * @param tasksByDate The TaskList to add matching tasks to.
     */
    private void addMatchingTaskbyDate(Task task, String inputDate, TaskList tasksByDate) {
        if (task instanceof Deadline) {
            handleDeadline((Deadline) task, inputDate, tasksByDate); // Check and add deadline tasks
        } else if (task instanceof Event) {
            handleEvent((Event) task, inputDate, tasksByDate); // Check and add event tasks
        }
    }
    private void addMatchingTaskbyIdx(Task task, String inpuidx, TaskList tasksByIdx) {
        if (task.getDescription().contains(inpuidx)) {
            tasksByIdx.addTask(task);
        }

    }

    /**
     * Handles adding Deadline tasks that match the input date.
     *
     * @param deadlineTask The deadline task to check.
     * @param inputDate The input date to compare against.
     * @param tasksByDate The TaskList to add matching tasks to.
     */
    private void handleDeadline(Deadline deadlineTask, String inputDate, TaskList tasksByDate) {
        if (inputDate.equals(deadlineTask.getDuedate())) { // Check for matching due date
            tasksByDate.addTask(deadlineTask); // Add matching task to the list
        }
    }

    /**
     * Handles adding Event tasks that match the input date.
     *
     * @param eventTask The event task to check.
     * @param inputDate The input date to compare against.
     * @param tasksByDate The TaskList to add matching tasks to.
     */
    private void handleEvent(Event eventTask, String inputDate, TaskList tasksByDate) {
        if (inputDate.equals(eventTask.getEnddate())) { // Check for matching end date
            tasksByDate.addTask(eventTask); // Add matching task to the list
        }
    }

    /**
     * Adds a task to the internal task list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task); // Add the task to the list
    }

    /**
     * Deletes a task from the list by its index.
     *
     * @param index The index of the task to be deleted.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */


    public void deleteTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task index: " + (index + 1));
        }
        tasks.remove(index); // Remove the task from the tasks list
    }

    /**
 * Marks a task as done based on its index in the task list.
 * 
 * @param index The index of the task to be marked as done.
 */
public void markTask(int index) {
    Task task = tasks.get(index); // Retrieve the task at the specified index
    task.setTask(); // Mark the task as done
}

/**
 * Unmarks a task, setting its status back to not done based on its index.
 * 
 * @param index The index of the task to be unmarked.
 */
public void unmarkTask(int index) {
    Task task = tasks.get(index); // Retrieve the task at the specified index
    task.unSetTask(); // Set the task's status to not done
}

/**
 * Prints all tasks in the task list to the console.
 * Each task is preceded by its index in the list.
 */
public void printTasks() {
    for (int i = 0; i < tasks.size(); i++) { // Iterate through all tasks in the list
        System.out.println((i + 1) + ". " + tasks.get(i).toString()); // Print task index and description
    }
}

/**
 * Retrieves a task by its ID (index) from the task list.
 * 
 * @param taskId The index of the task to retrieve.
 * @return The task at the specified index.
 */
public Task getTaskById(Integer taskId) {
    return tasks.get(taskId); // Return the task at the given index
}

/**
 * Returns the total number of tasks in the task list.
 * 
 * @return The size of the task list.
 */
public Integer getNoOfTask() {
    return tasks.size(); // Return the number of tasks
}

/**
 * Converts the list of tasks to a formatted string for storage or display.
 * If the task list is empty, returns a message indicating that.
 * 
 * @return A string representation of the task list.
 */
@Override
public String toString() {
    if (tasks.isEmpty()) {
        return "The list is empty."; // Message when no tasks are present
    }
    
    StringBuilder sb = new StringBuilder(); // StringBuilder for efficient string concatenation
    for (int i = 0; i < tasks.size(); i++) { // Iterate over tasks
        sb.append(i + 1).append(". ").append(tasks.get(i).toString()).append(System.lineSeparator()); // Append task details
    }
    return sb.toString().trim(); // Convert StringBuilder to String and trim trailing newline characters
}
}
