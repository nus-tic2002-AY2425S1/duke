package wkduke.storage.encoder;

import wkduke.parser.TimeParser;
import wkduke.task.Deadline;
import wkduke.task.Event;
import wkduke.task.Task;
import wkduke.task.Todo;

/**
 * Encodes different types of {@code Task} objects into their string representations for file storage.
 * Supports encoding {@code Todo}, {@code Deadline}, and {@code Event} task types.
 */
public class TaskEncoder {
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
    public static String encodeTask(Task task) {
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
