package wkduke.storage;

import wkduke.common.Messages;
import wkduke.exception.FileContentException;
import wkduke.exception.TaskFormatException;
import wkduke.parser.TimeParser;
import wkduke.task.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Decodes task data from storage format into Task objects.
 * Supports decoding Todo, Deadline, and Event tasks from encoded strings.
 */
//Solution below inspired by https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/AddressBookDecoder.java
public class TaskListDecoder {
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
     * Creates a specific {@code Task} object (Todo, Deadline, or Event) based on the task type.
     *
     * @param matcher The matcher containing the captured task data.
     * @return The appropriate {@code Task} object.
     * @throws FileContentException If required fields for the task type are missing or invalid.
     * @throws TaskFormatException  If an error occurs in date-time parsing.
     */
    private static Task createTaskFromMatcher(Matcher matcher) throws FileContentException, TaskFormatException {
        TaskType taskType = TaskType.fromCode(matcher.group("taskType"));
        String taskDescription = matcher.group("taskDescription");
        TaskPriority taskPriority = TaskPriority.fromCode(matcher.group("taskPriority"));
        boolean isDone = "1".equals(matcher.group("taskStatus"));

        // An invalid task type scenario is already handled earlier.
        return switch (taskType) {
            case TODO -> new Todo(taskDescription, isDone, taskPriority);
            case DEADLINE -> createDeadlineTask(matcher, taskDescription, isDone, taskPriority);
            case EVENT -> createEventTask(matcher, taskDescription, isDone, taskPriority);
        };
    }

    /**
     * Decodes a single encoded task string into a {@code Task} object.
     *
     * @param encodedTask The encoded task string.
     * @return A {@code Task} representing the decoded task.
     * @throws FileContentException If the encoded task has an invalid format.
     */
    public static Task decodeTaskFromString(String encodedTask) throws FileContentException {
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

    /**
     * Decodes a list of encoded task strings into a {@code TaskList}.
     *
     * @param encodedTasks The list of encoded task strings.
     * @return A {@code TaskList} containing the decoded tasks.
     * @throws FileContentException If any encoded task has an invalid format.
     */
    public static TaskList decodeTaskList(List<String> encodedTasks) throws FileContentException {
        TaskList taskList = new TaskList();
        for (String encodedTask : encodedTasks) {
            taskList.addTask(decodeTaskFromString(encodedTask));
        }
        return taskList;
    }
}
