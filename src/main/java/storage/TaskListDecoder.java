package storage;

import common.Constants;
import common.Messages;
import exception.CommandException;
import exception.FileContentException;
import exception.TaskListDecoderException;
import parser.DateTimeParser;
import task.TaskList;
import task.Task;
import task.TaskType;
import task.CompletionStatus;
import task.Todo;
import task.Deadline;
import task.Event;
import task.FixedDuration;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Decodes a list of encoded tasks into a {@code TaskList} object.
 * This class interprets the specific format of each task and decodes the task
 * in different formats before creating the corresponding {@code Task} objects.
 */
public class TaskListDecoder {

    private static final String EXPECTED_FORMAT_TODO = "T | 0 | <task description>";
    private static final String EXPECTED_FORMAT_DEADLINE = "D | 0 | <task description> | <task deadline>";
    private static final String EXPECTED_FORMAT_EVENT =
        "E | 0 | <task description> | <event start date/time> | <event end date/time>";
    private static final String EXPECTED_FORMAT_FD = "FD | 0 | <task description> | <required duration in hours>";

    // Add a private constructor to hide the implicit public one.
    private TaskListDecoder() {

    }

    /**
     * Decodes a list of encoded tasks into a {@code TaskList} object, which will contain the decoded tasks.
     *
     * @param encodedTaskList represents a list of strings that contain the encoded tasks.
     * @return a {@code TaskList} object that contains the decoded tasks.
     * @throws FileContentException     if an error occurs while decoding the task on a specific line,
     *                                  i.e. the file content is invalid.
     * @throws TaskListDecoderException if an error occurs while decoding the task on a specific line.
     * @throws CommandException         if an error occurs while processing the command.
     */
    // Solution below referenced from https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/AddressBookDecoder.java#L34
    protected static TaskList decodeTaskList(List<String> encodedTaskList)
        throws FileContentException, TaskListDecoderException, CommandException {
        assert encodedTaskList != null : "Encoded task list should not be null";
        TaskList decodedTasks = new TaskList();
        for (String encodedTask : encodedTaskList) {
            Task decodedTask = decodeTaskFromString(encodedTask);
            decodedTasks.addTask(decodedTask);
        }
        return decodedTasks;
    }

    /**
     * Validates the encoded task string.
     * Ensures that the encoded task is not null or empty.
     * If the task is empty, a {@link FileContentException} is thrown with an appropriate error message.
     *
     * @param encodedTask The encoded task string to validate.
     * @throws FileContentException If the task string is empty.
     */
    private static void validateEncodedTask(String encodedTask) throws FileContentException {
        assert encodedTask != null : "Encoded task should not be null";
        if (encodedTask.isEmpty()) {
            throw new FileContentException(
                String.format("%s. %s.", Messages.MESSAGE_EMPTY_LINE, Messages.MESSAGE_INVALID_TASKS_DATA),
                String.format("Received `%s`", encodedTask),
                String.format("Expected format: `%s` or `%s` or `%s` or `%s`", EXPECTED_FORMAT_TODO,
                    EXPECTED_FORMAT_DEADLINE, EXPECTED_FORMAT_EVENT, EXPECTED_FORMAT_FD)
            );
        }
    }

    /**
     * Splits an encoded task string into its components based on the delimiter " | ".
     * Throws a {@link FileContentException} if the task does not have the required components.
     *
     * @param encodedTask The encoded task string to split.
     * @return An array of strings containing the task components.
     * @throws FileContentException If the task string does not contain enough components.
     */
    private static String[] splitEncodedTask(String encodedTask) throws FileContentException {
        assert encodedTask != null : "Encoded task should not be null";
        String[] taskData = encodedTask.split(" \\| ");
        if (taskData.length < 3) {
            throw new FileContentException(
                String.format("%s. %s.", Messages.MESSAGE_TASK_MISSING_COMPONENTS,
                    Messages.MESSAGE_INVALID_TASKS_DATA),
                String.format("Received `%s`", Arrays.toString(taskData)),
                String.format("Expected format: `%s` or `%s` or `%s`", EXPECTED_FORMAT_TODO,
                    EXPECTED_FORMAT_DEADLINE, EXPECTED_FORMAT_EVENT)
            );
        }

        return taskData;
    }

    /**
     * Retrieves the {@link TaskType} for the given task type string.
     * If the string does not match a valid task type, a {@link FileContentException} is thrown.
     *
     * @param taskTypeString The task type as a string (e.g., "TODO", "DEADLINE").
     * @return The corresponding {@link TaskType}.
     * @throws FileContentException If the task type string is invalid.
     */
    private static TaskType getTaskType(String taskTypeString) throws FileContentException {
        assert taskTypeString != null : "Task type string should not be null";
        final String ERROR_GET_TASKTYPE = "Error: Unknown task type.";
        final String VALID_TASK_TYPE = TaskType.getValidTaskType();
        TaskType taskType;

        try {
            taskType = TaskType.getTaskType(taskTypeString);
        } catch (IllegalArgumentException e) {
            throw new FileContentException(ERROR_GET_TASKTYPE,
                String.format("Received `%s`", taskTypeString.trim()),
                String.format("Expected `%s`", VALID_TASK_TYPE)
            );
        }

        return taskType;
    }

