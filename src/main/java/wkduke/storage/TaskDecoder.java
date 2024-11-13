package wkduke.storage;

import wkduke.common.Messages;
import wkduke.exception.FileContentException;
import wkduke.exception.TaskFormatException;
import wkduke.parser.TimeParser;
import wkduke.task.*;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Decodes individual tasks from their encoded string format into {@code Task} objects.
 * Supports decoding of {@code Todo}, {@code Deadline}, and {@code Event} tasks.
 */
public class TaskDecoder {
    public static final Pattern TASK_PATTERN = Pattern.compile(
            "(?<taskType>[TDE]) \\| (?<taskPriority>[LMH]) \\| (?<taskStatus>[01]) \\| (?<taskDescription>[^|]+)"
                    + "(?: \\| (?<by>[^|]+))?"   // Optional by, only for D (Deadline task)
                    + "(?: \\| (?<from>[^|]+) \\| (?<to>[^|]+))?" // Optional from and to, only for E (Event task)
    );

    /**
     * Creates a Deadline task from the provided matcher data.
     *
     * @param matcher     The matcher containing the captured task data.
     * @param description The description of the deadline task.
     * @param isDone      The completion status of the task.
     * @param priority    The priority of the task.
     * @return A {@code Deadline} task object with the specified attributes.
     * @throws FileContentException If the "by" field is missing.
     * @throws TaskFormatException  If an error occurs in date-time parsing.
     */
    private static Task createDeadlineTask(Matcher matcher, String description, boolean isDone, TaskPriority priority)
            throws FileContentException, TaskFormatException {
        String by = matcher.group("by");
        if (by == null) {
            throw new FileContentException(Messages.MESSAGE_INVALID_DEADLINE_ENCODED);
        }
        LocalDateTime dateTime = TimeParser.parseDateTime(by);
        return new Deadline(description, dateTime, isDone, priority);
    }

    /**
     * Creates an Event task from the provided matcher data.
     *
     * @param matcher     The matcher containing the captured task data.
     * @param description The description of the event task.
     * @param isDone      The completion status of the task.
     * @param priority    The priority of the task.
     * @return An {@code Event} task object with the specified attributes.
     * @throws FileContentException If either "from" or "to" field is missing.
     * @throws TaskFormatException  If an error occurs in date-time parsing.
     */
    private static Task createEventTask(Matcher matcher, String description, boolean isDone, TaskPriority priority)
            throws FileContentException, TaskFormatException {
        String from = matcher.group("from");
        String to = matcher.group("to");
        if (from == null || to == null) {
            throw new FileContentException(Messages.MESSAGE_INVALID_EVENT_ENCODED);
        }
        LocalDateTime fromDateTime = TimeParser.parseDateTime(from);
        LocalDateTime toDateTime = TimeParser.parseDateTime(to);
        return new Event(description, fromDateTime, toDateTime, isDone, priority);
    }

    /**
     * Decodes a {@code Task} object from the provided matcher data.
     *
     * @param matcher The matcher containing captured task data.
     * @return The decoded {@code Task} object.
     * @throws FileContentException If required fields for the task are missing or invalid.
     * @throws TaskFormatException  If an error occurs in date-time parsing.
     */
    public static Task createTaskFromMatcher(Matcher matcher) throws FileContentException, TaskFormatException {
        TaskType taskType = TaskType.fromCode(matcher.group("taskType"));
        String description = matcher.group("taskDescription");
        TaskPriority priority = TaskPriority.fromCode(matcher.group("taskPriority"));
        boolean isDone = "1".equals(matcher.group("taskStatus"));

        return switch (taskType) {
            case TODO -> new Todo(description, isDone, priority);
            case DEADLINE -> createDeadlineTask(matcher, description, isDone, priority);
            case EVENT -> createEventTask(matcher, description, isDone, priority);
        };
    }

    /**
     * Decodes a single encoded task string into a {@code Task} object.
     *
     * @param encodedTask The encoded task string.
     * @return A {@code Task} representing the decoded task.
     * @throws FileContentException If the encoded task has an invalid format.
     */
    public static Task decodeTask(String encodedTask) throws FileContentException {
        final Matcher matcher = TASK_PATTERN.matcher(encodedTask);
        if (!matcher.matches()) {
            throw new FileContentException(
                    Messages.MESSAGE_INVALID_TASK_ENCODED_FORMAT, String.format("EncodedTask='%s'", encodedTask)
            );
        }
        try {
            return createTaskFromMatcher(matcher);
        } catch (FileContentException e) {
            e.setDetail(String.format("EncodedTask='%s'", encodedTask));
            throw e;
        } catch (TaskFormatException e) {
            throw new FileContentException(
                    e.getMessage(), String.format("EncodedTask='%s'", encodedTask), e.getHelp()
            );
        }
    }
}
