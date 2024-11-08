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
import task.Task;
import task.TaskList;
import task.TaskType;
import task.Todo;

/**
 * The TaskListDecoder is responsible for decoding a list of encoded tasks into a {@code TaskList} object.
 * This class interprets the specific format of each task and decodes the task in different formats before creating the corresponding Task objects.
 */
public class TaskListDecoder {

    /**
     * Decodes a list of encoded tasks into a {@code TaskList} object, which will contain the decoded tasks.
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

    /**
     * Decodes a single line of {@code encodedTask} String (from tasks file) into a {@code Task} object
     * @param encodedTask represents a String containing the encoded task
     * @return a {@code Task} object that corresponds to the {@code encodedTask} String
     * @throws FileContentException if the encoded task format is invalid
     * @throws TaskListDecoderException if an error occurs while decoding the task list, i.e. format of the encoded task (String) is invalid
     * @throws CommandException if an error related to command processing occurs, i.e. there is an issue while parsing date/time
     */
    // Example encodedTask: T | 1 | read book
    private static Task decodeTaskFromString(String encodedTask) throws FileContentException, TaskListDecoderException, CommandException {
        
        final String EXPECTED_FORMAT_TODO = "T | 0 | <task description>";
        final String EXPECTED_FORMAT_DEADLINE = "D | 0 | <task description> | <task deadline>";
        final String EXPECTED_FORMAT_EVENT = "E | 0 | <task description> | <event start date/time> | <event end date/time>";
        
        if (encodedTask.isEmpty()) {
            // throw new FileContentException("Empty line found. Please ensure that all lines in tasks.txt contain valid data.");
            throw new FileContentException(
                String.format("%s. %s.", Messages.MESSAGE_EMPTY_LINE, Messages.MESSAGE_INVALID_TASKS_DATA),
                String.format("Received `%s`", encodedTask),
                String.format("Expected format: `%s` or `%s` or `%s`", EXPECTED_FORMAT_TODO, 
                              EXPECTED_FORMAT_DEADLINE, EXPECTED_FORMAT_EVENT)
            );
        }
        
        String[] taskData = encodedTask.split(" \\| ");
        // System.out.    ("taskData is " + Arrays.toString(taskData));

        if (taskData.length < 3) {
            // "Task data has missing components. Please check tasks.txt. Expected format: [T|D|E] | [0|1] | description [| additional info]. Found: " + taskData
            throw new FileContentException(
                String.format("%s %s", Messages.MESSAGE_TASK_MISSING_COMPONENTS, Messages.MESSAGE_INVALID_TASKS_DATA),
                String.format("Received `%s`", Arrays.toString(taskData)),
                String.format("Expected format: `%s` or `%s` or `%s`", EXPECTED_FORMAT_TODO, 
                              EXPECTED_FORMAT_DEADLINE, EXPECTED_FORMAT_EVENT)
            );
        }

        Task task = null;
        TaskType taskType;
        
        final String ERROR_GET_TASKTYPE = "Error: Unknown task type.";
        final String VALID_TASK_TYPE = "`T`, `D`, or `E`";

        try {
            taskType = TaskType.getTaskType(taskData[0]);
        } catch (IllegalArgumentException e) {
            // throw new FileContentException("Unknown task type: " + taskData[0].trim());
            throw new FileContentException(
                ERROR_GET_TASKTYPE,
                String.format("Received `%s`", taskData[0].trim()),
                String.format("Expected `%s`", VALID_TASK_TYPE)
            );
        }

        boolean isDone;

        final String MESSAGE_INVALID_COMPLETION_STATUS = "Task has invalid completion status";
        final String VALID_COMPLETION_STATUS = "`1` or `0`";

        try {
            if (taskData[1].equals("1")) {
                isDone = true;
            } else {
                isDone = false;
            }
        } catch (NumberFormatException e) {
            // "Invalid completion status found. Expected 0 or 1, but found: " + taskData[1]
            throw new FileContentException(
                MESSAGE_INVALID_COMPLETION_STATUS,
                String.format("Received `%s`", taskData[1]),
                String.format("Expected %s", VALID_COMPLETION_STATUS)
            );
        }

        String description = taskData[2];
        int taskDataLength = taskData.length;

        switch (taskType) {
            case TODO:
                task = new Todo(description, isDone);
                break;
            case DEADLINE:
                if (taskDataLength < 4) {
                    throw new TaskListDecoderException(
                        Messages.ERROR_INVALID_TASK_FORMAT,
                        String.format("Received `%s`", Arrays.toString(taskData)),
                        String.format("Expected format: `%s`", EXPECTED_FORMAT_DEADLINE)
                    );
                }
                String dueString = taskData[3].trim();
                // System.out.println("dueString: " + dueString);

                // The tasks file contains a String representation of the time. To create a deadline task, we need to parse the String into a LocalDateTime object.
                LocalDateTime due = DateTimeParser.parseOutputDateTime(dueString);
                // System.out.println("due is " + due);
                // LocalDateTime due = DateTimeParser.parseDateTime(dueString);
                // try {
                //     // due = DateTimeParser.decodeDateTime(dueString);
                //     due = DateTimeParser.parseDateTime(dueString);
                // } catch (IllegalArgumentException e) {
                //     System.out.println(e.getMessage());
                // }
                // LocalDateTime due = LocalDateTime.parse(taskData[3].trim());
                task = new Deadline(description, isDone, due);
                break;
            case EVENT:
                if (taskDataLength < 5) {
                    // throw new TaskListDecoderException("Invalid Event format");
                    throw new TaskListDecoderException(
                        Messages.ERROR_INVALID_TASK_FORMAT,
                        String.format("Received `%s`", Arrays.toString(taskData)),
                        String.format("Expected format: `%s`", EXPECTED_FORMAT_EVENT)
                    );
                }

                // String start = taskData[3];
                
                // Jul 06 2024 1800
                // System.out.println("taskData 3 " + taskData[3] + " of type " + taskData[3].getClass().getName());
                // LocalDateTime startDateTime = LocalDateTime.parse(taskData[3].trim());
                LocalDateTime startDateTime = DateTimeParser.parseOutputDateTime(taskData[3].trim());
                
                // String end = taskData[4];
                // System.out.println("taskData 4 " + taskData[4]);
                // LocalDateTime endDateTime = LocalDateTime.parse(taskData[4].trim());
                
                LocalDateTime endDateTime = DateTimeParser.parseOutputDateTime(taskData[4].trim());
                
                task = new Event(description, isDone, startDateTime, endDateTime);
                break;
            default:
                throw new TaskListDecoderException("Invalid task type: " + taskType);
        }

        // System.out.println("task is " + task);
        task.setDone(isDone);
        return task;
    }

}