    /**
     * Converts the completion status string into a boolean value representing whether the task is done.
     * Throws a {@link FileContentException} if the string does not correspond to a valid completion status.
     *
     * @param isDoneString The completion status as a string (e.g., "DONE", "NOT_DONE").
     * @return True if the task is marked as done, false otherwise.
     * @throws FileContentException If the completion status string is invalid.
     */
    private static boolean getIsDone(String isDoneString) throws FileContentException {
        assert isDoneString != null : "Completion status string should not be null";
        CompletionStatus isDone;
        final String MESSAGE_INVALID_COMPLETION_STATUS = "Task has invalid completion status";

        try {
            isDone = CompletionStatus.getStatus(isDoneString);
        } catch (IllegalArgumentException e) {
            throw new FileContentException(MESSAGE_INVALID_COMPLETION_STATUS);
        }

        return isDone == CompletionStatus.DONE;
    }

    /**
     * Validates that the task data array has the correct length based on the task type.
     * Throws a {@link TaskListDecoderException} if the length of the task data does not match the expected length
     * for the specified task type.
     *
     * @param taskDataLength The length of the task data array.
     * @param taskType The type of the task (e.g., TODO, DEADLINE).
     * @param taskData The array of task data components.
     * @throws TaskListDecoderException If the task data length is incorrect for the given task type.
     */
    private static void validateTaskDataLength(int taskDataLength, TaskType taskType,
        String[] taskData) throws TaskListDecoderException {

        assert taskData != null : "Task data should not be null";

        int expectedTaskDataLength = 0;
        String expectedFormat = null;

        if (taskType.equals(TaskType.DEADLINE)) {
            expectedTaskDataLength = Constants.FOUR;
            expectedFormat = EXPECTED_FORMAT_DEADLINE;
        } else if (taskType.equals(TaskType.EVENT)) {
            expectedTaskDataLength = Constants.FIVE;
            expectedFormat = EXPECTED_FORMAT_EVENT;
        } else if (taskType.equals(TaskType.FIXED_DURATION)) {
            expectedTaskDataLength = Constants.FOUR;
            expectedFormat = EXPECTED_FORMAT_FD;
        }

        if (taskDataLength < expectedTaskDataLength) {
            throw new TaskListDecoderException(Messages.ERROR_INVALID_TASK_FORMAT + taskType,
                String.format("Received `%s`", Arrays.toString(taskData)),
                String.format("Expected format: `%s`", expectedFormat));
        }
    }

    /**
     * Decodes a single line of {@code encodedTask} String (from tasks file) into a {@code Task} object.
     *
     * @param encodedTask represents a String containing the encoded task.
     * @return a {@code Task} object that corresponds to the {@code encodedTask} String.
     * @throws FileContentException     if the encoded task format is invalid.
     * @throws TaskListDecoderException if an error occurs while decoding the task list,
     *                                  i.e. format of the encoded task (String) is invalid.
     * @throws CommandException         if an error related to command processing occurs,
     *                                  i.e. there is an issue while parsing date/time.
     */
    // Example encodedTask: T | 1 | read book
    private static Task decodeTaskFromString(String encodedTask)
        throws FileContentException, TaskListDecoderException, CommandException {

        validateEncodedTask(encodedTask);

        String[] taskData = splitEncodedTask(encodedTask);

        Task task;
        TaskType taskType = getTaskType(taskData[0].trim());
        boolean isDone = getIsDone(taskData[1].trim());
        String description = taskData[2].trim();

        validateTaskDataLength(taskData.length, taskType, taskData);

        switch (taskType) {
            case TODO:
                task = new Todo(description, isDone);
                break;

            case DEADLINE:
                // The tasks file contains a String representation of the time.
                // To create a deadline task, we need to parse the String into a LocalDateTime object.
                LocalDateTime due = DateTimeParser.parseOutputDateTime(taskData[3].trim());
                task = new Deadline(description, isDone, due);
                break;

            case EVENT:
                LocalDateTime startDateTime = DateTimeParser.parseOutputDateTime(taskData[3].trim());
                LocalDateTime endDateTime = DateTimeParser.parseOutputDateTime(taskData[4].trim());
                task = new Event(description, isDone, startDateTime, endDateTime);
                break;

            case FIXED_DURATION:
                 String[] durationString = taskData[3].split(Constants.EMPTY_STRING);
                 double duration = Double.parseDouble(durationString[0].trim());
                task = new FixedDuration(description, isDone, duration);
                break;

            default:
                throw new TaskListDecoderException("Invalid task type: " + taskType);

        }

        assert task != null : "Task should not be null";

        task.setDone(isDone);
        return task;

    }

}
