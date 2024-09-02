package duke.dancepop;

import duke.dancepop.entities.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final List<Task> tasks = new ArrayList<>();;

    public static Task get(int i) {
       return tasks.get(i-1);
    }

    public static void add(Task t) {
        tasks.add(t);
    }

    public static void remove(int i) {
        tasks.remove(i-1);
    }

    public static void markDone(int i) {
        Task t = get(i);
        t.setDone(true);
    }

    public static void unmarkDone(int i) {
        Task t = get(i);
        t.setDone(false);
    }

    public static void print() {
        String[] taskDescriptions = tasks.stream()
            .map(Task::toString)
            .toArray(String[]::new);
        Log.printSeqMsg(taskDescriptions);
    }
}
