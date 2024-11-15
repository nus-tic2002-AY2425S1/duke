package jibberJabber.commands;

import jibberJabber.tasks.TaskFiles;
import jibberJabber.tasks.TaskList;
import jibberJabber.tasks.taskType.Event;
import jibberJabber.tasks.Task;
import jibberJabber.tasks.taskType.ToDo;
import jibberJabber.tasks.taskType.Deadline;
import jibberJabber.ui.Message;

public class KeywordHandling {
    // Exception checks for processMarkKeyword / processRemoveKeyword methods:
    // 1. Check if the second input value is a numerical string
    // 2. Check if the input string is out of bound from the task list or index is 0 (invalid index)
    private boolean isValidateIndex(int totalNumberOfTaskObj, String index) {
        if (totalNumberOfTaskObj == 0) {
            Message.printEmptyMessage(true);
            return false;
        } else if (!ExceptionHandling.isInteger(index)) {
            Message.printInvalidIntegerMessage();
            return false;
        } else {
            int convertedIndex = Integer.parseInt(index) - 1;
            if (convertedIndex >= totalNumberOfTaskObj || convertedIndex < 0) {
                Message.printOutOfBoundsMessage();
                return false;
            }
        }
        return true;
    }
    public void processListKeyword(TaskList taskList) {
        if (taskList.getTotalTaskCount() == 0){
            Message.printEmptyMessage(true);
        } else taskList.printTaskList();
    }
    // Check if the task have been marked / unmarked before
    public void processMarkKeyword(TaskList todoTaskList, String index, boolean isMark, TaskFiles taskFiles, boolean isFromFile){
        if (isValidateIndex(todoTaskList.getTotalTaskCount(), index)){
            int convertedIndex = Integer.parseInt(index) - 1;
            if (ExceptionHandling.isTaskMarked(todoTaskList, convertedIndex + 1, isMark)){
                Message.printMarkedTaskMessage();
            } else {
                Task markTask = todoTaskList.getTaskById(convertedIndex);
                markTask.setTaskMarkStatus(todoTaskList, convertedIndex, isMark, isFromFile);
                taskFiles.writeToTextFile(todoTaskList, todoTaskList.getLastTask(), false);
            }
        }
    }
    public void processRemoveKeyword(TaskList todoTaskList, String index, TaskFiles taskFiles){
        if (isValidateIndex(todoTaskList.getTotalTaskCount(), index)){
            int convertedIndex = Integer.parseInt(index) - 1;
            Task deleteTask = todoTaskList.removeTask(convertedIndex);
            int totalNumberOfTodoTask = Task.decreaseTotalNumberOfTodoTask();
            taskFiles.writeToTextFile(todoTaskList, todoTaskList.getLastTask(), false);
            Message.printDeleteTaskMessage(totalNumberOfTodoTask, deleteTask.printAddedTask());
        }
    }
    // task commandKeyword = todos / deadlines / events
    public void processTodoTask(String todoTask, TaskList todoTaskList, boolean isFromFile) {
        String newTodoTask = ExceptionHandling.removeSpaces( todoTask.replace("todo", ""));
        ToDo inputTodoTask =  new ToDo(newTodoTask);
        todoTaskList.addTask(inputTodoTask);
        if (!isFromFile) {
            Message.printAddedTaskMessage(todoTaskList.getTotalTaskCount(), inputTodoTask.printAddedTask());
        }
    }
    public void processDeadlineTask(String todoTask, TaskList todoTaskList, boolean isFromFile) {
        String deadlineTask = ExceptionHandling.removeSpaces(todoTask.replace("deadline", ""));
        String[] deadlineDetails = deadlineTask.split("/by");
        // Checks if deadline is provided
        if (!ExceptionHandling.isValidDeadlineInput(deadlineDetails)) {
            Message.printMissingParameterMessage("deadline");
            return;
        }
        String newDeadlineTask = ExceptionHandling.removeSpaces(deadlineDetails[0]);
        String deadlineOfTask = ExceptionHandling.removeSpaces(deadlineDetails[1]);
        Deadline inputDeadlineTask = new Deadline(newDeadlineTask, deadlineOfTask);
        todoTaskList.addTask(inputDeadlineTask);
        if (!isFromFile) {
            Message.printAddedTaskMessage(todoTaskList.getTotalTaskCount(), inputDeadlineTask.printAddedTask());
        }
    }
    public void processEventTask(String todoTask, TaskList todoTaskList, boolean isFromFile) {
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
        Event inputEventTask = new Event(newEventTask, from, to);
        todoTaskList.addTask(inputEventTask);
        if (!isFromFile) {
            Message.printAddedTaskMessage(todoTaskList.getTotalTaskCount(), inputEventTask.printAddedTask());
        }
    }
}
