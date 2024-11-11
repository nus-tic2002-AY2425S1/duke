package storage;

import common.Constants;
import common.Messages;
import exception.CommandException;
import exception.FileContentException;
import exception.TaskListDecoderException;
import parser.DateTimeParser;
import task.TaskList;
import task.Task;
import task.Todo;
import task.TaskType;
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
    public static TaskList decodeTaskList(List<String> encodedTaskList)
        throws FileContentException, TaskListDecoderException, CommandException {
        TaskList decodedTasks = new TaskList();
        for (String encodedTask : encodedTaskList) {
            // System.out.println("encodedTask: " + encodedTask);
            Task task = decodeTaskFromString(encodedTask);
            // System.out.println("Decoded task: " + task);
            decodedTasks.addTask(task);
        }
        return decodedTasks;
    }

    private static void validateEncodedTask(String encodedTask) throws FileContentException {
        if (encodedTask.isEmpty()) {
            throw new FileContentException(
                String.format("%s. %s.", Messages.MESSAGE_EMPTY_LINE, Messages.MESSAGE_INVALID_TASKS_DATA),
                String.format("Received `%s`", encodedTask),
                String.format("Expected format: `%s` or `%s` or `%s` or `%s`", EXPECTED_FORMAT_TODO,
                    EXPECTED_FORMAT_DEADLINE, EXPECTED_FORMAT_EVENT, EXPECTED_FORMAT_FD)
            );
        }
    }

    private static String[] splitEncodedTask(String encodedTask) throws FileContentException {
        String[] taskData = encodedTask.split(" \\| ");
        // System.out.println("taskData is " + Arrays.toString(taskData));

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

    private static TaskType getTaskType(String taskTypeString) throws FileContentException {
        final String ERROR_GET_TASKTYPE = "Error: Unknown task type.";
        final String VALID_TASK_TYPE = TaskType.getValidTaskType();
        TaskType taskType;

        try {
            taskType = TaskType.getTaskType(taskTypeString);
        } catch (IllegalArgumentException e) {
            // throw new FileContentException("Unknown task type: " + taskData[0].trim());
            throw new FileContentException(ERROR_GET_TASKTYPE,
                String.format("Received `%s`", taskTypeString.trim()),
                String.format("Expected `%s`", VALID_TASK_TYPE)
            );
        }

        return taskType;
    }

    private static boolean getIsDone(String isDoneString) throws FileContentException {
        boolean isDone;

        final String MESSAGE_INVALID_COMPLETION_STATUS = "Task has invalid completion status";
        final String VALID_COMPLETION_STATUS = "`1` or `0`";

        isDone = switch (isDoneString) {
            case "1" -> true;
            case "0" -> false;
            default -> throw new FileContentException(MESSAGE_INVALID_COMPLETION_STATUS,
                String.format("Received `%s`", isDoneString),
                String.format("Expected %s", VALID_COMPLETION_STATUS)
            );
        };

        return isDone;
    }

    private static void validateTaskDataLength(int taskDataLength, TaskType taskType,
                                               String[] taskData) throws TaskListDecoderException {

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
            throw new TaskListDecoderException(Messages.ERROR_INVALID_TASK_FORMAT,
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

        task.setDone(isDone);
        return task;

    }

}
