package jibberJabber.commands;

import jibberJabber.tasks.TaskFiles;
import jibberJabber.tasks.TaskList;
import jibberJabber.tasks.taskType.Event;
import jibberJabber.tasks.Task;
import jibberJabber.tasks.taskType.ToDo;
import jibberJabber.tasks.taskType.Deadline;
import jibberJabber.ui.Message;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.time.LocalDateTime.parse;
/**
 * The keyword class handles keyword-specific operations for task management, such as adding, marking, removing, and listing tasks.
 */
public class KeywordHandling {
    // Exception checks for processMarkKeyword / processRemoveKeyword methods:
    // 1. Check if the second input value is a numerical string
    // 2. Check if the input string is out of bound from the task list or index is 0 (invalid index)
    private boolean isInvalidIndex(TaskList todoTaskList, String index) {
        int totalNumberOfTaskObj = todoTaskList.getTotalTaskCount();
        if (totalNumberOfTaskObj == 0) {
            Message.printEmptyMessage(true);
            return true;
        }
        if (!ExceptionHandling.isInteger(index)) {
            Message.printInvalidIntegerMessage();
            return true;
        }
        int convertedIndex = Integer.parseInt(index) - 1;
        if (convertedIndex >= totalNumberOfTaskObj || convertedIndex < 0) {
            Message.printOutOfBoundsMessage();
            return true;
        }
        return false;
    }
    /**
     * Lists all tasks in the TaskList. If the list is empty, a message is printed.
     *
     * @param todoTaskList the TaskList to display.
     */
    public void processListKeyword(TaskList todoTaskList) {
        if (todoTaskList.getTotalTaskCount() == 0){
            Message.printEmptyMessage(true);
        } else todoTaskList.printTaskList();
    }
    /**
     * Check if the task have been marked / unmarked before
     *
     * @param todoTaskList the TaskList containing tasks.
     * @param index        the index of the task to mark/unmark.
     * @param isMark       true to mark the task as done, false to unmark it.
     * @param taskFiles    the TaskFiles object for file handling.
     * @param isFromFile   indicates whether the task was read from a file: true if read from file (not to display default message), false if its from user input (display default message)
     */
    public void processMarkKeyword(TaskList todoTaskList, String index, boolean isMark, TaskFiles taskFiles, boolean isFromFile){
        if (isInvalidIndex(todoTaskList, index)){
            return;
        }
        int convertedIndex = Integer.parseInt(index) - 1;
        if (ExceptionHandling.isTaskMarked(todoTaskList, convertedIndex + 1, isMark)){
            Message.printMarkedTaskMessage();
            return;
        }
        Task markTask = todoTaskList.getTaskById(convertedIndex);
        markTask.setTaskMarkStatus(todoTaskList, convertedIndex, isMark, isFromFile);
        if (!todoTaskList.getTasks().isEmpty()){
            taskFiles.writeToTextFile(todoTaskList, todoTaskList.getLastTask(), false);
        }
    }
    /**
     * Removes a task from the TaskList.
     *
     * @param todoTaskList the TaskList containing tasks.
     * @param index        the index of the task to remove.
     * @param taskFiles    the TaskFiles object for file handling.
     */
    public void processRemoveKeyword(TaskList todoTaskList, String index, TaskFiles taskFiles){
        if (isInvalidIndex(todoTaskList, index)){
            return;
        }
        int convertedIndex = Integer.parseInt(index) - 1;
        Task deleteTask = todoTaskList.removeTask(convertedIndex);
        int totalNumberOfTodoTask = Task.decreaseTotalNumberOfTodoTask();
        if (!todoTaskList.getTasks().isEmpty()){
            taskFiles.writeToTextFile(todoTaskList, todoTaskList.getLastTask(), false);
            Message.printDeleteTaskMessage(totalNumberOfTodoTask, deleteTask.printAddedTask());
        }
    }
    /**
     * Processes task keyword -  todo / deadlines / events
     *
     * @param todoTask     the task description.
     * @param todoTaskList the TaskList to add the task to.
     * @param isFromFile   indicates whether the task was read from a file: true if read from file (not to display default message), false if its from
     */
    public void processTodoTask(String todoTask, TaskList todoTaskList, boolean isFromFile) {
        String newTodoTask = ExceptionHandling.removeSpaces( todoTask.replace("todo", ""));
        ToDo inputTodoTask =  new ToDo(newTodoTask);
        todoTaskList.addTask(inputTodoTask);
        if (!isFromFile) {
            Message.printAddedTaskMessage(todoTaskList.getTotalTaskCount(), inputTodoTask.printAddedTask());
        }
    }
    /**
     * Processes a "deadline" task and adds it to the TaskList.
     *
     * @param todoTask     the task description with a deadline.
     * @param todoTaskList the TaskList to add the task to.
     * @param isFromFile   indicates whether the task was read from a file: true if read from file (not to display default message), false if its from
     */
    public void processDeadlineTask(String todoTask, TaskList todoTaskList, boolean isFromFile) {
        LocalDateTime deadlineDateTime;
        String deadlineTask = ExceptionHandling.removeSpaces(todoTask.replace("deadline", ""));
        String[] deadlineDetails = deadlineTask.split("/by");
        // Checks if deadline is provided
        if (!ExceptionHandling.isValidDeadlineInput(deadlineDetails)) {
            Message.printMissingParameterMessage("deadline");
            return;
        }
        String newDeadlineTask = ExceptionHandling.removeSpaces(deadlineDetails[0]);
        String deadlineOfTask = ExceptionHandling.removeSpaces(deadlineDetails[1]);
        if (ExceptionHandling.isInvalidDate(deadlineOfTask)){
            Message.printInvalidDateFormatMessage();
            return;
        }
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            deadlineDateTime = parse(deadlineOfTask, inputFormatter);
        } catch (DateTimeParseException e) {
            Message.printInvalidDateFormatMessage();
            return;
        }
        Deadline inputDeadlineTask = new Deadline(newDeadlineTask, deadlineDateTime);
        todoTaskList.addTask(inputDeadlineTask);
        if (!isFromFile) {
            Message.printAddedTaskMessage(todoTaskList.getTotalTaskCount(), inputDeadlineTask.printAddedTask());
        }
    }
    /**
     * Processes an "event" task and adds it to the TaskList.
     *
     * @param todoTask     the task description with event period range
     * @param todoTaskList the TaskList to add the task to.
     * @param isFromFile   indicates whether the task was read from a file: true if read from file (not to display default message), false if its from
     */
    public void processEventTask(String todoTask, TaskList todoTaskList, boolean isFromFile) {
        LocalDateTime fromDateTime;
        LocalDateTime toDateTime;
        String eventTask = ExceptionHandling.removeSpaces(todoTask.replace("event", ""));
        if (!eventTask.contains("/from") || !eventTask.contains("/to")) {
            // Checks if the input value is in proper format
            Message.printMissingParameterMessage("event");
            return;
        }
        String[] eventDetails = eventTask.split("/from");
        if (!ExceptionHandling.isValidEventInput(eventDetails)) {
            // Checks if event duration is provided
            Message.printMissingParameterMessage("event");
            return;
        }
        String newEventTask = ExceptionHandling.removeSpaces(eventDetails[0]);
        String[] eventDurationDetails = eventDetails[1].split("/to");
        String from = ExceptionHandling.removeSpaces(eventDurationDetails[0]);
        String to = ExceptionHandling.removeSpaces(eventDurationDetails[1]);
        if (ExceptionHandling.isInvalidDate(from) || ExceptionHandling.isInvalidDate(to)) {
            Message.printInvalidDateFormatMessage();
            return;
        }
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            fromDateTime = parse(from, inputFormatter);
            toDateTime = parse(to, inputFormatter);
        } catch (DateTimeParseException e) {
            Message.printInvalidDateFormatMessage();
            return;
        }
        Event inputEventTask = new Event(newEventTask, fromDateTime, toDateTime);
        todoTaskList.addTask(inputEventTask);
        if (!isFromFile) {
            Message.printAddedTaskMessage(todoTaskList.getTotalTaskCount(), inputEventTask.printAddedTask());
        }
    }
}
