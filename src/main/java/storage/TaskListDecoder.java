package storage;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDateTime;

import common.Messages;
import exception.CommandException;
import exception.FileContentException;
import exception.TaskListDecoderException;
import parser.DateTimeParser;
import task.Deadline;
import task.Event;
import task.FixedDuration;
import task.Task;
import task.TaskList;
import task.TaskType;
import task.Todo;

/**
 * The {@code TaskListDecoder} is responsible for decoding a list of encoded tasks into a {@code TaskList} object.
 * This class interprets the specific format of each task and decodes the task in different formats before creating the corresponding {@code Task} objects.
 */
public class TaskListDecoder {

    private static final String EXPECTED_FORMAT_TODO = "T | 0 | <task description>";
    private static final String EXPECTED_FORMAT_DEADLINE = "D | 0 | <task description> | <task deadline>";
    private static final String EXPECTED_FORMAT_EVENT = "E | 0 | <task description> | <event start date/time> | <event end date/time>";
    private static final String EXPECTED_FORMAT_FD = "FD | 0 | <task description> | <required duration in hours>";
    
    /**
     * Decodes a list of encoded tasks into a {@code TaskList} object, which will contain the decoded tasks.
     * 
     * @param encodedTaskList represents a list of strings that contain the encoded tasks
     * @return a {@code TaskList} object that contains the decoded tasks
     * @throws FileContentException if an error occurs while decoding the task on a specific line, i.e. the file content is invalid
     * @throws TaskListDecoderException if an error occurs while decoding the task on a specific line
     * @throws CommandException if an error occurs while processing the command
     */
    // Solution below referenced from https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/AddressBookDecoder.java#L34
    public static TaskList decodeTaskList(List<String> encodedTaskList) throws FileContentException, TaskListDecoderException, CommandException {
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
            // throw new FileContentException("Empty line found. Please ensure that all lines in tasks.txt contain valid data.");
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
            // "Task data has missing components. Please check tasks.txt. Expected format: [T|D|E] | [0|1] | description [| additional info]. Found: " + taskData
            throw new FileContentException(
                String.format("%s. %s.", Messages.MESSAGE_TASK_MISSING_COMPONENTS, Messages.MESSAGE_INVALID_TASKS_DATA),
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
        // System.out.println("Valid task types are " + VALID_TASK_TYPE);
        // final String VALID_TASK_TYPE = "`T`, `D`, `E`, `FD`";

        TaskType taskType;
        
        try {
            taskType = TaskType.getTaskType(taskTypeString);
        } catch (IllegalArgumentException e) {
            // throw new FileContentException("Unknown task type: " + taskData[0].trim());
            throw new FileContentException(
                ERROR_GET_TASKTYPE,
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

        switch (isDoneString) {
            case "1":
                isDone = true;
                break;
            case "0":
                isDone = false;
                break;
            default:
                throw new FileContentException(
                    MESSAGE_INVALID_COMPLETION_STATUS,
                    String.format("Received `%s`", isDoneString),
                    String.format("Expected %s", VALID_COMPLETION_STATUS)
                );
        }

        return isDone;
    }

    private static void validateTaskDataLength(int taskDataLength, int expectedTaskDataLength, String[] taskData, String expectedFormat) throws TaskListDecoderException {
        if (taskDataLength < expectedTaskDataLength) {
            throw new TaskListDecoderException(
                Messages.ERROR_INVALID_TASK_FORMAT,
                String.format("Received `%s`", Arrays.toString(taskData)),
                String.format("Expected format: `%s`", expectedFormat));
        }
    }

    /**
     * Decodes a single line of {@code encodedTask} String (from tasks file) into a {@code Task} object.
     * 
     * @param encodedTask represents a String containing the encoded task
     * @return a {@code Task} object that corresponds to the {@code encodedTask} String
     * @throws FileContentException if the encoded task format is invalid
     * @throws TaskListDecoderException if an error occurs while decoding the task list, i.e. format of the encoded task (String) is invalid
     * @throws CommandException if an error related to command processing occurs, i.e. there is an issue while parsing date/time
     */
    // Example encodedTask: T | 1 | read book
    private static Task decodeTaskFromString(String encodedTask) throws FileContentException, TaskListDecoderException, CommandException {
        
        validateEncodedTask(encodedTask);
        String[] taskData = splitEncodedTask(encodedTask);
        
        Task task = null;
        TaskType taskType = getTaskType(taskData[0]);

        boolean isDone = getIsDone(taskData[1]);

        String description = taskData[2];
        int taskDataLength = taskData.length;

        switch (taskType) {
            case TODO:
                task = new Todo(description, isDone);
                break;
            case DEADLINE:
                
                validateTaskDataLength(taskDataLength, 4, taskData, EXPECTED_FORMAT_DEADLINE);

                String dueString = taskData[3].trim();
                
                // The tasks file contains a String representation of the time. To create a deadline task, we need to parse the String into a LocalDateTime object.
                LocalDateTime due = DateTimeParser.parseOutputDateTime(dueString);
                task = new Deadline(description, isDone, due);
                break;
            case EVENT:
            
                validateTaskDataLength(taskDataLength, 5, taskData, EXPECTED_FORMAT_EVENT);

                LocalDateTime startDateTime = DateTimeParser.parseOutputDateTime(taskData[3].trim());
                LocalDateTime endDateTime = DateTimeParser.parseOutputDateTime(taskData[4].trim());
                
                task = new Event(description, isDone, startDateTime, endDateTime);
                break;

            case FIXED_DURATION:

                validateTaskDataLength(taskDataLength, 4, taskData, EXPECTED_FORMAT_FD);
                String[] durationString = taskData[3].split(" ");
                double duration = Double.parseDouble(durationString[0]);
                task = new FixedDuration(description, isDone, duration);
                break;

            default:
                throw new TaskListDecoderException("Invalid task type: " + taskType);
        }

        task.setDone(isDone);
        return task;
    }

}
