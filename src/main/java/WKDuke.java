import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WKDuke {
    private static final String BORDER_LINE = "\t____________________________________________________________";
    private static final String INDENT = "\t ";
    private static final String NEW_INDENT_LINE = System.lineSeparator() + "\t ";

    private static final String EXIT_KEYWORD = "bye";
    private static final String LIST_TASK_KEYWORD = "list";
    private static final String MARK_TASK_DONE_KEYWORD = "mark";
    private static final String MARK_TASK_UNDONE_KEYWORD = "unmark";
    private static final String ADD_TODO_TASK_KEYWORD = "todo";
    private static final String ADD_DEADLINE_TASK_KEYWORD = "deadline";
    private static final String ADD_EVENT_TASK_KEYWORD = "event";
    private static final String DELETE_TASK_KEYWORD = "delete";

    private static final List<Task> taskList = new ArrayList<>();

    public static void echo(String message) {
        System.out.println(BORDER_LINE);
        System.out.println(INDENT + message);
        System.out.println(BORDER_LINE + System.lineSeparator());
    }

    //Solution below inspired by https://stackoverflow.com/questions/69576641
    public static void printTaskList() {
        if (taskList.isEmpty()) {
            echo("Your task list is currently empty.");
            return;
        }

        StringBuilder messageBuilder = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            messageBuilder.append(NEW_INDENT_LINE).append(i + 1).append(".").append(taskList.get(i));
        }
        echo(messageBuilder.toString());
    }

    public static boolean invalidTaskNumber(String string) {
        try {
            int taskNumber = Integer.parseInt(string);
            return taskNumber <= 0;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private static void validateTaskInput(String[] inputWords) throws InvalidTaskOperationException {
        String taskOperation = inputWords[0];
        // Check if input contain a task number
        if (inputWords.length < 2) {
            throw new InvalidTaskOperationException("Action required a task number.", taskOperation);
        }
        // Check if task number is not a valid integer
        String taskNumber = inputWords[1];
        if (invalidTaskNumber(taskNumber)) {
            throw new InvalidTaskOperationException(String.format("Task number '%s' is invalid.", taskNumber), taskOperation);
        }
        // Check if task number exist in taskList
        if (Integer.parseInt(taskNumber) > taskList.size()) {
            throw new InvalidTaskOperationException(String.format("Task number '%s' not found.", taskNumber), taskOperation);
        }
    }

    public static void markTaskAsDone(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        if (task.isDone()) {
            echo(String.format("This task is already marked as done:%s  %s", NEW_INDENT_LINE, task));
            return;
        }
        task.markAsDone();
        echo(String.format("Nice! I've marked this task as done:%s  %s", NEW_INDENT_LINE, task));
    }

    public static void markTaskAsUndone(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        if (!task.isDone()) {
            echo(String.format("This task is still not completed:%s  %s", NEW_INDENT_LINE, task));
            return;
        }
        task.markAsUndone();
        echo(String.format("OK, I've marked this task as not done yet:%s  %s", NEW_INDENT_LINE, task));
    }

    public static void updateTask(String[] inputWords) {
        String taskAction = inputWords[0];
        try {
            validateTaskInput(inputWords);

            int taskNumber = Integer.parseInt(inputWords[1]);
            switch (taskAction) {
                case MARK_TASK_DONE_KEYWORD -> markTaskAsDone(taskNumber);
                case MARK_TASK_UNDONE_KEYWORD -> markTaskAsUndone(taskNumber);
                default -> throw new InvalidTaskOperationException("Unknown task operation.", taskAction);
            }
        } catch (InvalidTaskOperationException e) {
            echo(String.format("Action: updateTask(%s)%sError: %s", e.getTaskOperation(), NEW_INDENT_LINE, e.getMessage()));
        }
    }

    private static void parseDeadlineTask(String taskDetail) throws InvalidTaskFormatException {
        String[] taskDetailParts = taskDetail.split("/by");
        if (taskDetailParts.length != 2) {
            throw new InvalidTaskFormatException("Deadline task requires '/by' information.", TaskType.DEADLINE);
        }
    }

    private static void parseEventTask(String taskDetail) throws InvalidTaskFormatException {
        String[] parts = taskDetail.split("/from|/to");
        if (parts.length != 3) {
            throw new InvalidTaskFormatException("Event task requires '/from' and '/to' information.", TaskType.EVENT);
        }
    }

    private static void validateAddTaskInput(String[] inputWords) throws InvalidTaskFormatException {
        TaskType taskType = TaskType.valueOf(inputWords[0].toUpperCase());
        if (inputWords.length == 1 || inputWords[1].trim().isEmpty()) {
            throw new InvalidTaskFormatException("Task description is missing.", taskType);
        }
        String taskDetail = inputWords[1].trim();
        switch (taskType) {
            case DEADLINE:
                parseDeadlineTask(taskDetail);
                break;
            case EVENT:
                parseEventTask(taskDetail);
                break;
        }
    }

    private static Task createDeadlineTask(String taskDetail) {
        String[] taskDetailParts = taskDetail.split("/by");
        return new Deadline(taskDetailParts[0].trim(), taskDetailParts[1].trim());
    }

    private static Task createEventTask(String taskDetail) {
        String[] parts = taskDetail.split("/from|/to");
        return new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }

    public static void addTask(String[] inputWords) {
        TaskType taskType = TaskType.valueOf(inputWords[0].toUpperCase());
        try {
            validateAddTaskInput(inputWords);

            String taskDetail = inputWords[1];
            Task newTask = switch (taskType) {
                case TODO -> new ToDo(taskDetail);
                case DEADLINE -> createDeadlineTask(taskDetail);
                case EVENT -> createEventTask(taskDetail);
                default -> throw new InvalidTaskFormatException("Unknown task type.", taskType);
            };
            taskList.add(newTask);
            echo(String.format("Got it. I've added this task:%s  %s%sNow you have %s tasks in the list.", NEW_INDENT_LINE, newTask, NEW_INDENT_LINE, taskList.size()));
        } catch (InvalidTaskFormatException e) {
            echo(String.format("Action: addTask(%s)%sError: %s", e.getTaskType(), NEW_INDENT_LINE, e.getMessage()));
        }
    }

    public static void deleteTask(String[] inputWords) {
        try {
            validateTaskInput(inputWords);

            int taskNumber = Integer.parseInt(inputWords[1]);
            Task task = taskList.get(taskNumber - 1);
            taskList.remove(task);
            echo(String.format("Noted. I've removed this task:%s  %s%sNow you have %s tasks in the list.", NEW_INDENT_LINE, task, NEW_INDENT_LINE, taskList.size()));
        } catch (InvalidTaskOperationException e) {
            echo(String.format("Action: deleteTask%sError: %s", NEW_INDENT_LINE, e.getMessage()));
        }
    }

    public static void main(String[] args) {
        String logo = """
                 ___       __   ___  __    ________  ___  ___  ___  __    _______
                \t |\\  \\     |\\  \\|\\  \\|\\  \\ |\\   ___ \\|\\  \\|\\  \\|\\  \\|\\  \\ |\\  ___ \\    \s
                \t \\ \\  \\    \\ \\  \\ \\  \\/  /|\\ \\  \\_|\\ \\ \\  \\\\\\  \\ \\  \\/  /|\\ \\   __/|   \s
                \t  \\ \\  \\  __\\ \\  \\ \\   ___  \\ \\  \\ \\\\ \\ \\  \\\\\\  \\ \\   ___  \\ \\  \\_|/__ \s
                \t   \\ \\  \\|\\__\\_\\  \\ \\  \\\\ \\  \\ \\  \\_\\\\ \\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\_|\\ \\\s
                \t    \\ \\____________\\ \\__\\\\ \\__\\ \\_______\\ \\_______\\ \\__\\\\ \\__\\ \\_______\\
                \t     \\|____________|\\|__| \\|__|\\|_______|\\|_______|\\|__| \\|__|\\|_______|
                """;
        echo(String.format("%s%sHello! I'm WKDuke%sWhat can I do for you?", logo, NEW_INDENT_LINE, NEW_INDENT_LINE));

        Scanner sc = new Scanner(System.in);
        String action = "";

        while (!action.equals(EXIT_KEYWORD)) {
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }

            String[] inputWords = input.split(" ", 2);
            action = inputWords[0];
            switch (action) {
                case EXIT_KEYWORD:
                    break;
                case LIST_TASK_KEYWORD:
                    printTaskList();
                    break;
                case MARK_TASK_DONE_KEYWORD:
                case MARK_TASK_UNDONE_KEYWORD:
                    updateTask(inputWords);
                    break;
                case ADD_TODO_TASK_KEYWORD:
                case ADD_DEADLINE_TASK_KEYWORD:
                case ADD_EVENT_TASK_KEYWORD:
                    addTask(inputWords);
                    break;
                case DELETE_TASK_KEYWORD:
                    deleteTask(inputWords);
                    break;
                default:
                    echo(String.format("Action: userInput%sError: Unknown command for '%s'.", NEW_INDENT_LINE, input));
                    break;
            }
        }

        echo("Bye. Hope to see you again soon!");
    }
}
