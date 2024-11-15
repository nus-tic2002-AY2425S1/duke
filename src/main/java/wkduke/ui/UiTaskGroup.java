package wkduke.ui;

import wkduke.task.Task;

import java.util.List;

/**
 * Represents a group of tasks for display in the UI, including an optional header and footer.
 */
public record UiTaskGroup(String header, String footer, List<Task> tasks) {
    /**
     * Constructs a UiTaskGroup with the specified header, footer, and list of tasks.
     *
     * @param header The header text to display above the task group.
     * @param footer The footer text to display below the task group.
     * @param tasks  The list of tasks in this group.
     */
    public UiTaskGroup {
    }

    /**
     * Returns the header text of the task group.
     *
     * @return The header text.
     */
    @Override
    public String footer() {
        return footer;
    }

    /**
     * Returns the footer text of the task group.
     *
     * @return The footer text.
     */
    @Override
    public String header() {
        return header;
    }

    /**
     * Returns the list of tasks in the task group.
     *
     * @return The list of tasks.
     */
    @Override
    public List<Task> tasks() {
        return tasks;
    }
}