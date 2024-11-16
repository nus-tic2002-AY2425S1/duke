package pistamint.parser;

import pistamint.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import pistamint.general.*;
import pistamint.storage.Storage;
import pistamint.taskList.TaskList;
import pistamint.ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public static TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * This method is used to process tasks in the duke.txt
     * by taking in the taskData argument, splits up the data into three parts
     * to identify which task class it belongs to, assign and adds into the task list.
     * If the task is marked with 1 in the file, then it will mark the task as done in the task list.
     *
     * @param taskData are each line stored in duke.txt storage file
     */
    public static void parseTask(String taskData) {
        if (!taskData.trim().isEmpty()) {
            char symbol = taskData.charAt(0);
            String[] dataParts = taskData.split("\\|");
            assert dataParts.length == 3 : "Task data format is incorrect. Expected 3 parts but got " + dataParts.length;
            boolean isDone = dataParts[1].equals("1");
            String description = dataParts[2];
            Task task;
            if (symbol == 'T') {
                task = new Todo(description, symbol);
                taskList.addTask(task, true);
            } else if (symbol == 'D') {
                task = new Deadline(description, symbol);
                taskList.addTask(task, true);
            } else {
                task = new Event(description, symbol);
                taskList.addTask(task, true);
            }
            if (isDone) {
                task.markAsDone();
            }
        }
    }

    public static String checkDescription(String task, String input) {
        if (task.equalsIgnoreCase("deadline")) {
            return input.substring(input.indexOf(" "), input.indexOf("/by"));
        } else if (task.equalsIgnoreCase("event")) {
            return input.substring(input.indexOf(" "), input.indexOf("/from"));
        }
        return input;
    }

    /**
     * This method returns true if the commands have been successfully processed (added to task list and also the file storage)
     * return false if otherwise. It parses the user input timeline in "yyyy-MM-dd" for event /deadlines task
     * into Date Format "MMM dd yyyy"
     *
     * @param input the entire line of command keyed in by the user including activities
     * @param task  the key command that user wants to do
     * @return true/false
     * @throws DateTimeParseException          if the date time is in invalid format to be parsed.
     * @throws StringIndexOutOfBoundsException if the user did not complete the command input for event/deadline/todo.
     */
    public static boolean processTask(String input, String task) {
        String action = "";
        LocalDate timeline, to, from;
        try {
            if (task.equalsIgnoreCase("todo")) {
                action = input.substring(input.indexOf(" "));
                if (action.trim().isEmpty()) {
                    Ui.stringIndexOutOfBound(task);
                    return false;
                }
                Todo todo = new Todo(action.trim(), 'T');
                taskList.addTask(todo, false);
            } else {
                String description = "";
                try {
                    description = checkDescription(task, input);
                    if (description.trim().isEmpty()) {
                        Ui.stringIndexOutOfBound(task);
                        return false;
                    }
                    if (task.equalsIgnoreCase("deadline")) {
                        timeline = LocalDate.parse(input.substring(input.indexOf("/by") + 3).trim());
                        String dL = timeline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        Deadline deadline = new Deadline(description.trim(), 'D', dL);
                        taskList.addTask(deadline, false);
                    } else if (task.equalsIgnoreCase("event")) {
                        from = LocalDate.parse(input.substring(input.indexOf("/from") + 5, input.indexOf("/to")).trim());
                        to = LocalDate.parse(input.substring(input.indexOf("/to") + 3).trim());
                        String eFrom = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        String eTo = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        Event event = new Event(description.trim(), 'E', eFrom, eTo);
                        taskList.addTask(event, false);
                    }

                } catch (DateTimeParseException e) {
                    Ui.dateTimeException();
                    return false;
                }
            }
            return true;
        } catch (StringIndexOutOfBoundsException ex) {
            Ui.stringIndexOutOfBound(task);
        }
        return false;
    }

    /**
     * This method is used to process the update of event details without user having to delete the
     * entry and re-add a new one.
     *
     * @param event  specifies the specific event that needs to be updated
     * @param reader contains the user input to which entry that needs to be updated
     * @throws DateTimeParseException when user inputs an invalid date format
     * @throws IOException            when there is an issue when processing the file.
     */
    public static void processUpdateEvent(Event event, String reader) {
        Scanner input;
        LocalDate to, from;
        boolean success = true;
        try {
            while (reader != null) {
                System.out.print(Ui.start);
                if (reader.equalsIgnoreCase("1")) {
                    System.out.print("\n\tNew From:");
                    input = new Scanner(System.in);
                    from = LocalDate.parse(input.nextLine());
                    event.setFrom(from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
                    break;
                } else if (reader.equalsIgnoreCase("2")) {
                    System.out.print("\n\tNew To:");
                    input = new Scanner(System.in);
                    to = LocalDate.parse(input.nextLine());
                    event.setTo(to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
                    break;
                } else if (reader.equalsIgnoreCase("3")) {
                    System.out.print("\n\tNew Description:");
                    input = new Scanner(System.in);
                    success = updateDescription(input.nextLine() + " ", event);
                    break;
                } else {
                    System.out.print("\n\tPlease only select within selection: ");
                    input = new Scanner(System.in);
                    reader = input.nextLine();
                }
            }
            if (success) {
                Storage.refreshFile(taskList.getTasks());
                assert taskList.getSize() > 0 : "Task list is empty after task updated.";
                Ui.showTaskUpdated((Task) event);
            }
        } catch (IOException e) {
            Ui.runTimeException(e.getMessage());
        } catch (DateTimeParseException e) {
            Ui.dateTimeException();
        }
    }

    /**
     * This module is used to process the update of event details without user having to delete the
     * entry and re-add a new one.
     *
     * @param dL     specifies the specific event that needs to be updated
     * @param reader contains the user input to which entry that needs to be updated
     * @throws DateTimeParseException when user inputs an invalid date format
     * @throws IOException            when there is an issue when processing the file.
     */
    public static void processUpdateDeadline(Deadline dL, String reader) {
        Scanner input;
        LocalDate deadline;
        boolean success = true;
        try {
            while (reader != null) {
                System.out.print(Ui.start);
                if (reader.equalsIgnoreCase("1")) {
                    System.out.print("\n\tNew Deadline:");
                    input = new Scanner(System.in);
                    deadline = LocalDate.parse(input.nextLine());
                    dL.setDeadline(deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
                    break;
                } else if (reader.equalsIgnoreCase("2")) {
                    System.out.print("\n\tNew Description:");
                    input = new Scanner(System.in);
                    success = updateDescription(input.nextLine() + " ", dL);
                    break;
                } else {
                    System.out.print("\n\tPlease only select within selection: ");
                    input = new Scanner(System.in);
                    reader = input.nextLine();
                }
            }
            if (success) {
                Storage.refreshFile(taskList.getTasks());
                assert taskList.getSize() > 0 : "Task list is empty after task updated.";
                Ui.showTaskUpdated((Task) dL);
            }
        } catch (IOException e) {
            Ui.runTimeException(e.getMessage());
        } catch (DateTimeParseException e) {
            Ui.dateTimeException();
        }
    }

    /**
     * This method checks if the user key in the keyword "exit"
     *
     * @param reader is the user input being passed into this method
     * @return whether did user really keyed in "exit"
     */
    public static boolean isExit(String reader) {
        if (reader.equalsIgnoreCase("exit")) {
            System.out.println(Ui.start);
            return false;
        }
        return true;
    }

    /**
     * This method updates the description field of the task
     *
     * @param description refers to the user input for the new description that they have entered
     * @param task        refers to the task that is to be updated
     * @return true/false dependent on the user input, it is to prevent user from updating empty description.
     */
    public static boolean updateDescription(String description, Task task) {
        if (description.trim().isEmpty()) {
            Ui.stringIndexOutOfBound("Description");
        } else {
            task.setDescription(description);
            return true;
        }
        return false;
    }

    /**
     * This method takes in the task that is being selected for update
     * It will process the task based on the type of the task (T,D,E)
     *
     * @param task is the task item that user have selected that is to be updated.
     */
    public static void parseUpdate(Task task) {
        try {
            String input;
            Scanner reader;
            if (task.getSymbol() == 'T') {
                System.out.println(Ui.start + "\n\tDescription [Current:(" + task.getDescription().trim() + ")]");
                System.out.print("\n\tNew Description:");
                reader = new Scanner(System.in);
                if (updateDescription(reader.nextLine(), task)) {
                    Storage.refreshFile(taskList.getTasks());
                    assert taskList.getSize() > 0 : "Task list is empty after task updated.";
                    Ui.showTaskUpdated((Task) task);
                }
            } else if (task.getSymbol() == 'D') {
                System.out.println(Ui.start + "\n\tWhich detail do you want to update? Type 'exit' if you do not wish to update");
                System.out.println("\t    1.Deadline [Current:" + ((Deadline) task).getDeadline() + "]");
                System.out.print("\t    2.Description [Current:(" + ((Deadline) task).getOnlyDescription().trim() + ")]\n\tPlease enter number:");
                reader = new Scanner(System.in);
                input = reader.nextLine();
                if (isExit(input)) {
                    processUpdateDeadline((Deadline) task, input);
                }

            } else {
                System.out.println(Ui.start + "\n\tWhich detail do you want to update? Type 'exit' if you do not wish to update");
                System.out.println("\t    1.From [Current:" + ((Event) task).getFrom().trim() + ")]");
                System.out.println("\t    2.To [Current:(" + ((Event) task).getTo() + "]");
                System.out.print("\t    3.Description [Current:(" + ((Event) task).getOnlyDescription().trim() + ")]\n\tPlease enter number:");
                reader = new Scanner(System.in);
                input = reader.nextLine();
                if (isExit(input)) {
                    processUpdateEvent((Event) task, input);
                }
            }
        } catch (IOException e) {
            Ui.runTimeException(e.getMessage());
        }

    }

    /**
     * This method is used to clone a task for the user based on their selection
     *
     * @param t is the task that needs to be cloned
     */
    public static void cloneTask(Task t) {
        try {
            taskList.addTask(t, false);
            Storage.refreshFile(taskList.getTasks());
            assert taskList.getSize() > 0 : "Task list is empty after task cloning.";
        } catch (IOException e) {
            Ui.runTimeException(e.getMessage());
        }
    }

    /**
     * This method is used to process different type of inputs that user have keyed into the system.
     * For each of the commands keyed, it will call different Parser/Ui/Storage methods.
     *
     * @param input takes in user input for processing.
     * @throws NumberFormatException for commands like mark/unmark/delete
     */
    public static void parseCommand(String input) {
        String command = input.split(" ")[0];
        switch (command.toLowerCase()) {
            case "bye":
                Ui.showGoodbye();
                break;
            case "list":
                Ui.printItems();
                break;
            case "mark":
                try {
                    if (!taskList.getTasks().get(Integer.parseInt(input.split(" ")[1]) - 1).isDone()) {
                        taskList.markAsDone(Integer.parseInt(input.split(" ")[1]) - 1);
                        Ui.showTaskMarked(taskList.getTasks().get(Integer.parseInt(input.split(" ")[1]) - 1));
                        Storage.refreshFile(taskList.getTasks());
                    } else {
                        System.out.println(Ui.start + "\n\t Item has already been marked completed" + Ui.end);
                    }
                    assert taskList.getSize() > 0 : "Task list is empty after task marked.";
                } catch (NumberFormatException e) {
                    Ui.numberFormat(command);
                } catch (IOException e) {
                    Ui.runTimeException(e.getMessage());
                } catch (IndexOutOfBoundsException e) {
                    Ui.indexOutOfBound();
                }
                break;
            case "unmark":
                try {
                    if (taskList.getTasks().get(Integer.parseInt(input.split(" ")[1]) - 1).isDone()) {
                        taskList.markAsUnDone(Integer.parseInt(input.split(" ")[1]) - 1);
                        Ui.showTaskUnMarked(taskList.getTasks().get(Integer.parseInt(input.split(" ")[1]) - 1));
                        Storage.refreshFile(taskList.getTasks());
                    } else {
                        System.out.println(Ui.start + "\n\t Item hasn't been marked" + Ui.end);
                    }
                    assert taskList.getSize() > 0 : "Task list is empty after task unmarked.";
                } catch (NumberFormatException e) {
                    Ui.numberFormat(command);
                } catch (IOException e) {
                    Ui.runTimeException(e.getMessage());
                } catch (IndexOutOfBoundsException e) {
                    Ui.indexOutOfBound();
                }
                break;
            case "todo", "deadline", "event":
                if (processTask(input, command)) {
                    Ui.showTaskAdded(taskList.getTasks().get(taskList.getSize() - 1), input);
                }
                break;
            case "delete":
                try {
                    Ui.showTaskRemoved(taskList.getTasks().get(Integer.parseInt(input.split(" ")[1]) - 1));
                    taskList.removeTask(Integer.parseInt(input.split(" ")[1]) - 1);
                    Storage.refreshFile(taskList.getTasks());
                    assert taskList.getSize() > 0 : "Task list is empty after task removed.";
                    break;

                } catch (NumberFormatException e) {
                    Ui.numberFormat(command);
                } catch (IOException e) {
                    Ui.runTimeException(e.getMessage());
                } catch (IndexOutOfBoundsException e) {
                    Ui.indexOutOfBound();
                }
                break;
            case "find":
                ArrayList<Task> resultTasks = taskList.findTask(input.substring(command.length() + 1));
                Ui.printMatchingTask(resultTasks);
                break;
            case "update":
                try {
                    if (input.length() == command.length()) {
                        System.out.println(Ui.start + "\n\tHere is the list of items that you have in your list currently");
                        Ui.printItems();
                        System.out.println("\tWhich item do you want to update? Please indicate 'update follow by an Integer eg. update 1'" + Ui.end);
                    } else {
                        int index = Integer.parseInt(input.split(" ")[1]) - 1;
                        Task task = taskList.getTasks().get(index);
                        parseUpdate(task);
                    }
                } catch (NumberFormatException e) {
                    Ui.numberFormat(command);
                } catch (IndexOutOfBoundsException e) {
                    Ui.indexOutOfBound();
                }
                break;
            case "clone":
                try {
                    if (input.length() == command.length()) {
                        System.out.println(Ui.start + "\n\tHere is the list of items that you have in your list currently");
                        Ui.printItems();
                        System.out.println("\tWhich item do you want to clone? Please indicate 'clone follow by an Integer eg. clone 1'" + Ui.end);
                    } else {
                        int index = Integer.parseInt(input.split(" ")[1]) - 1;
                        Task task = taskList.getTasks().get(index);
                        Task newTask = task.clone();
                        cloneTask(newTask);
                        Ui.showItemCloned(taskList.getTasks().get(taskList.getSize() - 1));
                        System.out.println("\tDo you wish to update this item?(Y/N)" + Ui.end);
                        Scanner reader = new Scanner(System.in);
                        if (reader.nextLine().equalsIgnoreCase("y")) {
                            parseUpdate(taskList.getTasks().get(taskList.getSize() - 1));
                        } else {
                            System.out.println(Ui.end);
                        }
                    }
                } catch (NumberFormatException e) {
                    Ui.numberFormat(command);
                } catch (IndexOutOfBoundsException e) {
                    Ui.indexOutOfBound();
                }
                break;
            default:
                System.out.println(Ui.start + "\n\tOOPS!! I'm sorry, but I don't know what that means :(" + Ui.end);
        }
    }


}
