package duke.dancepop.utils;

import duke.dancepop.entities.Deadline;
import duke.dancepop.entities.Event;
import duke.dancepop.entities.Task;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public static void print() {
        String header = "Here are the tasks in your list:";
        String[] taskDescriptions = tasks.stream()
                .map(Task::toString)
                .toArray(String[]::new);
        Log.printSeqMsg(header, taskDescriptions);
    }

    public static void print(LocalDateTime localDateTime) {
        String header = "Here are the deadlines and events in your list on: {0}";
        header = MessageFormat.format(header, DateTimeUtil.toString(localDateTime));
        String[] taskDescriptions = tasks.stream()
                .filter(task -> {
                    if (task instanceof Event event) {
                        // start >= localDateTime <= end
                        return !localDateTime.isBefore(event.getStart()) && !localDateTime.isAfter(event.getEnd());
                    } else if (task instanceof Deadline deadline) {
                        return localDateTime.toLocalDate().equals(deadline.getDeadline().toLocalDate());
                    }
                    return false;
                })
                .map(Task::toString)
                .toArray(String[]::new);

        Log.printSeqMsg(header, taskDescriptions);
    }
}
