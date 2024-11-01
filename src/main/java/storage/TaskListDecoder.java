package storage;
import java.util.Arrays;
import java.util.List;

import common.Messages;
import exception.FileContentException;
import exception.TaskListDecoderException;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.TaskType;
import task.Todo;

public class TaskListDecoder {

    // https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/AddressBookDecoder.java#L34
    // Decodes {@code encodedTaskList} into an {@code TaskList} containing the decoded tasks.
    public static TaskList decodeTaskList(List<String> encodedTaskList) throws FileContentException, TaskListDecoderException {
        TaskList decodedTasks = new TaskList();
        for (String encodedTask : encodedTaskList) {
            Task task = decodeTaskFromString(encodedTask);
            // System.out.println("Decoded task: " + task);
            decodedTasks.addTask(task);
        }
        return decodedTasks;
    }

    // Decodes {@code encodedTask} from tasks.txt into a {@code Task}
    // Example encodedTask: T | 1 | read book
    private static Task decodeTaskFromString(String encodedTask) throws FileContentException, TaskListDecoderException {
        
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
        // System.out.println("taskData is " + Arrays.toString(taskData));

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
            case DEADLINE:      // case "D":
                if (taskDataLength < 4) {
                    throw new TaskListDecoderException(
                        Messages.ERROR_INVALID_TASK_FORMAT,
                        String.format("Received `%s`", Arrays.toString(taskData)),
                        String.format("Expected format: `%s`", EXPECTED_FORMAT_DEADLINE)
                    );
                }
                String due = taskData[3];
                task = new Deadline(description, isDone, due);
                break;
            case EVENT:     // case "E":
                if (taskDataLength < 5) {
                    // throw new TaskListDecoderException("Invalid Event format");
                    throw new TaskListDecoderException(
                        Messages.ERROR_INVALID_TASK_FORMAT,
                        String.format("Received `%s`", Arrays.toString(taskData)),
                        String.format("Expected format: `%s`", EXPECTED_FORMAT_EVENT)
                    );
                }
                String start = taskData[3];
                String end = taskData[4];
                task = new Event(description, isDone, start, end);
                break;
            default:
                throw new TaskListDecoderException("Invalid task type: " + taskType);
        }

        task.setDone(isDone);
        return task;
    }

}
