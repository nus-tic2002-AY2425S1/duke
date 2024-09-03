package duke.dancepop;

import duke.dancepop.entities.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final List<Task> tasks = new ArrayList<>();

    public static Task get(int i) {
       return tasks.get(i);
    }

    public static void add(Task t) {
        tasks.add(t);
        // TODO: Refactor all these strings into enum or something
        Log.printMsg("Got it. I've added this task:", t.toString(), "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void remove(int i) {
        tasks.remove(i);
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

    public static void print() {
        String header = "Here are the tasks in your list:";
        String[] taskDescriptions = tasks.stream()
            .map(Task::toString)
            .toArray(String[]::new);
        Log.printSeqMsg(header, taskDescriptions);
    }
}
