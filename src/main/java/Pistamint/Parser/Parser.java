package Pistamint.Parser;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Pistamint.General.*;
import Pistamint.Storage.Storage;
import Pistamint.TaskList.TaskList;
import Pistamint.Ui.Ui;
import java.io.IOException;
import java.time.format.DateTimeParseException;

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
     * @param taskData are each line stored in duke.txt storage file
     * @throws IOException when there's issues with the file
     */
    public static void parseTask(String taskData) throws IOException {
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
     * @param task the key command that user wants to do
     * @return true/false
     * @throws DateTimeParseException if the date time is in invalid format to be parsed.
     * @throws StringIndexOutOfBoundsException if the user did not complete the command input for event/deadline/todo.
     */
    public static boolean processTask(String input, String task) {
        String action = "";
        LocalDate timeline,to,from;
        try {
            if (task.equalsIgnoreCase("todo")) {
                action = input.substring(input.indexOf(" "));
                Todo todo = new Todo(action.trim(), 'T');
                taskList.addTask(todo, false);
            } else {
                String description;
                try{
                if (task.equalsIgnoreCase("deadline")) {
                    description = input.substring(input.indexOf(" "), input.indexOf("/by"));
                    timeline = LocalDate.parse(input.substring(input.indexOf("/by") + 3).trim());
                    String dL=timeline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
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
                }catch(DateTimeParseException e){
                    System.out.println(Ui.start+"\n\tThe date format you have keyed in is invalid. Please key in the following format 'yyyy-MM-dd'"+Ui.end);
                    return false;
                }
            }
            return true;
        } catch (StringIndexOutOfBoundsException ex) {
            System.out.println(Ui.start + "\n\tOOPS!!! The description of " + task + " is incomplete." + Ui.end);
        }
        return false;
    }

    /**
     * This method is used to process different type of inputs that user have keyed into the system.
     * For each of the commands keyed, it will call different Parser/Ui/Storage methods.
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
                    taskList.markAsDone(Integer.parseInt(input.split(" ")[1])-1);
                    Ui.showTaskMarked(taskList.getTasks().get(Integer.parseInt(input.split(" ")[1])-1));
                } catch (NumberFormatException e) {
                    System.out.println(Ui.start+"\n\tYour input might be incorrect / out of range, please input the command 'mark' follow by an Integer\n\teg. mark 1 "+Ui.end);
                }
                break;
            case "unmark":
                try {
                    taskList.markAsUnDone(Integer.parseInt(input.split(" ")[1]) - 1);
                    Ui.showTaskUnMarked(taskList.getTasks().get(Integer.parseInt(input.split(" ")[1]) - 1));
                }catch (NumberFormatException e) {
                    System.out.println(Ui.start+"\n\tYour input might be incorrect / out of range, please input the command 'mark' follow by an Integer\n\teg. unmark 1"+Ui.end);
                }
                break;
            case "todo", "deadline","event":
                if(processTask(input, command)) {
                    Ui.showTaskAdded(taskList.getTasks().get(taskList.getSize() - 1), input);
                }
                break;
            case "delete":
                try {
                    Ui.showTaskRemoved(taskList.getTasks().get(Integer.parseInt(input.split(" ")[1])-1));
                    taskList.removeTask(Integer.parseInt(input.split(" ")[1])-1);
                    Storage.removeFromFile(taskList.getTasks());
                    break;

                }catch (NumberFormatException e) {
                    System.out.println(Ui.start+"\n\tYour input is incorrect, please input the command 'delete' follow by an Integer\n\teg.delete 1"+Ui.end);
                } catch (IOException e) {
                    Ui.runTimeException(e.getMessage());
                }
                break;
            default:
                System.out.println(Ui.start+ "\n\tOOPS!! I'm sorry, but I don't know what that means :("+Ui.end);
        }
    }


}
