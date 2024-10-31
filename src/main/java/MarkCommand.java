import java.util.ArrayList;
import java.util.Arrays;

public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <task number>";
    public static final String MESSAGE_MARK_SUCCESS = "Nice! I've marked this task as done:";
    public static final String MESSAGE_EMPTY_TASKLIST = "The task list is empty. Please add a task first.";
    
    protected int taskNumber;
    
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    
    public int getTaskNumber() {
        return taskNumber;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CommandException, StorageOperationException {
        
        int taskNumber = getTaskNumber();
        int taskListSize = taskList.getSize();
        final String MESSAGE_NONEXISTENT_TASK = "Task number " + taskNumber + " not found. Please enter a valid task number from 1 to " + taskListSize + ".";

        int indexToMark = taskNumber - 1;
        Task taskToMark = null;
        try {
            taskToMark = taskList.getTask(indexToMark);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new CommandException(MESSAGE_NONEXISTENT_TASK);
        }
        final String MESSAGE_ALREADY_MARKED = "The task `" + taskToMark + "` is already marked as done. No action done.";
        
        String[] messageList = null;

        boolean isMarkedSuccess = taskList.markTask(indexToMark);
        if (isMarkedSuccess == true) {
            storage.saveTasks(taskList);
            messageList = new String[]{MESSAGE_MARK_SUCCESS, ui.formatSpace(2) + taskToMark};
        } else {
            messageList = new String[]{MESSAGE_ALREADY_MARKED};
        }

        ui.printMessage(messageList);


        // try {
        //     boolean isMarkedSuccess = taskList.markTask(indexToMark);
        // } catch ()




        // String message;
        // if (taskList.isEmpty()) {
        //     message = MESSAGE_EMPTY_TASKLIST;
        // }

        // if (taskNumber > taskListSize) {
        //     throw new TaskException(MESSAGE_NONEXISTENT_TASK);
        // }
        

        // try {
        //     boolean isMarkedSuccess = taskList.markTask(indexToMark);
        //     if (isMarkedSuccess) {          // operation successful
    
        //     } else {        // task is already marked done before operation
        //         ui.printMessage(MESSAGE_ALREADY_MARKED);
        //     }
        // } catch (IndexOutOfBoundsException | NumberFormatException e) {
        //     // throw new CommandException(MESSAGE_NONEXISTENT_TASK);
        // }
        
        // String[] messageList = null;



        // ArrayList<String> messageList = new ArrayList<>(Arrays.asList("Here are the tasks in your list:"));

        // https://stackoverflow.com/questions/5554734/what-causes-a-java-lang-arrayindexoutofboundsexception-and-how-do-i-prevent-it
        // https://www.geeksforgeeks.org/array-index-out-of-bounds-exception-in-java/
        // try {
        //     // indexToMark = Integer.parseInt(input.substring(indexOfSpace + 1)) - 1;
        //     indexToMark = Integer.parseInt(inputIndex) - 1;
        //     taskToMark = taskList.get(indexToMark);
            
        //     String alreadyMarkedMessage = "The task is already marked as done. No action done.";
            
        //     // System.out.println("taskToMark getIsDone: " + taskToMark.getIsDone());
        //     // System.out.println("taskToMark getIsDoneValue: " + taskToMark.getIsDoneValue());

        //     if (taskToMark.getIsDone() == true) {
        //         // System.out.println("getIsDone is true");
        //         // throw new TaskException(alreadyMarkedMessage.toString());
        //         messageList = new String[]{alreadyMarkedMessage};
        //         throw new TaskException(alreadyMarkedMessage);
        //     }
    
        //     // Mark task as done
        //     message = "Nice! I've marked this task as done:";
        //     // messageList.add(message);
        //     taskToMark.setDone(true);

        //     taskFile.markTask(taskList, indexToMark);

        //     // taskFile.replaceTask(taskList, taskToMark);
        //     // taskToMark.writeToFile(taskFile);

        //     // messageList.add(formatSpace(2) + taskToMark);
        //     // String[] messageList = {message, formatSpace(2) + taskToMark};

        //     messageList = new String[]{message, formatSpace(2) + taskToMark};

        // } catch (IndexOutOfBoundsException | NumberFormatException e) {     // handles the case where index (from input) > number of items in the list, or when index (from input) > number of items in the list
        //     int taskListSize = taskList.size();
        //     if (taskListSize < 1) {
        //         message = "Your task list is empty. Please add a task first.";
        //     } else {
        //         message = "Task not found. Please enter a valid task number from 1 to " + taskList.size() + ".";
        //     }
        //     messageList = new String[]{message};
        //     // messageList.add(message);
        //     throw new TaskException(message);
        // }

        // // System.out.println("Now, messageList is " + messageList);
        // // Print result on CLI
        // // String[] messageList;
        // // if (taskToMark == null) {
        // //     String[] messageList = {};
        // // }
        // // System.out.println(Arrays.toString(messageList));
        // ui.printMessage(messageList);

        // return taskList;
    }
}
