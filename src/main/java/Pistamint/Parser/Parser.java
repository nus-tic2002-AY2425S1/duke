package Pistamint.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Pistamint.General.*;
import Pistamint.Storage.Storage;
import Pistamint.TaskList.TaskList;
import Pistamint.Ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Parser {
    private static TaskList taskList;

    //private static Pistamint.Ui ui;
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * This method is used to process tasks in the duke.txt
     * by taking in the taskData argument, splits up the data into three parts
     * to identify which task class it belongs to, assign and adds into the tasklist.
     *
     * @param taskData are each line stored in duke.txt storage file
     */
    public static void parseTask(String taskData) {
        char symbol = taskData.charAt(0);
        String[] dataParts = taskData.split("\\|");
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

    /**
     * This method returns true if the commands have been successfully processed (added to tasklist and also the file storoage)
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
                Todo todo = new Todo(action.trim(), 'T');
                taskList.addTask(todo, false);
            } else {
                String description;
                try {
                    if (task.equalsIgnoreCase("deadline")) {
                        description = input.substring(input.indexOf(" "), input.indexOf("/by"));
                        timeline = LocalDate.parse(input.substring(input.indexOf("/by") + 3).trim());
                        String dL = timeline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                        Deadline deadline = new Deadline(description.trim(), 'D', dL);
                        taskList.addTask(deadline, false);
                    } else if (task.equalsIgnoreCase("event")) {
                        description = input.substring(input.indexOf(" "), input.indexOf("/from"));
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
     * This module is used to process the update of event details without user having to delete the
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
        try {
            System.out.print(Ui.start);

            if (reader.equalsIgnoreCase("1")) {
                System.out.print("\n\tNew From:");
                input = new Scanner(System.in);
                from = LocalDate.parse(input.nextLine());
                event.setFrom(from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
            } else if (reader.equalsIgnoreCase("2")) {
                System.out.print("\n\tNew To:");
                input = new Scanner(System.in);
                to = LocalDate.parse(input.nextLine());
                event.setTo(to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
            } else {
                System.out.print("\n\tNew Description:");
                input = new Scanner(System.in);
                event.setDescription(input.nextLine()+ " ");
            }
            Storage.refreshFile(taskList.getTasks());
            Ui.showTaskUpdated((Task) event);
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
     * @throws IOException when there is an issue when processing the file.
     */
    public static void processUpdateDeadline(Deadline dL, String reader) {
        Scanner input;
        LocalDate deadline;
        try {
            System.out.print(Ui.start);
            if (reader.equalsIgnoreCase("1")) {
                System.out.print("\n\tNew From:");
                input = new Scanner(System.in);
                deadline = LocalDate.parse(input.nextLine());
                dL.setDeadline(deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
            } else {
                System.out.print("\n\tNew Description:");
                input = new Scanner(System.in);
                dL.setDescription(input.nextLine() + " ");
            }
            Storage.refreshFile(taskList.getTasks());
            Ui.showTaskUpdated((Task) dL);
        } catch (IOException e) {
            Ui.runTimeException(e.getMessage());
        } catch (DateTimeParseException e) {
            Ui.dateTimeException();
        }
    }

    /**
     * This method takes in the task that is being selected for update
     *
     * @param task is the task item that user have selected that is to be updated.
     */
    public static void parseUpdate(Task task) {
        try {
            Scanner reader;
            if (task.getSymbol() == 'T') {
                System.out.print(Ui.start + "\n\tNew Description:");
                reader = new Scanner(System.in);
                task.setDescription(reader.nextLine());
                Storage.refreshFile(taskList.getTasks());
                Ui.showTaskUpdated((Task) task);
            } else if (task.getSymbol() == 'D') {
                System.out.println(Ui.start + "\n\tWhich detail do you want to update?");
                System.out.println("\t    1.Deadline (by)");
                System.out.print("\t    2.Description\n\tPlease enter number:");
                reader = new Scanner(System.in);
                processUpdateDeadline((Deadline) task, reader.nextLine());
            } else {
                System.out.println(Ui.start + "\n\tWhich detail do you want to update?");
                System.out.println("\t    1.From");
                System.out.println("\t    2.To");
                System.out.print("\t    3.Description\n\tPlease enter number:");
                reader = new Scanner(System.in);
                processUpdateEvent((Event) task, reader.nextLine());
            }
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
                    taskList.markAsDone(Integer.parseInt(input.split(" ")[1]) - 1);
                    Ui.showTaskMarked(taskList.getTasks().get(Integer.parseInt(input.split(" ")[1]) - 1));
                    Storage.refreshFile(taskList.getTasks());
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
                    taskList.markAsUnDone(Integer.parseInt(input.split(" ")[1]) - 1);
                    Ui.showTaskUnMarked(taskList.getTasks().get(Integer.parseInt(input.split(" ")[1]) - 1));
                    Storage.refreshFile(taskList.getTasks());
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

            default:
                System.out.println(Ui.start + "\n\tOOPS!! I'm sorry, but I don't know what that means :(" + Ui.end);
        }
    }


}
