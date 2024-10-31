import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class TaskListDecoder {

    // https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/AddressBookDecoder.java#L34
    // Decodes {@code encodedTaskList} into an {@code TaskList} containing the decoded tasks.
    public static TaskList decodeTaskList(List<String> encodedTaskList) throws FileContentException, TaskListDecoderException {
        TaskList decodedTasks = new TaskList();
        for (String encodedTask : encodedTaskList) {
            // System.out.println("In loop of encoded tasks");
            Task task = decodeTaskFromString(encodedTask);
            // System.out.println("Decoded task: " + task);
            decodedTasks.addTask(task);
        }
        return decodedTasks;
    }

    // Decodes {@code encodedTask} from tasks.txt into a {@code Task}
    // Example encodedTask: T | 1 | read book
    private static Task decodeTaskFromString(String encodedTask) throws FileContentException, TaskListDecoderException {
        
        if (encodedTask.isEmpty()) {
            throw new FileContentException("Empty line found. Please ensure that all lines in tasks.txt contain valid data.");
        }
        
        String[] taskData = encodedTask.split(" \\| ");
        // System.out.println("taskData is " + Arrays.toString(taskData));

        if (taskData.length < 3) {
            throw new FileContentException("Task data has missing components. Please check tasks.txt. Expected format: [T|D|E] | [0|1] | description [| additional info]. Found: " + taskData);
        }

        Task task = null;
        TaskType taskType;
        
        // System.out.println("taskType: " + taskData[0]);
        // System.out.println("type of taskData " + taskData[0].getClass().getName());
        
        // TaskType taskType = task.getTaskType();
        try {
            taskType = TaskType.getTaskType(taskData[0]);
            // System.out.println("In try: " + taskData[0].trim());
            // System.out.println("asd type " +  TaskType.valueOf(taskData[0].trim().toUpperCase()).getClass().getName());
            // System.out.println("testa " + TaskType.valueOf(taskData[0].trim().toUpperCase()));
            // taskType = TaskType.valueOf(taskData[0].trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new FileContentException("Unknown task type: " + taskData[0].trim());
        }

        // System.out.println("this is taskType: " + taskType + " of type " + taskType.getClass().getName());
        
        boolean isDone;
        try {
            isDone = Boolean.valueOf(taskData[1]);
        } catch (NumberFormatException e) {
            throw new FileContentException("Invalid completion status found. Expected 0 or 1, but found: " + taskData[1]);
        }

        String description = taskData[2];

        switch (taskType) {
            case TODO:
                task = new Todo(description, isDone);
                break;
            case DEADLINE:      // case "D":
                if (taskData.length < 4) {
                    throw new TaskListDecoderException("Invalid Deadline format");
                }
                String due = taskData[3];
                task = new Deadline(description, isDone, due);
                break;
            case EVENT:     // case "E":
                if (taskData.length < 5) {
                    throw new TaskListDecoderException("Invalid Event format");
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
