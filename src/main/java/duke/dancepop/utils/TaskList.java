package duke.dancepop.utils;

import duke.dancepop.entities.Deadline;
import duke.dancepop.entities.Event;
import duke.dancepop.entities.Task;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Utility class for managing a list of tasks.
 */
public class TaskList {
    private static final List<Task> tasks = new ArrayList<>();

    /**
     * Retrieves the task at the specified index.
     *
     * @param i The index of the task to retrieve.
     * @return The Task object at the specified index.
     */
    public static Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Adds a list of tasks to the task list.
     *
     * @param t The list of tasks to add.
     */
    public static void addAll(List<Task> t) {
        tasks.addAll(t);
    }

    /**
     * Adds a task to the task list.
     *
     * @param t The task to add.
     */
    public static void add(Task t) {
        tasks.add(t);
    }

    /**
     * Adds a task to the task list and prints a confirmation message.
     *
     * @param t The task to add.
     */
    public static void addAndPrint(Task t) {
        add(t);
        Log.printMsg("Got it. I've added this task:", t.toString(), "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Removes the task at the specified index from the task list and prints a confirmation message.
     *
     * @param i The index of the task to remove.
     */
    public static void remove(int i) {
        Task t = tasks.get(i);
        tasks.remove(i);
        Log.printMsg("Noted. I've removed this task:", t.toString(), "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Marks the task at the specified index as done and prints a confirmation message.
     *
     * @param i The index of the task to mark as done.
     */
    public static void markDone(int i) {
        Task t = get(i);
        t.setIsDone(true);
        Log.printMsg("Nice! I've marked this task as done:", t.toString());
    }

    /**
     * Marks the task at the specified index as not done and prints a confirmation message.
     *
     * @param i The index of the task to mark as not done.
     */
    public static void unmarkDone(int i) {
        Task t = get(i);
        t.setIsDone(false);
        Log.printMsg("OK, I've marked this task as not done yet:", t.toString());
    }

    /**
     * Clears all tasks from the task list.
     */
    public static void clear() {
        tasks.clear();
    }

    /**
     * Retrieves the list of all tasks.
     *
     * @return The list of tasks.
     */
    public static List<Task> getTasks() {
        return tasks;
    }

    /**
     * Print tasks matching the description (case-insensitive).
     *
     * @param value The substring to match in task descriptions.
     */
    public static void printByDescriptionContains(String value) {
        String header = "Here are the matching tasks in your list:";
        printTasks(
                header,
                tasks.stream().filter(task -> task.getDescription().toLowerCase().contains(value.toLowerCase()))
        );
    }

    /**
     * Print all tasks.
     */
    public static void print() {
        printTasks("Here are the tasks in your list:", tasks.stream());
    }

    /**
     * Print deadlines and events occurring on the specified date.
     *
     * @param localDateTime The date-time to filter deadlines and events.
     */
    public static void print(LocalDateTime localDateTime) {
        String header = MessageFormat.format(
                "Here are the deadlines and events in your list on: {0}",
                DateTimeUtil.toString(localDateTime)
        );

        printTasks(
                header,
                tasks.stream().filter(task -> isTaskOnDate(task, localDateTime))
        );
    }

    /**
     * Helper method to print tasks with a given header and task stream.
     *
     * @param header     The header message to print.
     * @param taskStream The stream of tasks to print.
     */
    private static void printTasks(String header, Stream<Task> taskStream) {
        String[] taskDescriptions = taskStream
                .map(Task::toString)
                .toArray(String[]::new);

        if (taskDescriptions.length == 0) {
            Log.printMsg("There are no tasks found.");
            return;
        }

        Log.printSeqMsg(header, taskDescriptions);
    }

    /**
     * Determine if a task occurs on the specified date-time.
     *
     * @param task          The task to check.
     * @param localDateTime The date-time to match.
     * @return True if the task occurs on the specified date-time, false otherwise.
     */
    private static boolean isTaskOnDate(Task task, LocalDateTime localDateTime) {
        if (task instanceof Event event) {
            // start >= localDateTime <= end
            return !localDateTime.isBefore(event.getStart()) && !localDateTime.isAfter(event.getEnd());
        } else if (task instanceof Deadline deadline) {
            return localDateTime.toLocalDate().equals(deadline.getDeadline().toLocalDate());
        }
        return false;
    }
}
