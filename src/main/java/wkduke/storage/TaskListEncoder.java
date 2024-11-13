package wkduke.storage;

import wkduke.parser.TimeParser;
import wkduke.task.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Encodes a {@code TaskList} into a format suitable for file storage.
 * Each task in the list is converted to a string representation.
 */
//Solution below inspired by https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/AddressBookEncoder.java
public class TaskListEncoder {
    /**
     * Encodes a {@code Deadline} task.
     *
     * @param deadline The {@code Deadline} task to encode.
     * @return The encoded string representation of the task.
     */
    private static String encodeDeadlineTask(Deadline deadline) {
        return String.format("D | %s | %s | %s | %s",
                deadline.getPriority(),
                deadline.isDone() ? "1" : "0",
                deadline.getDescription(),
                deadline.getBy().format(TimeParser.ENCODING_FORMATTER)
        );
    }

    /**
     * Encodes an {@code Event} task.
     *
     * @param event The {@code Event} task to encode.
     * @return The encoded string representation of the task.
     */
    private static String encodeEventTask(Event event) {
        return String.format("E | %s | %s | %s | %s | %s",
                event.getPriority(),
                event.isDone() ? "1" : "0",
                event.getDescription(),
                event.getFrom().format(TimeParser.ENCODING_FORMATTER),
                event.getTo().format(TimeParser.ENCODING_FORMATTER)
        );
    }

    /**
     * Encodes a single {@code Task} into a string based on its type.
     *
     * @param task The task to encode.
     * @return The encoded string representation of the task.
     */
    private static String encodeTask(Task task) {
        if (task instanceof Todo) {
            return encodeTodoTask((Todo) task);
        } else if (task instanceof Deadline) {
            return encodeDeadlineTask((Deadline) task);
        } else if (task instanceof Event) {
            return encodeEventTask((Event) task);
        }
        throw new IllegalArgumentException("Unknown task type");
    }

    /**
     * Encodes the tasks in the given {@code TaskList} into a list of strings.
     *
     * @param taskList The {@code TaskList} containing tasks to encode.
     * @return A {@code List<String>} where each string represents an encoded task.
     */
    public static List<String> encodeTaskList(TaskList taskList) {
        final List<String> encodedTasks = new ArrayList<>();

        for (Task task : taskList.getAllTask()) {
            encodedTasks.add(encodeTask(task));
        }
        return encodedTasks;
    }

    /**
     * Encodes a {@code Todo} task.
     *
     * @param todo The {@code Todo} task to encode.
     * @return The encoded string representation of the task.
     */
    private static String encodeTodoTask(Todo todo) {
        return String.format("T | %s | %s | %s",
                todo.getPriority(),
                todo.isDone() ? "1" : "0",
                todo.getDescription()
        );
    }
}
