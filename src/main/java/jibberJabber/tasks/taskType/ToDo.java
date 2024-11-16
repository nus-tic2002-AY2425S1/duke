package jibberJabber.tasks.taskType;

import jibberJabber.tasks.Task;
/**
 * The todos class represents a Todos task (subclass) that extends the base Task class.
 */
public class ToDo extends Task {
    /**
     * Constructs a todos task with the user's input - task name
     *
     * @param taskName The name of the task.
     */
    public ToDo(String taskName) {
        super(taskName);
    }
    /**
     * Returns a formatted string representing the added todos task.
     *
     * @return The formatted string with task details including task type, and task name
     */
    @Override
    public String printAddedTask() {
        return "\t[T]" + super.printAddedTask();
    }
}
