package jibberJabber.commands;

import jibberJabber.tasks.TaskList;
import jibberJabber.tasks.taskType.Event;
import jibberJabber.tasks.Task;
import jibberJabber.tasks.taskType.ToDo;
import jibberJabber.tasks.taskType.Deadline;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;

/**
 * The Exception handling class provides methods for handling exceptions
 */
public class ExceptionHandling {
    /**
     * Validates whether the input index for marking or unmarking a task is a valid integer
     *
     * @param input the input string to validate.
     * @return true if the input is a valid integer, false if it's not.
     */
    //Solution below adapted from https://www.quora.com/What-is-the-function-of-a-isInteger-in-Java
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch (NumberFormatException e) { return false;
        }
    }
    /**
     * Remove unnecessary whitespace between words and start / end of the string
     *
     * @param input the input string.
     * @return the string to check for extra spaces to be removed.
     */
    public static String removeSpaces(String input){
        return input.trim().replaceAll("\\s+", " ");
    }
    /**
     * Convert localDate date into formatted string
     *
     * @param date the input date in local date data type.
     * @return the string of the formatted date
     */
    public static String formatDateInputsAsString(LocalDate date){
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return removeSpaces(date.format(inputFormatter));
    }
    /**
     * Validates if the input contains only specified keywords or if no name has been provided for the task
     *
     * @param input the input string to validate.
     * @return true if the input is empty or invalid, false if it is valid.
     */
    public static boolean isEmptyInput(String input) {
        input = removeSpaces(input);
        boolean isEmptyTask = false;
        if (input.equalsIgnoreCase("todo") ||
                input.equalsIgnoreCase("deadline") ||
                input.equalsIgnoreCase("event") ||
                input.isEmpty()) {
            isEmptyTask = true;
        } else {
            String[] splitWord = input.split(" ");
            String keyword = splitWord[0];
            switch (keyword) {
                case "todo" -> {
                    if (removeSpaces(splitWord[1]).isEmpty()) {
                        isEmptyTask = true;
                    }
                }
                case "deadline" -> {
                    input = removeSpaces(input.replace("deadline", ""));
                    if (input.contains("/by")) {
                        String[] deadlineDetails = input.split("/by");
                        // Check if the task is empty
                        if (deadlineDetails.length == 0 || removeSpaces(deadlineDetails[0]).isEmpty()) {
                            isEmptyTask = true;
                        }
                    }
                }
                case "event" -> {
                    input = removeSpaces(input.replace("event", ""));
                    if (!input.contains("/from") || !input.contains("/to")) {
                        return false;
                    }
                    String[] eventDetails = input.split("/from");
                    if (eventDetails.length == 0 || removeSpaces(eventDetails[0]).isEmpty()) {
                        isEmptyTask = true;
                    }
                }
            }
        }
        return isEmptyTask;
    }
    /**
     * Validates if there are duplicate tasks, including task type, already added to the list
     *
     * @param todoTaskList the list of tasks to check against.
     * @param newTask      the new task to validate.
     * @return true if the task is duplicated, false if not.
     */
    public static boolean isTaskDuplicated(ArrayList<Task> todoTaskList, String newTask) {
        for (Task task : todoTaskList) {
            if(task instanceof ToDo){
                newTask = removeSpaces(newTask.replace("todo", ""));
                if (task.getTaskName().equalsIgnoreCase(newTask)){
                    return true;
                }
            } else if (task instanceof Deadline){
                newTask = removeSpaces(newTask.replace("deadline", ""));
                if (newTask.contains("/by")){
                    String[] deadlineDetails = newTask.split("/by");
                    if (deadlineDetails.length == 2 ){
                        String newDeadlineTask = removeSpaces(deadlineDetails[0]);
                        String deadlineOfTask = removeSpaces(deadlineDetails[1]);
                        String formattedBy = formatDateInputsAsString(((Deadline) task).by);
                        if (formattedBy.equalsIgnoreCase(deadlineOfTask) &&
                            task.getTaskName().equalsIgnoreCase(newDeadlineTask)) {
                                return true;
                        }
                    }
                }
            } else if (task instanceof Event){
                newTask = removeSpaces(newTask.replace("event", ""));
                if (newTask.contains("/from") && newTask.contains("/to")){
                    String[] eventDetails = newTask.split("/from");
                    if (eventDetails.length == 2){
                        String newEventTask = removeSpaces(eventDetails[0]);
                        String[] taskDurationDetails = eventDetails[1].split("/to");
                        if (taskDurationDetails.length == 2){
                            String from = removeSpaces(taskDurationDetails[0]);
                            String to = removeSpaces(taskDurationDetails[1]);
                            String fromDateInput = formatDateInputsAsString(((Event) task).from);
                            String toDateInput = formatDateInputsAsString(((Event) task).to);
                            if (task.getTaskName().equalsIgnoreCase(newEventTask) &&
                                fromDateInput.equalsIgnoreCase(from) &&
                                toDateInput.equalsIgnoreCase(to)) {
                                    return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    /**
     * Validates if the task has already been marked or unmarked in the list
     *
     * @param todoTaskList the TaskList containing all tasks objects
     * @param index        the index of the task to validate.
     * @param isDone       the status of the task
     * @return true if the task is already marked/unmarked as per the input, false if not.
     */
    public static boolean isTaskMarked(TaskList todoTaskList, int index, boolean isDone){
        try{
            return todoTaskList.getTaskById(index - 1).isDone == isDone;
        } catch (Exception e){ return false;
        }
    }
    /**
     * Validates if the provided deadline for the task is missing
     *
     * @param deadlineDetails an array of deadline details split by "/by".
     * @return true if the deadline details format are valid, false if not.
     */
    public static boolean isValidDeadlineInput(String[] deadlineDetails) {
        return deadlineDetails.length >= 2 && !removeSpaces(deadlineDetails[1]).isEmpty();
    }
    /**
     * Validates if the event period provided for the task is missing
     *
     * @param eventDetails an array of event details split by "/from" and by "/to"
     * @return true if the event details format are valid, false if not.
     */
    public static boolean isValidEventInput(String[] eventDetails) {
        if (eventDetails.length < 2) {
            return false;
        }
        String[] eventOfTask = eventDetails[1].split("/to");
        return eventOfTask.length >= 2 && !removeSpaces(eventOfTask[0]).isEmpty() && !removeSpaces(eventOfTask[1]).isEmpty();
    }
    /**
     * Validates if the file is created
     *
     * @param file the file to check.
     * @return true if the file was created successfully, false if not.
     */
    public static boolean isFileCreated (File file) {
        try {
            return file.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }
    /**
     * Validates if the given date string matches the expected format.
     *
     * @param date the date string to check against.
     * @return true if the date format is invalid, false if not.
     */
    public static boolean isInvalidDate(String date){
        Pattern pattern = Pattern.compile("^\\d{1,2}/\\d{1,2}/\\d{4}$");
        Matcher matcher = pattern.matcher(date);
        return !matcher.matches();
    }
    /**
     * Validates if the given keyword string matches the enum keywords
     *
     * @return true if the date format is invalid, false if not.
     */
    public static boolean isInvalidKeywordCommand(String keyword){
        try{
            Keywords.valueOf(keyword.toUpperCase());
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }
}
