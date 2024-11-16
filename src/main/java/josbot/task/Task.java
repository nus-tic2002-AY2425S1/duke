package josbot.task;

import java.time.LocalDateTime;

/**
 * Represent Task
 */

public class Task {
    protected String description;
    protected boolean isDone;
    protected String tag;

    /**
     * Creates a Task with its description
     *
     * @param description description of the event task
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = "";
    }

    /**
     * Return String of Task description
     *
     * @return Task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Return String of Icon that marked whether a task is marked done or not.
     * return of 'X' represents that the task is done
     * return of ' ' represents that the task is not done
     *
     * @return Task isDone String
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Set a tag to a task
     *
     * @param tag represents the tag value
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Return String of the tag value
     *
     * @return String of the tag value
     */
    public String getTag() {
        if (tag.equals("")) {
            return "";
        } else {
            return tag;
        }
    }

    /**
     * Mark task as done
     */

    public void markAsDone() {
        isDone = true;
    }

    /**
     * Mark task as not done
     */

    public void markAsNotDone() {
        isDone = false;
    }


    /**
     * Return a String of the task type
     *
     * @return a String of the task type
     */
    public String getType() {
        return "";
    }

    /**
     * Return a LocalDateTime of a particular task
     *
     * @return a LocalDateTime of a particular task
     */

    public LocalDateTime getDateTime() {
        return null;
    }


    /**
     * Return String of the Task details
     *
     * @return String of the Tasks details
     */
    public String toString() {
        if (getTag().isEmpty()) {
            return "[" + getStatusIcon() + "] " + description;
        } else {
            return "[" + getStatusIcon() + "] [#" + getTag() + "] " + description;
        }

    }
}


