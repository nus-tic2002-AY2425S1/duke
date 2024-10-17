import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import checkbot.Task.*;
import checkbot.Ui.*;
import checkbot.Utils.*;

public class Main {
    public static File taskFile = new File("data/checkbot.txt");

    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(taskFile);
        fw.write(textToAdd);
        fw.close();
    }

    public static void updateFile() {
        String textToAdd = "";

        for (Task task : TaskList.tasks) {
            textToAdd = textToAdd.concat(task.getFileView() + System.lineSeparator());
        }

        try {
            writeToFile(textToAdd);
        } catch (IOException e) {
            System.out.println("Oops! Something wrong happened with saving of file.");
        }
    }

    public static void readFile(File taskFile) throws FileNotFoundException {
        Scanner scanFile = new Scanner(taskFile);
        List<String> taskList = new ArrayList<>();
        int taskCount = 0;

        while (scanFile.hasNextLine()) {
            taskList.add(scanFile.nextLine());
        }

        for (String task : taskList) {
            String[] taskArray = task.split("\\|");
            String taskCommand = switch (taskArray[0].trim()) {
                case "T" -> "Todo " + taskArray[2].trim();
                case "D" -> "Deadline " + taskArray[2].trim();
                case "E" -> "Event " + taskArray[2].trim();
                default -> {
                    // invalid task type, end the program
                    System.out.println("Invalid task type! Please check your txt file.");
                    throw new RuntimeException();
                }
            };

            try {
                if (addTask(taskCommand)) {
                    // if task added successfully, set task status
                    if (taskArray[1].trim().equals("NOT_DONE")) {
                        TaskList.tasks.get(taskCount).setDone(false);
                    } else if (taskArray[1].trim().equals("DONE")) {
                        TaskList.tasks.get(taskCount).setDone(true);
                    } else {
                        System.out.println("Invalid task status! Please check your txt file.");
                        throw new RuntimeException();
                    }
                    // increase taskCount for next line in taskList
                    taskCount++;
                } else {
                    // command input is wrong, end the program
                    System.out.println("Invalid task! Please check your txt file.");
                    throw new RuntimeException();
                }
            } catch (EmptyTimeException e) {
                // empty time, end the program
                TextUi.printEmptyTime();
                System.out.println("Invalid input! Please check your txt file.");
                throw new RuntimeException();
            } catch (EmptyInputException e) {
                // empty task, end the program
                TextUi.printEmptyDescription();
                System.out.println("Invalid input! Please check your txt file.");
                throw new RuntimeException();
            }
        }
    }

    public static boolean addTask(String input) throws EmptyInputException, EmptyTimeException {
        String[] taskArray = input.split(" ",2);
        if (taskArray.length < 2) {
            throw new EmptyInputException();
        }
        String taskType = taskArray[0].toLowerCase();
        String taskDetails = taskArray[1];

        switch (taskType) {
            case "todo":
                addTodo(taskDetails);
                return true;
            case "deadline":
                try{
                    addDeadline(taskDetails);
                    return true;
                } catch (CommandNotFoundException e) {
                    System.out.println(StringHelper.deadlineError);
                    break;
                }
            case "event":
                try{
                    addEvent(taskDetails);
                    return true;
                } catch (CommandNotFoundException e) {
                    System.out.println(StringHelper.eventError);
                    break;
                }
            default:
                // do nothing
        }
        return false;
    }

    public static void addTodo(String input){
        Todo task = new Todo(input);
        TaskList.tasks.add(task);
        TextUi.echoTask(task);
    }

    public static void addDeadline(String input) throws EmptyInputException, EmptyTimeException, CommandNotFoundException {
        // input format: <task> /by <datetime>
        if (!input.contains("/by")){
            throw new CommandNotFoundException();
        }
        String[] deadlineArray = input.split("/by",2);
        String description = deadlineArray[0].trim();
        String dueDateTime = deadlineArray[1].trim();
        if (description.isEmpty()) {
            throw new EmptyInputException();
        }
        if (dueDateTime.isEmpty()) {
            throw new EmptyTimeException();
        }

        Deadline task = new Deadline(description, dueDateTime);
        TaskList.tasks.add(task);
        TextUi.echoTask(task);
    }

    public static void addEvent(String input) throws EmptyInputException, EmptyTimeException, CommandNotFoundException {
        // input format: <event> /from <datetime> /to <datetime>
        if (!input.contains("/from") || !input.contains("/to")){
            throw new CommandNotFoundException();
        }
        String[] eventArray = input.split("/from",2);
        String[] dateTimeArray = eventArray[1].split("/to",2);
        String description = eventArray[0].trim();
        if (description.isEmpty()) {
            throw new EmptyInputException();
        }
        String startDateTime = dateTimeArray[0].trim();
        String endDateTime = dateTimeArray[1].trim();
        if (startDateTime.isEmpty() || endDateTime.isEmpty()) {
            throw new EmptyTimeException();
        }

        Event task = new Event(description, startDateTime, endDateTime);
        TaskList.tasks.add(task);
        TextUi.echoTask(task);
    }

    public static void markTask(Task task) {
        task.setDone(true);
        System.out.println(StringHelper.outputLine + System.lineSeparator() +
                "Nice! I've marked this task as done: " + System.lineSeparator() +
                "  " + task.getListView() + System.lineSeparator() +
                StringHelper.outputLine);
    }

    public static void unmarkTask(Task task) {
        task.setDone(false);
        System.out.println(StringHelper.outputLine + System.lineSeparator() +
                "Okay, I've marked this task as not done yet: " + System.lineSeparator() +
                "  " + task.getListView() + System.lineSeparator() +
                StringHelper.outputLine);
    }

    public static void deleteTask(Task task) {
        TaskList.tasks.remove(task);
        System.out.println(StringHelper.outputLine + System.lineSeparator() +
                "Got it. I've removed this task: " + System.lineSeparator() +
                "  " + task.getListView() + System.lineSeparator() +
                "Now you have " + TaskList.tasks.size() + " task(s) in the list." + System.lineSeparator() +
                StringHelper.outputLine);

    }

    public static void setStatus(String  input) throws EmptyInputException {
        String[] setStatusArray = input.split(" ", 2);
        if (setStatusArray.length < 2) {
            throw new EmptyInputException();
        }
        String action = setStatusArray[0].toLowerCase();
        int taskIdx = Integer.parseInt(setStatusArray[1]) - 1;

        switch (action) {
            case "mark":
                markTask(TaskList.tasks.get(taskIdx));
                break;
            case "unmark":
                unmarkTask(TaskList.tasks.get(taskIdx));
                break;
            case "delete":
                deleteTask(TaskList.tasks.get(taskIdx));
                break;
            default:
                // do nothing
        }
    }

    public static void main(String[] args) {
        TextUi.printHello();
        boolean goToExit = false;

        // read file from existing file and add into tasks
        try {
            readFile(taskFile);
        } catch (FileNotFoundException e) {
            try {
                taskFile.getParentFile().mkdir();
                taskFile.createNewFile();
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }

        do {
            String input = TextUi.readInput().trim();
            String keyword = input.split(" ")[0].toLowerCase();

            switch (keyword) {
                case "bye":
                    TextUi.printExit();
                    goToExit = true;
                    break;
                case "list":
                    TextUi.printTasks();
                    break;
                case "mark":
                    // fallthrough
                case "unmark":
                    // fallthrough
                case "delete":
                    try {
                        setStatus(input);
                        updateFile();
                        break;
                    } catch (EmptyInputException e) {
                        System.out.println(StringHelper.emptyNumber);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(StringHelper.nonIntegerNumber);
                        break;
                    } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                        System.out.println(StringHelper.invalidTaskNumber);
                        break;
                    }
                case "todo":
                    // fallthrough
                case "deadline":
                    // fallthrough
                case "event":
                    try {
                        addTask(input);
                        updateFile();
                        break;
                    } catch (EmptyInputException e) {
                        TextUi.printEmptyDescription();
                        break;
                    } catch (EmptyTimeException e) {
                        TextUi.printEmptyTime();
                        break;
                    }
                default:
                    TextUi.printCommandNotFound();
            }
        } while (!goToExit);
    }
}
