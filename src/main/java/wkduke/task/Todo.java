package wkduke.task;

import java.time.LocalDateTime;

/**
 * Represents a Todo task with a description.
 * A Todo task has no associated date or deadline.
 */
public class Todo extends Task {
    /**
     * Constructs a {@code Todo} task with the specified description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a {@code Todo} task with the specified description, completion status, and priority.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     * @param priority    The priority level of the task.
     */
    public Todo(String description, boolean isDone, TaskPriority priority) {
        super(description, isDone, priority);
    }

    /**
     * Checks if the task is scheduled for or due on a specified date.
     * Since a Todo task has no associated date, this always returns {@code false}.
     *
     * @param targetDateTime The date to check against.
     * @return {@code false}, as Todo tasks have no associated date.
     */
    @Override
    public boolean isOnDate(LocalDateTime targetDateTime) {
        return false;
    }

    /**
     * Checks if this Todo task is equal to another object.
     * A Todo task is considered equal if it is of the same type and has the same
     * description and completion status as the specified task.
     *
     * @param obj The object to compare with this Todo task.
     * @return {@code true} if the specified object is a Todo task and is equal to this task; otherwise, {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Todo)) {
            return false;
        }
        return super.equals(obj);
    }

    /**
     * Returns a string representation of the Todo task, including its type and description.
     *
     * @return A {@code String} representing the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
