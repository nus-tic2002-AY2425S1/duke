package task;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import common.Constants;
import common.Messages;
import exception.CommandException;

import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Represents a list of tasks.
 * It provides methods to manage, manipulate, and do operations on the tasks list.
 * Functionalities include adding, deleting, marking, unmarking and retrieving tasks in the list.
 * It can also check tasks on specific dates and retrieve the size of the {@code TaskList}.
 */
public class TaskList {
    // https://stackoverflow.com/questions/2279030/type-list-vs-type-arraylist-in-java
    private final List<Task> taskList;

    private static final String SPACE = Constants.SPACE;

    /**
     * Initializes / constructs the {@code TaskList} as an empty list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the list of tasks in the {@code TaskList}.
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Retrieves the size of the {@code TaskList}.
     * 
     * @return the total number of tasks in the {@code TaskList}.
     */
    public int getSize() {
        return getTaskList().size();
    }

    /**
     * Checks if the {@code TaskList} is empty.
     * 
     * @return true if the {@code TaskList} is empty; false otherwise.
     */
    public boolean isEmpty() {
        return getTaskList().isEmpty();
    }

    public boolean hasTask(Task task) {
        return getTaskList().contains(task);
    }

    /**
     * Retrieves the task at a specific index in the {@code TaskList}.
     * 
     * @param index represents the index of the task to retrieve.
     * @return the task at the specified index.
     */
    public Task getTask(int index) {
        return getTaskList().get(index);
    }

    /**
     * Adds a new task to the {@code TaskList}.
     * 
     * @param task represents the task to be added. If it is null, the task will not be added.
     */
    // Adds a new task
    public void addTask(Task task) {
        if (task != null) {
            getTaskList().add(task);
        }
    }

    /**
     * Removes a task from the list.
     * 
     * @param task represents the task to be removed from the {@code TaskList}.
     * @return true if the task is removed successfully; false otherwise.
     */
    public boolean removeTask(Task task) {
        if (getTaskList().contains(task)) {
            return getTaskList().remove(task);
        } else {
            return false;
        }
    } 

    /**
     * Makes a task at a specified index as done. 
     * Returns false if the task has already been marked done before this method is called.
     * 
     * @param taskToMark represents the task to mark.
     * @return true if the task is marked successfully; false otherwise.
     */
    public boolean markTask(Task taskToMark) {
        if (taskToMark.getIsDone()) {       // Task has already been marked as done
            return false;
        } else {
            taskToMark.setDone(true);
            return true;
        }
    }

    /**
     * Marks a task at a specified index as not done.
     * Returns false if the task has already been marked as not done before this method is called.
     * 
     * @param taskToUnmark represents the task to unmark.
     * @return true if the task is unmarked successfully; false otherwise.
     */
    public boolean unmarkTask(Task taskToUnmark) {
        if (!taskToUnmark.getIsDone()) {        // Task has already been marked as not done
            return false;
        } else {
            taskToUnmark.setDone(false);
            return true;
        }
    }

    // Common helper function for execute method of MarkCommand, UnmarkCommand, and DeleteCommand
    public Task getTaskForOperation(int taskNumber) throws CommandException {
        if (isEmpty()) {
            throw new CommandException(Messages.MESSAGE_EMPTY_TASKLIST);
        }

        Task task;

        String MESSAGE_NONEXISTENT_TASK = Messages.MESSAGE_NONEXISTENT_TASK_PRE + SPACE +
            taskNumber + SPACE + Messages.MESSAGE_NONEXISTENT_TASK_POST;
        String MESSAGE_USAGE = Messages.MESSAGE_ENTER_VALID_TASK_NUMBER + SPACE + getSize() + Constants.DOT;

        try {
            task = getTask(taskNumber - 1);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new CommandException(Messages.ERROR_TASK_NONEXISTENT, MESSAGE_NONEXISTENT_TASK, MESSAGE_USAGE);
        }

        return task;
    }

    /**
     * Returns the appropriate word for "task" based on the number of tasks in the {@code TaskList}.
     * 
     * @return "task" if there is only one task in the {@code TaskList}; "tasks" if there are multiple.
     */
    public String getTaskWord() {
        StringBuilder taskWordStringBuilder = new StringBuilder(SPACE + Constants.TASK);

        if (getSize() != 1) {       // Only 1 is singular
            taskWordStringBuilder.append(Constants.S);
        }

        return taskWordStringBuilder.toString();
    }

    /**
     * Retrieves all tasks that occur on a specific date.
     *
     * @param date represents the date to check against.
     * @return a list of tasks that occur on a specified date.
     */
    public List<Task> getTasksOnDate(LocalDate date) {
        List<Task> tasksOnDate = new ArrayList<>();
        for (Task task : getTaskList()) {
            if (task.isOnDate(date)) {
                tasksOnDate.add(task);
            }
        }
        return tasksOnDate;
    }

    public TaskList getScheduledTasks(LocalDate date) {
        // Retrieve all tasks scheduled on the specified date
        List<Task> tasksOnDate = new ArrayList<>();

        // Add tasks that satisfy the date into the tasksOnDate list
        for (Task task : getTaskList()) {
            if (task.isOnDate(date)) {
                tasksOnDate.add(task);
            }
        }

        // Sort the tasks by their LocalDateTime
        // https://stackoverflow.com/questions/2784514/sort-arraylist-of-custom-objects-by-property
        tasksOnDate.sort(new Comparator<Task>() {
            @Override
            public int compare(Task taskA, Task taskB) {
                LocalDateTime dateTimeA = taskA.getTaskDateTime();
                LocalDateTime dateTimeB = taskB.getTaskDateTime();

                // https://www.javatpoint.com/compare-time-in-java
                // If dateTimeA.compareTo(dateTimeB) > 0, then dateTimeA > dateTimeB, i.e. dateTimeA is after dateTimeB
                // Handle null values. Deadlines can have no time.
                if (dateTimeA == null && dateTimeB == null) {
                    return 0;
                } else if (dateTimeA == null) {
                    return 1;       // Put dateTimeA before dateTimeB
                } else if (dateTimeB == null) {
                    return -1;      // Put dateTimeB after dateTimeA
                }

                return dateTimeA.compareTo(dateTimeB);
            }
        });

        return new TaskList(tasksOnDate);
    }

    /**
     * Retrieves all tasks that matches the specified description.
     * 
     * @param description represents the description to check against.
     * @return a list of tasks that has the same description as the specified one.
     */
    public TaskList getAllTasksWithMatchingDescription(String description) {
        TaskList tasksWithMatchingDescription = new TaskList();

        for (Task task : getTaskList()) {
            String taskDescription = task.getDescription();
            if (taskDescription.contains(description)) {
                tasksWithMatchingDescription.addTask(task);
            }
        }

        return tasksWithMatchingDescription;
    }
}
