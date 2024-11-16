package duke.dancepop.utils;

import duke.dancepop.entities.Deadline;
import duke.dancepop.entities.Event;
import duke.dancepop.entities.Task;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TaskList {
    private static final List<Task> tasks = new ArrayList<>();

    public static Task get(int i) {
        return tasks.get(i);
    }

    public static void add(Task t) {
        tasks.add(t);
    }

    public static void addAndPrint(Task t) {
        add(t);
        // TODO: Refactor all these strings into enum or something and also outside of this class
        Log.printMsg("Got it. I've added this task:", t.toString(), "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void remove(int i) {
        Task t = tasks.get(i);
        tasks.remove(i);
        Log.printMsg("Noted. I've removed this task:", t.toString(), "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void markDone(int i) {
        Task t = get(i);
        t.setDone(true);
        Log.printMsg("Nice! I've marked this task as done:", t.toString());
    }

    public static void unmarkDone(int i) {
        Task t = get(i);
        t.setDone(false);
        Log.printMsg("OK, I've marked this task as not done yet:", t.toString());
    }

    public static void clear() {
        tasks.clear();
    }

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
