package jibberJabber.commands;

import jibberJabber.tasks.TaskList;
import jibberJabber.tasks.taskType.Event;
import jibberJabber.tasks.Task;
import jibberJabber.tasks.taskType.ToDo;
import jibberJabber.tasks.taskType.Deadline;
import jibberJabber.ui.Message;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.time.LocalDate.parse;
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
     * Convert string date into formatted local date
     *
     * @param date the input date in local date data type.
     * @param pattern the pattern to format the date
     * @return the local date of the formatted date
     */
    public static LocalDate formatDateInputsAsLocalDate(String date, String pattern){
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(pattern);
        return parse(date, inputFormatter);
    }
    /**
     * Creates a map object to track the start and end date after word split
     *
     * @param todoTask     the task description.
     * @param splitRegex the word to split the string
     * @return a map object containing the start and end date after split
     */
    // Since multiple codes require the same codes, have created this method to capture and reuse across
    public Map<String, LocalDate> parseStartAndEndDates(String todoTask, String splitRegex){
        Map<String, LocalDate> dateRangeMap = new HashMap<>();
        String[] splitWord = todoTask.split(splitRegex);
        if (splitWord.length != 2) {
            Message.printInvalidDateFormatMessage();
            return dateRangeMap;
        }
        String startDate = ExceptionHandling.removeSpaces(splitWord[0]);
        String endDate = ExceptionHandling.removeSpaces(splitWord[1]);
        try {
            LocalDate convertedStartDateTime = formatDateInputsAsLocalDate(startDate, "d/M/yyyy");
            LocalDate convertedEndDateTime = formatDateInputsAsLocalDate(endDate, "d/M/yyyy");
            dateRangeMap.put("startDate", convertedStartDateTime);
            dateRangeMap.put("endDate", convertedEndDateTime);
        } catch (DateTimeParseException e) {
            Message.printInvalidDateFormatMessage();
        }
        return dateRangeMap;
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
     * @param isFromFile   indicates whether the task was read from a file: true if read from file (not to display default message), false if its from user input (display default message)
     */
    public void processMarkKeyword(TaskList todoTaskList, String index, boolean isMark, boolean isFromFile){
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
    }
    /**
     * Removes a task from the TaskList.
     *
     * @param todoTaskList the TaskList containing tasks.
     * @param index        the index of the task to remove.
     */
    public void processRemoveKeyword(TaskList todoTaskList, String index){
        if (isInvalidIndex(todoTaskList, index)){
            return;
        }
        int convertedIndex = Integer.parseInt(index) - 1;
        Task deleteTask = todoTaskList.removeTask(convertedIndex);
        int totalNumberOfTodoTask = Task.decreaseTotalNumberOfTodoTask();
        Message.printDeleteTaskMessage(totalNumberOfTodoTask, deleteTask.printAddedTask());
    }
    /**
     * Processes task keyword -  todo / deadlines / events
     *
     * @param todoTask     the task description.
     * @param todoTaskList the TaskList to add the task to.
     * @param isFromFile   indicates whether the task was read from a file: true if read from file (not to display default message), false if its from
     */
    public void processTodoTask(String todoTask, TaskList todoTaskList, boolean isFromFile) {
        String newTodoTask = ExceptionHandling.removeSpaces( todoTask.replaceAll("(?i)todo", ""));
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
        LocalDate deadlineDateTime;
        String deadlineTask = ExceptionHandling.removeSpaces(todoTask.replaceAll("(?i)deadline", ""));
        String[] deadlineDetails = deadlineTask.split("(?i)/by");
        String newDeadlineTask = ExceptionHandling.removeSpaces(deadlineDetails[0]);
        String deadlineOfTask = ExceptionHandling.removeSpaces(deadlineDetails[1]);
        try {
            deadlineDateTime = formatDateInputsAsLocalDate(deadlineOfTask, "d/M/yyyy");
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
        String eventTask = ExceptionHandling.removeSpaces(todoTask.replaceAll("(?i)event", ""));
        String[] eventDetails = eventTask.split("(?i)/from");
        String newEventTask = ExceptionHandling.removeSpaces(eventDetails[0]);
        Map<String, LocalDate> dateRange = parseStartAndEndDates(eventDetails[1], "(?i)/to");
        Event inputEventTask = new Event(newEventTask, dateRange.get("startDate"), dateRange.get("endDate"));
        todoTaskList.addTask(inputEventTask);
        if (!isFromFile) {
            Message.printAddedTaskMessage(todoTaskList.getTotalTaskCount(), inputEventTask.printAddedTask());
        }
    }
    /**
     * Processes task that needs to be completed within a date period
     *
     * @param todoTask the task description with the date period
     * @param todoTaskList the task list to sieve through the task.
    */
    public void processCompleteTaskWithinPeriod(String todoTask, TaskList todoTaskList){
        todoTask = ExceptionHandling.removeSpaces(todoTask.replaceAll("(?i)complete tasks? between", ""));
        Map<String, LocalDate> dateRange = parseStartAndEndDates(todoTask, "/to");
        LocalDate startDate = dateRange.get("startDate");
        LocalDate endDate = dateRange.get("endDate");
        if (dateRange.isEmpty() || startDate == null || endDate == null || startDate.isAfter(endDate)) {
            Message.printInvalidTimeMessage();
            return;
        }
        TaskList tasksWithinPeriod = todoTaskList.getTasksWithinPeriod(startDate, endDate);

        if (tasksWithinPeriod.getTotalTaskCount() == 0) {
            Message.printNoTaskFoundWithinPeriodMessage();
        }
        else {
            tasksWithinPeriod.printTaskList();
        }
    }
    /**
     * Processes task that contains the keyword
     *
     * @param todoTask the task description with event period range
     */
    public void processFindKeyword(String todoTask, TaskList todoTaskList){
        todoTask = ExceptionHandling.removeSpaces(todoTask.replaceAll("(?i)find", ""));
        if (todoTask.isEmpty()) {
            Message.printNoTaskFoundWithKeywordMessage();
            return;
        }
        TaskList tasksWithinPeriod = todoTaskList.getTasksWithMatchingKeyword(todoTask, todoTaskList);
        if (tasksWithinPeriod.getTotalTaskCount() == 0) {
            Message.printNoTaskFoundWithKeywordMessage();
        }
        else {
            tasksWithinPeriod.printTaskList();
        }
    }

}
